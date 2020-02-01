package com.thekitchenfridge.users.repository;

import com.thekitchenfridge.security.entities.ActivationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivationRepository extends JpaRepository<ActivationToken, Long> {
    Optional<ActivationToken> findByTokenId(String tokenId);
}
