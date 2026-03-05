package com.poc.alerts.exception;

public class UnrecognisedEventTypeException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnrecognisedEventTypeException(String message) {
        super(message);
    }
}