package com.goodboy.picshop.exception;

/**
 * 标签重复异常
 */
public class TagRepeatException extends RuntimeException{
    public TagRepeatException(String message) {
        super(message);
    }

    public TagRepeatException(String message, Throwable cause) {
        super(message, cause);
    }
}
