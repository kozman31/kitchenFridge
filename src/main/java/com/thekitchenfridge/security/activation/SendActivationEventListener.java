package com.thekitchenfridge.security.activation;

import com.thekitchenfridge.email.EmailService;
import com.thekitchenfridge.security.entities.ActivationToken;
import com.thekitchenfridge.users.entity.User;
import com.thekitchenfridge.users.repository.ActivationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.thekitchenfridge.security.activation.ActivationService.Activation.NEW_USER;

@Component
public class SendActivationEventListener implements ApplicationListener<SendActivationEvent> {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ActivationRepository activationRepo;

    @Override
    public void onApplicationEvent(SendActivationEvent event) {

        if(NEW_USER.equals(event.getActivationReason()))
            sendNewUserActivationEmail(event);
    }

    private void sendNewUserActivationEmail(SendActivationEvent event){
        User user = event.getUser();
        String tokenId = UUID.randomUUID().toString();
        ActivationToken token = activationRepo.save(new ActivationToken(user, tokenId));

        emailService.activationEmail(user, token.getTokenId());
    }

}
