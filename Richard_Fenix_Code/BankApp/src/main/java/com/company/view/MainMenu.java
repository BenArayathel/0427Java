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
	                displayEnrollNow();
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
//		We want admin to be able to just sign on to approve applications
//		BankApp.loggy.info("       9. Admin"); 
		BankApp.loggy.info("       ");
		BankApp.loggy.info("       0. Exit Program");
		BankApp.loggy.info("       ");
		BankApp.loggy.info("=====================================================");
		BankApp.loggy.info("    Enter your choice: ");
		
	    userChoice = Integer.parseInt(scanner.nextLine());
	
	    return userChoice;
	}

	public void displayEnrollNow() {
	    BankApp.loggy.info("================= Rich Bank Program =================");
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info("    Enroll Now Page");
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info("       1. Enter your FIRST name:");
        String firstName = scanner.nextLine();

        BankApp.loggy.info("       2. Enter your LAST name:");
        String lastName = scanner.nextLine();

        System.out.print("       3. Enter your ACCOUNT Number: ");
        String accountNumber = scanner.nextLine();
        
        // validation method to check if user (name and account number match) with live accounts.
        // call controller to validate the user
                
        if (bankServiceController.validateRegistration(firstName, lastName, accountNumber)){
        	
        	if (displayCreateLogin()) {
        		BankApp.loggy.info("Success! Login username and password CREATED!");
        	} else {
        		BankApp.loggy.info("Login username and password creation ABORTED!");
        	};
        	
        } else {
        	//BankApp.loggy.info("Invalid user and account information.");
        	BankApp.loggy.error("Invalid user and account information.");
        };
                
        return;
	}
	
	public boolean displayCreateLogin() {
	    BankApp.loggy.info("================= Rich Bank Program =================");
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info("    Enroll Now Page");
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info("       1. Create your LOGIN NAME:");
        String loginName = scanner.nextLine();
        BankApp.loggy.info("loginName entered: " + loginName);

        BankApp.loggy.info("       2. Enter your LOGIN PASSWORD:");
        String password1 = scanner.nextLine();

        BankApp.loggy.info("password1 entered: " + password1);

        BankApp.loggy.info("       3. RE-ENTER LOGIN PASSWORD: ");
        String password2 = scanner.nextLine();

        BankApp.loggy.info("password2 entered: " + password2);

		return true;
	}
		

}
