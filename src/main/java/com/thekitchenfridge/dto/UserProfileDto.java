package com.thekitchenfridge.dto;

import com.thekitchenfridge.security.entities.Authority;
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
public class UserProfileDto extends BasicUserDto implements Serializable {

    private String password;
    private Long roleId;
    private Set<Authority> authorities;

    public UserProfileDto(String username, String password, Long roleId, String email, String firstName, String lastName) {
        super(username, email, firstName, lastName);
        this.password = password;
        this.roleId = roleId;
    }

    public UserProfileDto(String username, String password, Long roleId, String email){
        this(username, password, roleId, email, null, null);
    }
}
