package com.company.view;

import java.util.Scanner;

import com.company.controller.BankServiceController;

public class MainMenu {
	
    private final BankServiceController bankServiceController = new BankServiceController();
	
    private int userChoice = 0;
    private Scanner scanner  = new Scanner(System.in);

    public void displayMenu() {
        
	    do {
	        userChoice = displayMainMenu();
	        switch(userChoice) {
	            case 1:
	                BankApp.loggy.info("going to sign on page...");
	                SignOn signOn = new SignOn();
	                signOn.displaySignOnMenu();
	                break;
	            case 2:
	                //BankApp.loggy.info("going to enroll page...");
	            	EnrollNow enrollNow = new EnrollNow();
	            	enrollNow.displayEnrollNow();
	                //displayEnrollNow();
	                break;
	            case 3:
	                //BankApp.loggy.info("applying for an account page...");
	                OpenCustomerAccount openCustomerAccount = new OpenCustomerAccount();
	                openCustomerAccount.displayForm();;
	                break;
	            case 0:
	            	BankApp.loggy.info("\n *** Thank you for using my application. Have a great day! ***");
	                break;
	            default:
	                //BankApp.loggy.info("Input not valid.");
	            	BankApp.loggy.info("\n *** Input not valid. Please try again. ***");
	                continue;
	        }
	    } while (userChoice != 0);
	    
        // Gracefully exit program.
        System.exit(0);
	    
    }


	public int displayMainMenu() {
		BankApp.loggy.info("================= Rich Bank Program =================");
		BankApp.loggy.info("       ");
		BankApp.loggy.info("    Here are your choices:");
		BankApp.loggy.info("       ");
		BankApp.loggy.info("       1. Sign On");
		BankApp.loggy.info("       2. Enroll Now (must have an active account)");
		BankApp.loggy.info("       3. Apply for an Account");
		BankApp.loggy.info("       ");
		BankApp.loggy.info("       0. Exit Program");
		BankApp.loggy.info("       ");
		BankApp.loggy.info("=====================================================");
		BankApp.loggy.info("    Enter your choice: ");
		
	    userChoice = Integer.parseInt(scanner.nextLine());
	
	    return userChoice;
	}		

}
