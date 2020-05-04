package com.bankofben.bankapplication;

public class RoutingNumberException extends Exception {

	/**
	 * RoutingNumberException is thrown when the routing number supplied is incorrect.
	 * There is only one routing number for the Bank of Ben (see BankOfBen.java).
	 */
	private static final long serialVersionUID = -7141197465762088913L;

	public RoutingNumberException() {
		super();
	}

	public RoutingNumberException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RoutingNumberException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoutingNumberException(String message) {
		super(message);
	}

	public RoutingNumberException(Throwable cause) {
		super(cause);
	}

}
