package com.goodboy.picshop.exception;

public class NoBuyerOrderException extends RuntimeException {
	public NoBuyerOrderException(String message) {
		super(message);
	}

	public NoBuyerOrderException(String message, Throwable cause) {
		super(message, cause);
	}
}
