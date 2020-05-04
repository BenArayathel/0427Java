package com.bankofben.bankapplication;

public class EmailInvalidException extends Exception {

	/**
	 * EmailInvalidException is thrown when the email supplied is not a valid email address.
	 */
	private static final long serialVersionUID = 6987867206840952134L;

	public EmailInvalidException() {
		super();
	}

	public EmailInvalidException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmailInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailInvalidException(String message) {
		super(message);
	}

	public EmailInvalidException(Throwable cause) {
		super(cause);
	}
	
	

}
