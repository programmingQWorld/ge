package com.goodboy.picshop.exception;

public class CommodityRepeatException extends RuntimeException {
    public CommodityRepeatException(String message) {
        super(message);
    }

    public CommodityRepeatException(String message, Throwable cause) {
        super(message, cause);
    }
}
