package com.thekitchenfridge.users.repository;

import com.thekitchenfridge.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends  JpaRepository <Role, Long> {
    Optional<Role> findRoleByRoleName(String name);

    Optional<Role> findRoleByRoleId(Long roleId);

    @Query(value = "SELECT * from Roles where default = true", nativeQuery = true)
    Role getDefaultRole();
}
