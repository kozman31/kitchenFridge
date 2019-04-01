package com.thekitchenfridge.email;


import com.thekitchenfridge.users.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(final Email email, Boolean isHtml){
        MimeMessage mimeMail = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMsgHlp = new MimeMessageHelper(mimeMail);
        try {
            mimeMsgHlp.addTo(email.getSender());
            mimeMsgHlp.setTo(email.getRecipient());
            mimeMsgHlp.setSubject(email.getMessageSubject());
            mimeMsgHlp.setText(email.getMessageBody(), isHtml);
            javaMailSender.send(mimeMail);
        }catch (MessagingException e){
            log.warn("Messaging error: "+ e.getMessage());
        }
    }

    public void confirmNewUser(final String id, User user){
//        String emailBody = StringFormat
        Email confirmationEmail = Email.builder()
                .id(id)
                .recipient(user.getEmail())
                .sender("admin@kitchenFridge.com")
                .messageSubject("Thank you for registering with us!")
                .build();
        sendMail(confirmationEmail, true);
    }
}
