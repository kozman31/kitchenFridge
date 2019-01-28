package com.thekitchenfridge.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thekitchenfridge.security.entity.User;
import com.thekitchenfridge.security.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.thekitchenfridge.security.JwtSecureFields.SECRET_KEY;
import static com.thekitchenfridge.security.JwtSecureFields.TOKEN_PREFIX;

public class JwtTokenFilter extends BasicAuthenticationFilter {

    public JwtTokenFilter(AuthenticationManager authManager){
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse rsp, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = req.getHeader(HttpHeaders.AUTHORIZATION);

        if(authHeader!= null && authHeader.startsWith(TOKEN_PREFIX)) {
            authHeader = authHeader.replace(TOKEN_PREFIX, "");
            if(!tokenExpired(authHeader)){
                UsernamePasswordAuthenticationToken authToken = getAuthentication(authHeader);
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(req,rsp);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        String username = getClaims(token).getBody().getSubject();
        ArrayList<GrantedAuthority> roles = (ArrayList<GrantedAuthority>) getClaims(token).getBody().get("roles");
        return new UsernamePasswordAuthenticationToken(username, null, roles);
    }

    private boolean tokenExpired(String authHeader) {
        return getClaims(authHeader)
                    .getBody()
                    .getExpiration().before(new Date());
    }

    private Jws<Claims> getClaims(String authToken){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(authToken);
    }
}
