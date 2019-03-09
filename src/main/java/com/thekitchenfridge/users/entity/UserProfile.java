package com.thekitchenfridge.users.entity;

import com.thekitchenfridge.audit.Auditor;
import com.thekitchenfridge.security.entities.Authority;
import com.thekitchenfridge.security.entities.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class UserProfile extends Auditor<String> {
    String username;
    @ToString.Exclude
    String password;
    @ToString.Exclude
    Role role;
    String firstName;
    String lastName;
    String location;

    public Collection<? extends GrantedAuthority> getAuthorities(){
        return role.getAuthorities();
    }
}
