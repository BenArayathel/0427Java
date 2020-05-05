package com.bankofben.bankapplication;

public class InvalidEmailException extends Exception {

	/**
	 * EmailInvalidException is thrown when the email supplied is not a valid email address.
	 */
	private static final long serialVersionUID = 6987867206840952134L;

	public InvalidEmailException() {
		super();
	}

	public InvalidEmailException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidEmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidEmailException(String message) {
		super(message);
	}

	public InvalidEmailException(Throwable cause) {
		super(cause);
	}
	
	

}
