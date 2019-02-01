package com.thekitchenfridge.users.entity;

import com.thekitchenfridge.security.Authority;
import com.thekitchenfridge.security.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileImpl implements UserProfile{

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
