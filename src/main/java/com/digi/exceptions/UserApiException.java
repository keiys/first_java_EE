package com.digi.exceptions;

public class UserApiException extends RuntimeException {

    public UserApiException(String errorMessage) {
        super(errorMessage);
    }
}
