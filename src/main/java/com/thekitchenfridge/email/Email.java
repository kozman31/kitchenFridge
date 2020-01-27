package com.thekitchenfridge.email;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Email {

    private final String ACTIVATION_EMAIL = "Please click the link below to activate your account.";
    private String sender;
    private String recipient;
    private String messageSubject;
    private String messageBody;
    private String tokenId;

    public Email addActivationMsg(String token){
        messageBody += ACTIVATION_EMAIL+"\n<a href=\"https:\\\\ekoz.dev\\activation?t=" + token +
                "\">http:\\\\ekoz.dev\\activation?t=" +token+"</a>";
        return this;
    }

}
