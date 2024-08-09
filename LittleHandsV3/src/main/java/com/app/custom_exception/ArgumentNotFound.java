package com.app.custom_exception;

@SuppressWarnings("serial")
public class ArgumentNotFound extends RuntimeException {
	
	public ArgumentNotFound(String message) {
		super(message);
	}
}
