package com.thekitchenfridge.email;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

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
        messageBody += ACTIVATION_EMAIL+"\n<a href=\"http:\\\\localhost:9090\\activation?t=" + token +
                "\">http:\\\\localhost:9090\\activation?t=" +token+"</a>";
        return this;
    }

}
