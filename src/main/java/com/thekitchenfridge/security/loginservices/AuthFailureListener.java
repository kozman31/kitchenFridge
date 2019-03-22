package com.thekitchenfridge.security.loginservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authFailBadCredsEvent) {
        Authentication failedAuthentication = authFailBadCredsEvent.getAuthentication();
        loginAttemptService.loginFail(failedAuthentication);
    }
}
