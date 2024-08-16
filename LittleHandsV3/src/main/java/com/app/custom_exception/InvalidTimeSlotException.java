package com.app.custom_exception;

@SuppressWarnings("serial")
public class InvalidTimeSlotException extends RuntimeException {

	public InvalidTimeSlotException(String message) {
		super(message);
	}
}
