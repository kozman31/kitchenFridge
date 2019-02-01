package com.thekitchenfridge.users.entity;

import com.thekitchenfridge.security.Authority;
import com.thekitchenfridge.security.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface UserProfile {
    String username = null;
    String password = null;
    Role role = null;

    void setUsername(String username);
    String getUsername();
    void setPassword(String password);
    String getPassword();
    Collection<? extends GrantedAuthority> getAuthorities();
    Role getRole();
}
