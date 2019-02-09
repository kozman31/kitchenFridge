package com.thekitchenfridge.users.service;

import com.thekitchenfridge.security.entities.Authority;
import com.thekitchenfridge.security.entities.Role;
import com.thekitchenfridge.users.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findRolesByName(String name){
        return roleRepository.findRolesByName(name);
    }

    public List<Role> findRolesByRoleId(Integer roleId){
        return roleRepository.findRolesByRoleId(roleId);
    }
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    public void saveRoles(List<Role> role){
        roleRepository.saveAll(role);
    }
}
