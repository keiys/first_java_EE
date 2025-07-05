package com.digi.exceptions;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
