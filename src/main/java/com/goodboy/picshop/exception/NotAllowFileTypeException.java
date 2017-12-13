package com.goodboy.picshop.exception;

public class NotAllowFileTypeException extends RuntimeException {
    public NotAllowFileTypeException(String message) {
        super(message);
    }

    public NotAllowFileTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
