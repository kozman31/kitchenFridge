package com.thekitchenfridge.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.thekitchenfridge.exceptions.AuthenticationExceptionImpl;
import com.thekitchenfridge.security.entity.User;
import com.thekitchenfridge.security.entity.UserProfile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;
import static com.thekitchenfridge.security.JwtSecureFields.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
public class BasicAuthFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationManager authManager;
    public BasicAuthFilter(String loginUrl, AuthenticationManager authManager){
        super(loginUrl);
        this.authManager = authManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try{
            UserProfile user = new ObjectMapper().readValue(req.getInputStream(), UserProfile.class);
            System.out.println(user.getUsername());
            return authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>()
                    )
            );
//        }catch(AuthenticationException e){
//            log.error("Authentication error: " + e.getMessage());
//            throw new AuthenticationExceptionImpl(e.getMessage());
        }catch(IOException e){
            log.error("IOException Empty Request read error " + e.getMessage());
            throw new AuthenticationExceptionImpl(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse rsp, FilterChain filterChain, Authentication auth){
        String id = UUID.randomUUID().toString().replace("-", "");
        Claims claims = Jwts.claims().setSubject(auth.getName());
        claims.put("roles", auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        String token = Jwts.builder()
                .setId(id)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_LENGTH))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        rsp.addHeader(AUTHORIZATION, TOKEN_PREFIX + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failedEx) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        log.warn("Authentication Failed: " + failedEx.getMessage());
        throw failedEx;
    }
}