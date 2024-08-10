package com.app.custom_exception;

@SuppressWarnings("serial")
public class AccessDeniedException extends RuntimeException {

	public AccessDeniedException(String message) {
		super(message);
	}
}
