package com.thekitchenfridge.users.entity;

import com.thekitchenfridge.security.entities.Authority;
import com.thekitchenfridge.security.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor()
@NoArgsConstructor
public class UserProfileImpl extends UserProfile implements Serializable {

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
