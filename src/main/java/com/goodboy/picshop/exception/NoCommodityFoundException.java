package com.goodboy.picshop.exception;

public class NoCommodityFoundException extends RuntimeException {
    public NoCommodityFoundException(String message) {
        super(message);
    }

    public NoCommodityFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
