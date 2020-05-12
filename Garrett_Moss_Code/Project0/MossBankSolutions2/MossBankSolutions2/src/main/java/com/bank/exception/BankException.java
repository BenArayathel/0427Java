package com.bank.exception;

public class BankException  extends Exception{
	
	public BankException() {
		
		super();
	}
	
	public BankException(final String exceptionMessage) {
		
		super(exceptionMessage);
	}

}
