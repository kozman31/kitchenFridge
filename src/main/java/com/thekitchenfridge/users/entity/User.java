package com.thekitchenfridge.users.entity;

import com.thekitchenfridge.audit.Auditor;
import com.thekitchenfridge.audit.UserHistoryListener;
import com.thekitchenfridge.recipes.entities.Recipe;
import com.thekitchenfridge.security.entities.LoginAttempt;
import com.thekitchenfridge.security.entities.Role;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="USERS")
@Data
@ToString(callSuper=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@EntityListeners(UserHistoryListener.class)
public class User extends Auditor<String> implements UserDetails {

    @Id
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique=true)
    private String username;

    @ToString.Exclude
    @NotNull
    private String password;

    @ToString.Exclude
    @ManyToOne(fetch=FetchType.EAGER)
    private Role role;

    @Builder.Default
    private Boolean isLocked = false;

    @Builder.Default
    private Boolean isEnabled = false;

    private String firstName;
    private String lastName;
    private String location;

    @OneToMany
    @JoinColumn(name="fk_user_id")
    private List<Recipe> recipeList;

    @Column(unique=true)
    private String email;

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
        return isEnabled;
    }

    public void lockAccount(){
        this.isLocked = true;
    }

    public void unlockAccount(Boolean isLocked){
        this.isLocked = false;
    }

    public void activate() {
        isEnabled = true;
    }

    public void deactivate() {
        isEnabled = false;
    }

}
