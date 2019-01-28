package com.thekitchenfridge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

public class AuthenticationExceptionImpl extends RuntimeException {

    public AuthenticationExceptionImpl(String msg) {
        super(msg);
    }
}
