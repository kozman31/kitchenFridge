package com.thekitchenfridge.security.entities;

import com.thekitchenfridge.audit.Auditor;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="AUTHORITIES")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority extends Auditor<String> implements GrantedAuthority {

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
