package com.bank.tools;

public class BankException extends Exception {
	
	/* 
	 * this abstract class provides the methods to create
	 * user-friendly error messages that can be display
	 * on the presentation layer
	 */
	
	public BankException() {
		super();
	}

	public BankException(final String message) {
		super(message);
	}

}
