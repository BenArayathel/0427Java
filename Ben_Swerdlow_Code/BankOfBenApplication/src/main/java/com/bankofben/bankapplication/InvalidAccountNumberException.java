package com.bankofben.bankapplication;

public class InvalidAccountNumberException extends Exception {

	/**
	 * AccountNumberException occurs when the supplied account number is not a 10-digit positive number.
	 */
	private static final long serialVersionUID = -8062851892301348451L;

	public InvalidAccountNumberException() {
		super();
	}

	public InvalidAccountNumberException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidAccountNumberException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidAccountNumberException(String message) {
		super(message);
	}

	public InvalidAccountNumberException(Throwable cause) {
		super(cause);
	}

}
