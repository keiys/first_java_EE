package com.digi.exceptions;

public class AlreadyExistException extends RuntimeException {

    public AlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
