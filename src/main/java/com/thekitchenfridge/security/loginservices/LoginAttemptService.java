package com.thekitchenfridge.security.loginservices;

import com.thekitchenfridge.security.entities.LoginAttempt;
import com.thekitchenfridge.users.entity.User;
import com.thekitchenfridge.users.repository.LoginAttemptRepository;
import com.thekitchenfridge.users.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Date;

@Service
public class LoginAttemptService {

    @Autowired
    LoginAttemptRepository loginAttemptRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    private final Integer INVALID_LOGIN_LIMIT;

    public LoginAttemptService(@Value("${security.user.invalid-cred-limit}")Integer INVALID_LOGIN_LIMIT) {
        this.INVALID_LOGIN_LIMIT = INVALID_LOGIN_LIMIT;
    }

    public void loginFail(Authentication failedAuthentication) {
        String username = failedAuthentication.getName();

        if(userDetailsServiceImpl.userExists(username)){

            String sessionIp = getSessionIp();
            User user = userDetailsServiceImpl.loadUserByUsername(username);

            LoginAttempt loginAttempt = loginAttemptRepository.findByUsername(username).orElse(firstLogin(user));

            loginAttempt.setSessionIp(sessionIp);
            loginAttempt.invalidLoginAttempted();
            loginAttemptRepository.save(loginAttempt);

            if(INVALID_LOGIN_LIMIT < loginAttempt.getInvalidLoginCount()){
                user.lockAccount();
                userDetailsServiceImpl.updateUserDetails(user);
            }

        }
    }

    public void loginSuccess(Authentication succcussfulAuth){

        String username = succcussfulAuth.getName();
        String sessionIp = getSessionIp();
        User user = userDetailsServiceImpl.loadUserByUsername(username);

        LoginAttempt loginAttempt = loginAttemptRepository.findByUsername(username).orElse(firstLogin(user));

        loginAttempt.setSessionIp(sessionIp);
        loginAttempt.resetLoginAttemptCount();
        Date lastValidLogin = Date.from(new Date().toInstant());
        loginAttempt.setLastValidLogin(lastValidLogin);
        loginAttempt = loginAttemptRepository.save(loginAttempt);

        if(user.getLastLoginAttempt()==null) {
            user.setLastLoginAttempt(loginAttempt);
            userDetailsServiceImpl.updateUserDetails(user);
        }
    }

    private String getSessionIp(){
        String sessionIp = httpServletRequest.getRemoteAddr();
        if("0:0:0:0:0:0:0:1".equals(sessionIp)){
            try{
                sessionIp = InetAddress.getLocalHost().getHostAddress();
            }catch(Exception e){
                System.out.println("error" + e.getMessage());
            }
        }
        return sessionIp;
    }

    private LoginAttempt firstLogin(User user){
        LoginAttempt firstLogin = new LoginAttempt(user);
        return firstLogin;
    }
}
