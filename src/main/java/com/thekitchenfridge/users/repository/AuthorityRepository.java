package com.thekitchenfridge.users.repository;

import com.thekitchenfridge.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository <Authority, Long> {
    List<Authority> getAuthoritiesByName(String name);
}
