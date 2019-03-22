package com.thekitchenfridge.exceptions;

public class AuthenticationExceptionImpl extends RuntimeException {

    public AuthenticationExceptionImpl(String msg) {
        super(msg);
    }
}
