package com.thekitchenfridge.exceptions;

import javax.persistence.EntityExistsException;

public class RoleAlreadyExistsException extends EntityExistsException {
    public RoleAlreadyExistsException(String message){
        super(message);
    }
}
