package com.thekitchenfridge.users.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileImpl implements UserProfile{

    private String username;
    private String password;
    private List<String> roles;

    public List<String> getRoles(){
        return roles;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(){
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

}
