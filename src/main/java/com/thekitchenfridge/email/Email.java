package com.thekitchenfridge.email;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Email {
    private String sender;
    private String recipient;
    private String messageSubject;
    private String messageBody;
    private String id;

}
