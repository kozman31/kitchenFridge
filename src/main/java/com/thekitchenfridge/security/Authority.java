package com.thekitchenfridge.security;

import com.thekitchenfridge.users.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements GrantedAuthority {

    @Id
    @Column(name="AUTHORITY_ID")
    private Long id;

    @Column(name="AUTHORITY_NAME")
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
