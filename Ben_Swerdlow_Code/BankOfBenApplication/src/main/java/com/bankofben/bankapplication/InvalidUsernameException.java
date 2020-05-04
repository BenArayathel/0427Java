package com.bankofben.bankapplication;

public class InvalidUsernameException extends Exception {

	/**
	 * Thrown when username is invalid (must be between 4 and 20 characters)
	 */
	private static final long serialVersionUID = -5332223767799315453L;

	public InvalidUsernameException() {
		super();
	}

	public InvalidUsernameException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidUsernameException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidUsernameException(String message) {
		super(message);
	}

	public InvalidUsernameException(Throwable cause) {
		super(cause);
	}
	
	

}
