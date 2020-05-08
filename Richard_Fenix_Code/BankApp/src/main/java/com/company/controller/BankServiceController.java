package com.company.controller;

public class BankServiceController {
	
	public Boolean validateRegistration(String firstName, String lastName, String accountNumber) {
		
		// call on serviceLayer to validate Registration. i.e. serviceLayer.checkRegistration();
		System.out.println("validateRegistration triggered...");
		return true;
	}

}
