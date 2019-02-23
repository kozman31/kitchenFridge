package com.thekitchenfridge.users.service;

import com.thekitchenfridge.security.entities.Authority;
import com.thekitchenfridge.security.entities.Role;
import com.thekitchenfridge.users.entity.UserProfileImpl;
import com.thekitchenfridge.users.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthorityService authorityService;

    public Role generateRole(UserProfileImpl userProfile){
        Role profileRole = userProfile.getRole();
        Role role = findRolesByRoleId(profileRole.getRoleId()).stream().filter(
                singleRole -> profileRole.equals(singleRole)).findFirst()
                .orElse(Role.builder().name(profileRole.getAuthority()).roleId(profileRole.getRoleId()).build());

        if(!role.getAuthorities().equals(profileRole)) {
            Set<Authority> activeAuthorities = authorityService.generateAuthoritySet(userProfile.getAuthorities());
            role.setAuthorities(activeAuthorities);
            role = saveRole(role);
        }
        return role;
    }

    public List<Role> findRolesByName(String name){
        return roleRepository.findRolesByName(name);
    }

    public List<Role> findRolesByRoleId(Long roleId){
        return roleRepository.findRolesByRoleId(roleId);
    }
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    public void saveRoles(List<Role> role){
        roleRepository.saveAll(role);
    }
}
