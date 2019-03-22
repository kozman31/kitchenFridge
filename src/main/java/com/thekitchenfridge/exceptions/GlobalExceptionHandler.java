package com.thekitchenfridge.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value ={JwtAuthException.class})
    public ResponseEntity<String> invalidJwtHandler(JwtAuthException e){
        log.debug("Invalid JWT "+e.getMessage());
        return handleException(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {UserExistsException.class})
    public ResponseEntity<String> userAlreadyRegisteredHandler(UserExistsException e){
        log.debug("UserRegistered "+e.getMessage());
        return handleException(e.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(value = {AuthenticationExceptionImpl.class})
    public ResponseEntity<String> authenticationFailedHandler(AuthenticationException e){
        log.debug("AuthException "+e.getMessage());
        return handleException(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {TransactionException.class})
    public ResponseEntity<String> authenticationFailedHandler(TransactionException e){
        log.debug("Transaction Error "+e.getMessage());
        return handleException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {IOException.class})
    public ResponseEntity<String> IOExceptionHandler(IOException e){
        log.debug("IOException " + e.getMessage());
        return handleException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<String> UsernameNotFoundHandler(UsernameNotFoundException e){
        log.debug("UsernameNotFoundException handler " + e.getMessage());
        return handleException(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
    }

    public ResponseEntity<String> handleException(String body, HttpStatus httpStatus){
        return new ResponseEntity<>(body, httpStatus);
    }
}
