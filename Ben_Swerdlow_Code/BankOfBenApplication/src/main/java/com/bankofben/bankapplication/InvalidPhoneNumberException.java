package com.bankofben.bankapplication;

public class InvalidPhoneNumberException extends Exception {

	/**
	 * InvalidPhoneNumberException is thrown when the supplied phone number is invalid.
	 */
	private static final long serialVersionUID = -5163447866075713688L;

	public InvalidPhoneNumberException() {
		super();
	}

	public InvalidPhoneNumberException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidPhoneNumberException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPhoneNumberException(String message) {
		super(message);
	}

	public InvalidPhoneNumberException(Throwable cause) {
		super(cause);
	}

}
