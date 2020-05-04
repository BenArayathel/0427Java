package com.bankofben.bankapplication;

public class InvalidSsnException extends Exception {

	/**
	 * InvalidSsnException is thrown when the supplied social security number is invalid.
	 */
	private static final long serialVersionUID = -7821053994097491843L;

	public InvalidSsnException() {
		super();
	}

	public InvalidSsnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidSsnException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidSsnException(String message) {
		super(message);
	}

	public InvalidSsnException(Throwable cause) {
		super(cause);
	}

}
