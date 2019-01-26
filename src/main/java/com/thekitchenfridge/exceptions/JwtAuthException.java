package com.thekitchenfridge.exceptions;

import javax.naming.AuthenticationException;
import java.io.IOException;

public class JwtAuthException extends IOException {
    public JwtAuthException(String message){
        super(message);
    }
}
