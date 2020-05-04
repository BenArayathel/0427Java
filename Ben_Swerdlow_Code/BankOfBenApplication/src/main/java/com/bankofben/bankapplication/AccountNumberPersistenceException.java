package com.bankofben.bankapplication;

public class AccountNumberPersistenceException extends Exception {

	/**
	 * AccountEditException is thrown when an attempt to change an existing account
	 * number is made. If someone wants to change their account number, they have to 
	 * open a new account, migrate their assets and information over, and close their
	 * previous account.
	 */
	private static final long serialVersionUID = 2396336065340508418L;

	public AccountNumberPersistenceException() {
		super();
	}

	public AccountNumberPersistenceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AccountNumberPersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountNumberPersistenceException(String message) {
		super(message);
	}

	public AccountNumberPersistenceException(Throwable cause) {
		super(cause);
	}

}
