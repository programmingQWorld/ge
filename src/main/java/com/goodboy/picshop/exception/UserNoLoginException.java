package com.goodboy.picshop.exception;

public class UserNoLoginException extends  RuntimeException{
    public UserNoLoginException(String message) {
        super(message);
    }

    public UserNoLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
