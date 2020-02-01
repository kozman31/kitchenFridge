package com.thekitchenfridge.users.repository;

import com.thekitchenfridge.security.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository <Authority, Long> {

}
