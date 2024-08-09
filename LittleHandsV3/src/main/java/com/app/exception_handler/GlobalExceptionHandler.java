package com.app.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.custom_exception.ArgumentNotFound;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ArgumentNotFound.class)
	public ResponseEntity<?> handleArgumentNotFound(ArgumentNotFound ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ex.getMessage());
	}
	
}
