package com.thekitchenfridge.exceptions;

import javax.persistence.EntityExistsException;

public class UserExistsException extends EntityExistsException {
    public UserExistsException(String message){
        super(message);
    }
}
