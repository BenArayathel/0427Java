package com.bankofben.bankapplication;

public class InvalidDateOfBirthException extends Exception {

	/**
	 * InvalidDateOfBirthException is thrown when the supplied date of birth is invalid (must be 18 years or older).
	 */
	private static final long serialVersionUID = 4114380502895086566L;

	public InvalidDateOfBirthException() {
		super();
	}

	public InvalidDateOfBirthException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidDateOfBirthException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDateOfBirthException(String message) {
		super(message);
	}

	public InvalidDateOfBirthException(Throwable cause) {
		super(cause);
	}

}
