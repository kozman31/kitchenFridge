package com.thekitchenfridge.security.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface UserProfile {
    String username = null;
    String password = null;
    List<String> roles = null;
    void setUsername(String username);
    String getUsername();
    void setPassword(String password);
    String getPassword();
    List<GrantedAuthority> getRoles();
    void setRoles(List<GrantedAuthority> password);
}
