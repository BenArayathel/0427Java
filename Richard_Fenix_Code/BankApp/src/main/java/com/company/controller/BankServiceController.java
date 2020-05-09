package com.company.controller;

import com.company.view.BankApp;

public class BankServiceController {
	
	public Boolean validateRegistration(String firstName, String lastName, String accountNumber) {
		
		// call on serviceLayer to validate Registration. i.e. serviceLayer.checkRegistration();
		//System.out.println("validateRegistration triggered...");
		BankApp.loggy.info("validateRegistration triggered...");

		return false;
	}

}
