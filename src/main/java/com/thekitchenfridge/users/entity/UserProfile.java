package com.thekitchenfridge.users.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;


public interface UserProfile {
    String username = null;
    String password = null;
    List<String> roles = null;

    void setUsername(String username);
    String getUsername();
    void setPassword(String password);
    String getPassword();
    Collection<? extends GrantedAuthority> getAuthorities();
    List<String> getRoles();
}
