package com.thekitchenfridge.security.entities;

import com.thekitchenfridge.audit.Auditor;
import com.thekitchenfridge.audit.LoginAttemptHistoryListener;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Data
@NoArgsConstructor
@EntityListeners({LoginAttemptHistoryListener.class, AuditingEntityListener.class})
public class LoginAttempt extends Auditor<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date createdDate;

    private Integer invalidLoginCount = 0;
    private String sessionIp;

    private Date lastValidLogin;


    public void invalidLoginAttempted(){
        invalidLoginCount++;
    }

    public void resetLoginAttemptCount(){
        invalidLoginCount = 0;
    }

    public LoginAttempt(String username, String sessionIp){
        this.username = username;
        this.sessionIp = sessionIp;
    }

    @OneToMany
    @Column(name="FK_login_attempt_hist")
    List<LoginAttemptHistory> loginAttemptHistoryList;
}
