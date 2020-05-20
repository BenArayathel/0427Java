package com.exceptions;

public class BusinessException extends Exception {

	public BusinessException() {
		super();
	}
	
	public BusinessException(final String message) {
		super(message);
	}

}

