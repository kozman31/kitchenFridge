package com.thekitchenfridge.security.entity;

import lombok.*;

public interface UserProfile {
    String username = null;
    String password = null;
    void setUsername(String username);
    String getUsername();
    void setPassword(String password);
    String getPassword();
}
