package com.thekitchenfridge.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtUtility {

    private final String SECRET_KEY;
    private final String TOKEN_PREFIX;
    private final Long EXPIRATION_LENGTH;

    @Autowired
    UserDetailsService userDetailsService;

    public JwtUtility(@Value("${security.jwt.token.secret-key}")String secretKey,
                      @Value("${security.jwt.token.token-prefix}") String tokenPrefix,
                      @Value("${security.jwt.token.expiration-length}") String expTime) {
        this.SECRET_KEY = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.TOKEN_PREFIX = tokenPrefix;
        this.EXPIRATION_LENGTH = Long.valueOf(expTime);
    }

    public String buildToken(Authentication auth) {
        String id = UUID.randomUUID().toString().replace("-", "");
        Claims claims = Jwts.claims().setSubject(auth.getName());
        claims.put("roles", auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        String token = Jwts.builder()
                .setId(id)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_LENGTH))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        token = TOKEN_PREFIX + token;
        return token;
    }

    public Jws<Claims> getClaims(String authToken){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(authToken);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String authHeader) {
        String token = authHeader.replace(TOKEN_PREFIX, "");
        String username = getClaims(token).getBody().getSubject();
        UserDetails user = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
    }
}

