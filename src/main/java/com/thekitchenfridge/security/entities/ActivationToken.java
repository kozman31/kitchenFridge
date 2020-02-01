package com.thekitchenfridge.security.entities;

import com.thekitchenfridge.users.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class ActivationToken {

    private static final int EXPIRY_IN_MIN = 60*24;

    public ActivationToken(User user, String tokenId){
        expirationDate = createExpiryDate();
        this.user = user;
        this.tokenId = tokenId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name="user_id")
    User user;

    String tokenId;

    @Column(name = "token_expiry")
    Date expirationDate;

    private Date createExpiryDate(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, EXPIRY_IN_MIN);
        return new Date(cal.getTime().getTime());
    }

    public User activateUser() {
        user.activate();
        return user;
    }
}
