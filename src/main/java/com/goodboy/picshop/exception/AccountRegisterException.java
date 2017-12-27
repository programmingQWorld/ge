package com.goodboy.picshop.exception;

public class AccountRegisterException extends RuntimeException {
    public AccountRegisterException(String message) {
        super(message);
    }

    public AccountRegisterException(String message, Throwable cause) {
        super(message, cause);
    }

}