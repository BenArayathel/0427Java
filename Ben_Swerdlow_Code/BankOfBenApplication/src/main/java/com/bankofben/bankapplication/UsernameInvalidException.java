package com.bankofben.bankapplication;

public class UsernameInvalidException extends Exception {

	/**
	 * UsernameInvalidException is thrown when the supplied username is invalid (must be between 4 and 20 characters)
	 */
	private static final long serialVersionUID = -4819641249338993064L;

	public UsernameInvalidException() {
		super();
	}

	public UsernameInvalidException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsernameInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsernameInvalidException(String message) {
		super(message);
	}

	public UsernameInvalidException(Throwable cause) {
		super(cause);
	}

}
