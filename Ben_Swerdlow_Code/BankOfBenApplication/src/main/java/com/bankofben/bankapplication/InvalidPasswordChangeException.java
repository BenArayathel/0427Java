package com.bankofben.bankapplication;

public class InvalidPasswordChangeException extends Exception {

	/**
	 * InvalidPasswordChangeException is called when you try to change a password outside of the
	 * User.changePassword() method. You need to go through the proper process to change passwords.
	 */
	private static final long serialVersionUID = 6484695988458097856L;

	public InvalidPasswordChangeException() {
		super();
	}

	public InvalidPasswordChangeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidPasswordChangeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPasswordChangeException(String message) {
		super(message);
	}

	public InvalidPasswordChangeException(Throwable cause) {
		super(cause);
	}

}
