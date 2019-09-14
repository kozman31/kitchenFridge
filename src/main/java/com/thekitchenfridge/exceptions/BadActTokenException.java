package com.thekitchenfridge.exceptions;

public class BadActTokenException extends Exception {
    public BadActTokenException(){
        super("Invalid Registration Token");
    }
    public BadActTokenException(String msg){
        super(msg);
    }
}
