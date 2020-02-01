package com.thekitchenfridge.users.repository;

import com.thekitchenfridge.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends  JpaRepository <Role, Long> {
    Optional<Role> findRoleByRoleName(String name);

    Optional<Role> findRoleByRoleId(Long roleId);

    @Query(value = "SELECT * from roles where default_role = true", nativeQuery = true)
    Role getDefaultRole();
}
