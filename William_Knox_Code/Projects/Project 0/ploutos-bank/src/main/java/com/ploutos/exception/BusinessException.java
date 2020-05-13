package com.ploutos.exception;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 3489767106912387468L;
	
	public BusinessException() {
		super();
	}
	
	public BusinessException(final String message) {
		super(message);
	}
}
