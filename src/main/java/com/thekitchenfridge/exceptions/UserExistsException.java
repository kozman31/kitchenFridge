package com.thekitchenfridge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityExistsException;

@ResponseStatus(value= HttpStatus.OK, reason="Error: User Already Exists")
public class UserExistsException extends EntityExistsException {
   /* public UserExistsException(String message){
        super(message);
    }*/
}
