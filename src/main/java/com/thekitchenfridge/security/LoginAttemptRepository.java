package com.thekitchenfridge.security;

import com.thekitchenfridge.security.loginservices.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {

    public Optional<LoginAttempt> findByUsername(String username);
}
