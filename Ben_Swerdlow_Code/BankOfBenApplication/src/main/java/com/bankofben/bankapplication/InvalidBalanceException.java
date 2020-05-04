package com.bankofben.bankapplication;

public class InvalidBalanceException extends Exception {

	/**
	 * InvalidBalanceException will be thrown when an action would result in a negative balance
	 * or an overflow or underflow error in the balance field of an account. The message should
	 * be structured such that it is clear to the user which action is needed
	 */
	private static final long serialVersionUID = 7712306432268898848L;

	public InvalidBalanceException() {
		super();
	}

	public InvalidBalanceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidBalanceException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidBalanceException(String message) {
		super(message);
	}

	public InvalidBalanceException(Throwable cause) {
		super(cause);
	}
	
	

}
