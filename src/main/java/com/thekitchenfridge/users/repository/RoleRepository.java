package com.thekitchenfridge.users.repository;

import com.thekitchenfridge.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends  JpaRepository <Role, Long> {
    List<Role> findRolesByName(String name);

    List<Role> findRolesByRoleId(Long roleId);
}
