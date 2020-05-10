package com.company.controller;

import java.math.BigDecimal;
import java.util.Date;

import com.company.model.Customer;
import com.company.service.ServiceLayer;
import com.company.view.BankApp;

public class BankServiceController {
	
    private final ServiceLayer serviceLayer = new ServiceLayer();
	
	public Boolean validateRegistration(String firstName, String lastName, String accountNumber) {
		
		// call on serviceLayer to validate Registration. i.e. serviceLayer.checkRegistration();
		//BankApp.loggy.info("validateRegistration triggered...");
		BankApp.loggy.info("validateRegistration triggered...");

		return false;
	}
	
	public Customer validateLogin(String loginName, String password) {	
		BankApp.loggy.info("validateLogin triggered...");
		return serviceLayer.validateLogin(loginName, password);
		
	}
	
	public Customer createCustomerAccount(String firstName, String lastName, String birthday, String usState, String accountType, BigDecimal bigDecimalBalance) {
		return serviceLayer.createCustomerAccount(firstName, lastName, birthday, usState, accountType, bigDecimalBalance);
	}

}
