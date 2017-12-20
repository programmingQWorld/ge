package com.goodboy.picshop.exception;

public class NoTagFoundException extends RuntimeException {
    public NoTagFoundException(String message) {
        super(message);
    }

    public NoTagFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
