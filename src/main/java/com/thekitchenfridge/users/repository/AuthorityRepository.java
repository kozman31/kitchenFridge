package com.thekitchenfridge.users.repository;

import com.thekitchenfridge.security.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

public interface AuthorityRepository extends JpaRepository <Authority, Long> {

}
