package com.thekitchenfridge.users.entity;

import com.thekitchenfridge.audit.Auditor;
import com.thekitchenfridge.security.entities.Authority;
import com.thekitchenfridge.security.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor()
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserProfile extends Auditor<String> implements Serializable {

    private String username;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;
    private String location;
    private String email;


    public Set<Authority> getAuthorities(){
        return role.getAuthorities();
    }

    public Role getRole() {
        return role;
    }

}
