package com.bankofben.bankapplication;

public class InvalidLoginException extends Exception {

	/**
	 * InvalidLoginException is thrown when an attempted login fails
	 */
	private static final long serialVersionUID = 425827902669525905L;

	public InvalidLoginException() {
		super();
	}

	public InvalidLoginException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidLoginException(String message) {
		super(message);
	}

	public InvalidLoginException(Throwable cause) {
		super(cause);
	}

}
