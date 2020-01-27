package com.thekitchenfridge.security.entities;

import com.thekitchenfridge.audit.Auditor;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="ROLES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Role extends Auditor<String> implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Exclude
    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "ROLE_NAME")
    @UniqueElements
    private String roleName;

    @Column(name = "DEFAULT_ROLE")
    private Boolean defaultRole;

    @ManyToMany(fetch=FetchType.EAGER)
    @Builder.Default
    private Set<Authority> authorities = new HashSet<>();

    @Override
    public String getAuthority() {
        return roleName;
    }

    public Set<Authority> getAuthorities(){
        authorities.add(Authority.builder()
                .name(roleName)
                .authorityId(roleId)
                .build());
        return authorities;
    }

}
