package com.bankofben.bankapplication;

public class InvalidAmountException extends Exception {

	/**
	 * InvalidAmountException is thrown when amount is negative or contains
	 * fractions of a cent
	 */
	private static final long serialVersionUID = -6583109130205165143L;

	public InvalidAmountException() {
		super();
	}

	public InvalidAmountException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidAmountException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidAmountException(String message) {
		super(message);
	}

	public InvalidAmountException(Throwable cause) {
		super(cause);
	}
	

}
