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
public class UserProfileDto extends Auditor<String> implements Serializable {

    private String username;
    private String password;
    private Long roleId;
    private Set<Authority> authorities;
    private String firstName;
    private String lastName;
    private String email;

    public UserProfileDto(String username, String password, Long roleId) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }
}
