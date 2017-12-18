package com.goodboy.picshop.exception;

public class UserErrorException extends RuntimeException{
    public UserErrorException(String message) {
        super(message);
    }

    public UserErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
