package com.bankofben.bankapplication;

public class UserNotFoundException extends Exception {

	/**
	 * UserNotFoundException is thrown when a user cannot be found in BoB's records.
	 */
	private static final long serialVersionUID = -2904497640122894366L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
	}

}
