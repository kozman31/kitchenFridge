package com.thekitchenfridge.security.activation;

import com.thekitchenfridge.users.entity.User;
import com.thekitchenfridge.security.activation.ActivationService.Activation;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class SendActivationEvent extends ApplicationEvent {

    private User user;
    private Activation activationReason;
    public SendActivationEvent(Object object,User user, Activation activationReason) {
        super(object);
        this.user = user;
        this.activationReason = activationReason;
    }

    public User getUser() {
        return user;
    }

    public Activation getActivationReason() {
        return activationReason;
    }

}
