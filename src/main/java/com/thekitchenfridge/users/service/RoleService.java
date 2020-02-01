package com.thekitchenfridge.users.service;

import com.thekitchenfridge.exceptions.RoleAlreadyExistsException;
import com.thekitchenfridge.security.entities.Authority;
import com.thekitchenfridge.security.entities.Role;
import com.thekitchenfridge.users.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthorityService authorityService;

    public Role createRole(Role newRole) throws RoleAlreadyExistsException {
        if (findRoleByName(newRole.getRoleName()).isPresent())
            throw new RoleAlreadyExistsException("Role already exists");

        Set<Authority> authorities = authorityService.generateAuthoritySet(newRole.getAuthorities());
        Role role = Role.builder().roleName(newRole.getRoleName()).authorities(authorities).build();
        return saveRole(role);
    }

    public Role findRolesByRoleId(Long roleId) throws RoleNotFoundException {
        Role role = roleRepository.findRoleByRoleId(roleId).orElseThrow(RoleNotFoundException::new);
        Set<Authority> authorities = authorityService.generateAuthoritySet(role.getAuthorities());
        role.setAuthorities(authorities);
        return role;
    }
    public Optional<Role> findRoleByName(String roleName){
        return roleRepository.findRoleByRoleName(roleName);
    }
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    public Role getDefaultRole(){
        Role role = roleRepository.getDefaultRole();
        Set<Authority> authorities = authorityService.generateAuthoritySet(role.getAuthorities());
        role.setAuthorities(authorities);
        return role;
    }
}
/*
    Roles
        Admin
          -Total Admin
          -General Admin
        User
          -User
            -Fmaily Lead
            -Fmaily
*/

