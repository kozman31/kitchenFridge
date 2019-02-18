package com.thekitchenfridge.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(getCurrentUser());
    }

    private String getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
