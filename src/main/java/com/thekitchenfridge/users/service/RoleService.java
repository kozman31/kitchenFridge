package com.thekitchenfridge.users.service;

import com.thekitchenfridge.security.Role;
import com.thekitchenfridge.users.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class RoleService {

    private RoleRepository roleRepository;

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    public void saveRoles(List<Role> role){
        roleRepository.saveAll(role);
    }
}
