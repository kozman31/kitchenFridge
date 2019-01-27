package com.thekitchenfridge.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value ={JwtAuthException.class})
    public ResponseEntity<String> invalidJwt(JwtAuthException e){
        log.debug("Invalid JWT "+e.getMessage());

        return handleException(e, "Error: "+e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {UserExistsException.class})
    public ResponseEntity<String> userAlreadyRegistered(UserExistsException e){
        log.debug("UserRegistered "+e.getMessage());
        return handleException(e, "Error: "+e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {AuthenticationExceptionImpl.class})
    public ResponseEntity<String> authenticationFailed(AuthenticationException e){
        log.debug("AuthException "+e.getMessage());
        return handleException(e, "Error: "+e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {TransactionException.class})
    public ResponseEntity<String> authenticationFailed(TransactionException e){
        log.debug("Transaction Error "+e.getMessage());
        return handleException(e, "Transaction Error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {IOException.class})
    public ResponseEntity<String> IOException(IOException e){
        log.debug("IOException " + e.getMessage());
        return handleException(e, "Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> handleException(Exception e, String body, HttpStatus httpStatus){

        return new ResponseEntity<>(body, httpStatus);
    }
}
