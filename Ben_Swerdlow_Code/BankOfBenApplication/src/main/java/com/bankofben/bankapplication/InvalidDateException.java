package com.bankofben.bankapplication;

public class InvalidDateException extends Exception {

	/**
	 * InvalidDateException is thrown when the supplied date is not in the valid format.
	 */
	private static final long serialVersionUID = -7780502436900272880L;

	public InvalidDateException() {
		super();
	}

	public InvalidDateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidDateException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDateException(String message) {
		super(message);
	}

	public InvalidDateException(Throwable cause) {
		super(cause);
	}

}
