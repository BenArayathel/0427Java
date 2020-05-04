package com.bankofben.bankapplication;

public class ExistingEmailException extends Exception {

	/**
	 * ExistingEmailException is thrown when there is an existing email in the Bank of Ben.
	 */
	private static final long serialVersionUID = 2725330444277403395L;

	public ExistingEmailException() {
		super();
	}

	public ExistingEmailException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExistingEmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExistingEmailException(String message) {
		super(message);
	}

	public ExistingEmailException(Throwable cause) {
		super(cause);
	}

}
