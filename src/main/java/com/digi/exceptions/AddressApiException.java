package com.digi.exceptions;

public class AddressApiException extends RuntimeException {
    public AddressApiException(String message) {
        super(message);
    }

    public AddressApiException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
