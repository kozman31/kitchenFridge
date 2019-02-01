package com.thekitchenfridge.security;

import com.thekitchenfridge.users.entity.User;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name="ROLES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Exclude
    @Column(name = "ROLE_ID")
    private Long id;

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
