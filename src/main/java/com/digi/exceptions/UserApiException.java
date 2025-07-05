package com.digi.exceptions;

public class UserApiException extends RuntimeException {

    public UserApiException(String errorMessage) {
        super(errorMessage);
    }

    public UserApiException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
