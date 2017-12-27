package com.goodboy.picshop.exception;

public class EmailRegisterException extends RuntimeException {
    public EmailRegisterException(String message) {
        super(message);
    }

    public EmailRegisterException(String message, Throwable cause) {
        super(message, cause);
    }

}