package com.goodboy.picshop.exception;

/**
 * 用户不存在异常
 */
public class NoUserException extends RuntimeException {
    public NoUserException(String message) {
        super(message);
    }

    public NoUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
