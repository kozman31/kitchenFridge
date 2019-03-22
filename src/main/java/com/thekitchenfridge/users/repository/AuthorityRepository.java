package com.thekitchenfridge.users.repository;

import com.thekitchenfridge.security.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository <Authority, Long> {

}
