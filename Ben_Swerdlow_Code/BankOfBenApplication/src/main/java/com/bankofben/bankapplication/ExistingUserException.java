package com.bankofben.bankapplication;

public class ExistingUserException extends Exception {

	/**
	 * ExistingUserException is thrown when there is an existing user in the Bank of Ben.
	 */
	private static final long serialVersionUID = 1592208706339597387L;

	public ExistingUserException() {
		super();
	}

	public ExistingUserException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExistingUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExistingUserException(String message) {
		super(message);
	}

	public ExistingUserException(Throwable cause) {
		super(cause);
	}

}
