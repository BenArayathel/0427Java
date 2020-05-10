package com.company.view;

import java.util.Scanner;

public class AdminPage {
	
    private int userChoice = 0;
    private Scanner scanner  = new Scanner(System.in);
	
	public int displayAdminMenu() {
		BankApp.loggy.info("================= Rich Bank Program =================");
		BankApp.loggy.info("       ");
		BankApp.loggy.info("    ADMINISTRATOR:");
		BankApp.loggy.info("       ");
		BankApp.loggy.info("       1. Open Customer Account");
		BankApp.loggy.info("       2. Approve Customer Account");
		BankApp.loggy.info("       ");	    
		BankApp.loggy.info("       ");
		BankApp.loggy.info("       0. Exit Program");
		BankApp.loggy.info("       ");
		BankApp.loggy.info("=====================================================");
		BankApp.loggy.info("    Enter your choice: ");
		
	    userChoice = Integer.parseInt(scanner.nextLine());
	
	    return userChoice;
	}

	
    public void AdminOptions() {
    	
    	OpenCustomerAccount openCustomerAccount = new OpenCustomerAccount();
    	
	    do {
	        userChoice = displayAdminMenu();
	        switch(userChoice) {
	            case 1:
	                BankApp.loggy.info("Opening customer account...");
	                openCustomerAccount.displayForm();
	                break;
	            case 2:
	                openCustomerAccount.showList();
	                break;
	            case 0:
	            	BankApp.loggy.info("\n *** Exiting admin page... ***");
	                break;
	            default:
	                //BankApp.loggy.info("Input not valid.");
	            	BankApp.loggy.info("\n *** Input not valid. Please try again. ***");
	                continue;
	        }
	    } while (userChoice != 0);
	    
	    return;
    }

}
