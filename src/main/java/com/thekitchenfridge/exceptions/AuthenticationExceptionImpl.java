package com.thekitchenfridge.exceptions;

import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

public class AuthenticationExceptionImpl extends RuntimeException {

    public AuthenticationExceptionImpl(String msg) {
        super(msg);
    }
    public AuthenticationExceptionImpl() {
    }
}
