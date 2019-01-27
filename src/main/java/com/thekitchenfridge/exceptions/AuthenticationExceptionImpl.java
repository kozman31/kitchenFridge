package com.thekitchenfridge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Request Empty")
public class AuthenticationExceptionImpl extends RuntimeException {

   /* public AuthenticationExceptionImpl(String msg) {
        super(msg);
    }
    public AuthenticationExceptionImpl() {
    }*/
}
