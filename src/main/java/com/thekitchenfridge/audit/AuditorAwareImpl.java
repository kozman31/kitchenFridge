package com.thekitchenfridge.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    HttpServletRequest httpServletResponse;
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(getCurrentUser());
    }

    private String getCurrentUser(){
        if(SecurityContextHolder.getContext().getAuthentication() != null)
            return SecurityContextHolder.getContext().getAuthentication().getName();
        return "System";
    }
}
