package com.bankofben.bankapplication;

public class InvalidPasswordException extends Exception {

	/**
	 * InvalidPasswordException is thrown when attempting to change or set a password
	 * and the password is invalid (see ValidationTools.isValidPassword())
	 */
	private static final long serialVersionUID = -7664396657070162913L;

	public InvalidPasswordException() {
		super();
	}

	public InvalidPasswordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPasswordException(String message) {
		super(message);
	}

	public InvalidPasswordException(Throwable cause) {
		super(cause);
	}

}
