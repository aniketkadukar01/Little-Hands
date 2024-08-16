package com.app.custom_exception;

@SuppressWarnings("serial")
public class SlotAlreadyBookedException extends RuntimeException {

	public SlotAlreadyBookedException(String message) {
		super(message);
	}
}
