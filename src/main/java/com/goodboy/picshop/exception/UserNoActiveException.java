package com.goodboy.picshop.exception;

public class UserNoActiveException extends RuntimeException{
    public UserNoActiveException(String message) {
        super(message);
    }

    public UserNoActiveException(String message, Throwable cause) {
        super(message, cause);
    }
}