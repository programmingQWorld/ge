package com.goodboy.picshop.exception;

public class CommodityAddException extends RuntimeException {
    public CommodityAddException(String message) {
        super(message);
    }

    public CommodityAddException(String message, Throwable cause) {
        super(message, cause);
    }
}
