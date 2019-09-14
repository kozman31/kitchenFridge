package com.thekitchenfridge.security.activation;

import com.thekitchenfridge.users.entity.User;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Data
public class SendActivationEvent extends ApplicationEvent {

    private User user;
    private String appUrl;
    private Locale locale;

    public SendActivationEvent(User user, Locale locale, String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
