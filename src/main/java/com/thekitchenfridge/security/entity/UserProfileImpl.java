package com.thekitchenfridge.security.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileImpl implements UserProfile{

    private String username;
    private String password;
    private List<GrantedAuthority> roles;

}
