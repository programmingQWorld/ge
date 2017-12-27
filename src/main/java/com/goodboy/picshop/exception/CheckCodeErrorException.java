package com.goodboy.picshop.exception;

public class CheckCodeErrorException extends RuntimeException {
    public CheckCodeErrorException(String message) {
        super(message);
    }

    public CheckCodeErrorException(String message, Throwable cause) {
        super(message, cause);
    }

}