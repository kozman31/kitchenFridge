package com.thekitchenfridge.security.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class LoginAttemptHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    Date createdDate;

    @ManyToOne
    @JoinColumn(name = "login_username", referencedColumnName = "username")
    private LoginAttempt loginAttempt;

    @Setter(AccessLevel.NONE)
    private Integer invalidLoginCount;

    String lastUserLoginContext;

    public LoginAttemptHistory(LoginAttempt loginAttempt) {
        this.loginAttempt = loginAttempt;
        this.lastUserLoginContext = loginAttempt.toString();
        this.invalidLoginCount = loginAttempt.getInvalidLoginCount();
    }
}

