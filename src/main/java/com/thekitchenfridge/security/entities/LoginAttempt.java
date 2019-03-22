package com.thekitchenfridge.security.entities;

import com.thekitchenfridge.audit.Auditor;
import com.thekitchenfridge.audit.LoginAttemptHistoryListener;
import com.thekitchenfridge.users.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@EntityListeners({LoginAttemptHistoryListener.class, AuditingEntityListener.class})
public class LoginAttempt extends Auditor<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @ToString.Exclude
    @JoinColumn(name="user_id", referencedColumnName = "id")
    User user;

    String username;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    private Integer invalidLoginCount = 0;
    private String sessionIp;

    private Date lastValidLogin;


    public void invalidLoginAttempted(){
        invalidLoginCount++;
    }

    public void resetLoginAttemptCount(){
        invalidLoginCount = 0;
    }

    public LoginAttempt(User user, String sessionIp){
        this.user = user;
        this.username = user.getUsername();
        this.sessionIp = sessionIp;
    }
    public LoginAttempt(User user){
        this.user = user;
        this.username = user.getUsername();
    }
}
