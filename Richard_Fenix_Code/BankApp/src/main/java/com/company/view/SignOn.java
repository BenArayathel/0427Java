package com.company.view;

import java.util.Scanner;

import com.company.controller.BankServiceController;
import com.company.model.Customer;

public class SignOn {
	
    private final BankServiceController bankServiceController = new BankServiceController();

    //private int userChoice = 0;
    private Scanner scanner  = new Scanner(System.in);
    
	public void displaySignOnMenu() {
	    BankApp.loggy.info("================= Rich Bank Program =================");
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info("    Sign On Page");
	    BankApp.loggy.info("       ");
	    System.out.print("       1. Enter your LOG IN name:");
        String loginName = scanner.nextLine();

        System.out.print("       2. Enter your PASSWORD:");
        String password = scanner.nextLine();
        
        // validation method to check if user (login name and password exists in db) .
        // call controller to validate login account
                
        
        Customer customer = bankServiceController.validateLogin(loginName, password);
        
        if (customer != null) {
        	
        	// UserAccountInfo userAccountInfo = new UserAccountInfo();
        	System.out.println("Login name and password validated. Display user account info");
        	
        	if (customer.getCustomerId() == 99999) {
        		AdminPage adminPage = new AdminPage();
        		adminPage.AdminOptions();
        	} else {
        		// CustomerPage();
            	BankApp.loggy.info("Display Customer Page.");
        	}
        	
        } else {
        	BankApp.loggy.info("Invalid login name and password.");
        } 	  
        	       
        return;
		
	}

}
