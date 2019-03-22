package com.thekitchenfridge.users.entity;

import com.thekitchenfridge.audit.UserHistoryListener;
import com.thekitchenfridge.security.entities.LoginAttempt;
import com.thekitchenfridge.security.entities.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name="USERS")
@Data
@ToString(callSuper=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(UserHistoryListener.class)
public class User  extends UserProfile implements UserDetails {

    @Id
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username;

    @ToString.Exclude
    @NotNull
    private String password;

    @ToString.Exclude
    @ManyToOne(fetch=FetchType.EAGER)
    private Role role;

    @Builder.Default
    private Boolean isLocked = false;

    @OneToOne
    @ToString.Exclude
    private LoginAttempt lastLoginAttempt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void lockAccount(Boolean isLocked){
        this.isLocked = isLocked;
    }

}
