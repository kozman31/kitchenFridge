package com.thekitchenfridge.security;

import com.thekitchenfridge.users.entity.User;
import com.thekitchenfridge.users.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.thekitchenfridge.security.JwtSecureFields.SECRET_KEY;
import static com.thekitchenfridge.security.JwtSecureFields.TOKEN_PREFIX;

public class JwtTokenFilter extends BasicAuthenticationFilter {

    private UserDetailsServiceImpl userDetailsService;

    public JwtTokenFilter(AuthenticationManager authManager, UserDetailsServiceImpl userDetailsService){
        super(authManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse rsp, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = req.getHeader(HttpHeaders.AUTHORIZATION);

        if(authHeader!= null && authHeader.startsWith(TOKEN_PREFIX)) {
            authHeader = authHeader.replace(TOKEN_PREFIX, "");
                UsernamePasswordAuthenticationToken authToken = getAuthentication(authHeader);
                SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(req,rsp);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        String username = getClaims(token).getBody().getSubject();
        User user = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
    }

    private Jws<Claims> getClaims(String authToken){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(authToken);
    }
}
