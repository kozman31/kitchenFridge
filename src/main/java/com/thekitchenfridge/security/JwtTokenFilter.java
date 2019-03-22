package com.thekitchenfridge.security;

import com.thekitchenfridge.users.service.UserDetailsServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends BasicAuthenticationFilter {

    private UserDetailsServiceImpl userDetailsService;
    private JwtUtility jwt;

    public JwtTokenFilter(AuthenticationManager authManager, UserDetailsServiceImpl userDetailsService, JwtUtility jwt){
        super(authManager);
        this.userDetailsService = userDetailsService;
        this.jwt = jwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse rsp, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = req.getHeader(HttpHeaders.AUTHORIZATION);

        if(authHeader!= null) {
            UsernamePasswordAuthenticationToken authToken = jwt.getAuthentication(authHeader);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(req,rsp);
    }




}
