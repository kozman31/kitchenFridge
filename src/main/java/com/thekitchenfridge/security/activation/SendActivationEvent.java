package com.thekitchenfridge.security.activation;

import com.thekitchenfridge.users.entity.User;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
