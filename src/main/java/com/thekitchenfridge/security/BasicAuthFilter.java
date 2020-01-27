package com.thekitchenfridge.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.thekitchenfridge.dto.UserProfileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

@Slf4j
public class BasicAuthFilter extends UsernamePasswordAuthenticationFilter {

    private JwtUtility jwt;
    private AuthenticationManager authManager;
    public BasicAuthFilter(AuthenticationManager authManager, JwtUtility jwt){
        this.authManager = authManager;
        this.jwt = jwt;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try{
            UserProfileDto user = new ObjectMapper().readValue(req.getInputStream(), UserProfileDto.class);
            return authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword(),
                        new ArrayList<>()
                )
            );
        }catch(IOException e){
            log.error("IOException Empty Request read error " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse rsp, FilterChain filterChain, Authentication auth) throws IOException, ServletException {
        String token = jwt.buildToken(auth);
        rsp.addHeader("Authorization", token);
        filterChain.doFilter(req,rsp);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse rsp, AuthenticationException authFailed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        log.warn("Authentication Failed: " + authFailed.getMessage());
        rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
