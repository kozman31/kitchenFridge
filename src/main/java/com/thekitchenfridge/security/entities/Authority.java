package com.thekitchenfridge.security.entities;

import com.thekitchenfridge.users.entity.User;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="AUTHORITIES")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements GrantedAuthority {

    @Id
    @EqualsAndHashCode.Exclude
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name="AUTHORITY_ID")
    private Long authorityId;

    @Column(name="AUTHORITY_NAME")
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }


}
