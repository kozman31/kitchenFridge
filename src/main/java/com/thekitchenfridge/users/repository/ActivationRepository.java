package com.thekitchenfridge.users.repository;

import com.thekitchenfridge.security.entities.ActivationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivationRepository extends JpaRepository<ActivationToken, Long> {
    Optional<ActivationToken> findByTokenId(String tokenId);
}
