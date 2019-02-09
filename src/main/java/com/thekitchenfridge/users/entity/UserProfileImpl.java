package com.thekitchenfridge.users.entity;

import com.thekitchenfridge.security.entities.Authority;
import com.thekitchenfridge.security.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileImpl implements UserProfile, Serializable {

    private String username;
    private String password;
    private Role role;


    public Set<Authority> getAuthorities(){
        return role.getAuthorities();
    }

    @Override
    public Role getRole() {
        return role;
    }

}
