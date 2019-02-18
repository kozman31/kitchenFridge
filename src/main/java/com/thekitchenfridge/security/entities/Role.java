package com.thekitchenfridge.security.entities;

import com.thekitchenfridge.audit.Auditor;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name="ROLES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Auditor<String> implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(name = "ROLE_ID")
    private Integer roleId;

    @Column(name = "ROLE_NAME")
    private String name;

    @ManyToMany(fetch=FetchType.EAGER)
    @Builder.Default
    private Set<Authority> authorities = new HashSet<>();

    @Override
    public String getAuthority() {
        return name;
    }

    public Set<Authority> getAuthorities(){
        return authorities;
    }

}
