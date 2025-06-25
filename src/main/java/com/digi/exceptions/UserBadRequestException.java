package com.digi.exceptions;

public class UserBadRequestException extends RuntimeException{

    public UserBadRequestException(String errorMessage){
        super(errorMessage);
    }
}
