package com.poc.alerts.validation;


public class InvalidHeaderException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidHeaderException(String message) {
        super(message);
    }
}
