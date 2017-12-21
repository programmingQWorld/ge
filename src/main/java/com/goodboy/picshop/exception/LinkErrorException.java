package com.goodboy.picshop.exception;

public class LinkErrorException extends RuntimeException {
    public  LinkErrorException(String message) {
        super(message);
    }

    public  LinkErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
