package com.thekitchenfridge.security.activation;

import com.thekitchenfridge.exceptions.BadActTokenException;
import com.thekitchenfridge.security.entities.ActivationToken;
import com.thekitchenfridge.users.entity.User;
import com.thekitchenfridge.users.repository.ActivationRepository;
import com.thekitchenfridge.users.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Data
@Service
public class ActivationService implements ApplicationEventPublisherAware {

    public enum Activation{NEW_USER, PASSWORD_RESET}

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivationRepository activationRepo;

    public Boolean activateUser(String tokenStr)throws BadActTokenException {
        ActivationToken token = activationRepo.findByTokenId(tokenStr).orElseThrow(BadActTokenException::new);
        if(!tokenExpired(token)){
            userRepository.save(token.activateUser());
            return true;
        }
        return false;
    }

    public boolean tokenExpired(ActivationToken token){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        return new Date(cal.getTime().getTime()).after(token.getExpirationDate());
    }

    public void userActivationEventTrigger(User user, Activation activationReason){
        SendActivationEvent customSpringEvent = new SendActivationEvent(this,user, activationReason);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }

}
