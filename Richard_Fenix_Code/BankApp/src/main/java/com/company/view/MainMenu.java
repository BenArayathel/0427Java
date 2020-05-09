package com.company.view;

import java.util.Scanner;

import com.company.controller.BankServiceController;

public class MainMenu {
	
    private final BankServiceController bankServiceController = new BankServiceController();
	
    private int userChoice = 0;
    public Scanner scanner  = new Scanner(System.in);

    public void displayMenu() {
        
	    do {
	        userChoice = displayMainMenu();
	        switch(userChoice) {
	            case 1:
	                System.out.println("going to sign on page...");
	                //addCarToInventory();
	                //save();
	                break;
	            case 2:
	                //System.out.println("going to enroll page...");
	                displayEnrollNow();
	                break;
	            case 9:
	                System.out.println("Admin page... can be hidden");
	                //displayCarInventory();
	                break;
	            case 0:
	            	BankApp.loggy.info("\n *** Thank you for using my application. Have a great day! ***");
	                break;
	            default:
	                //System.out.println("Input not valid.");
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
		BankApp.loggy.info("       2. Enroll Now");
		BankApp.loggy.info("       9. Admin");	    
		BankApp.loggy.info("       ");
		BankApp.loggy.info("       0. Exit Program");
		BankApp.loggy.info("       ");
		BankApp.loggy.info("=====================================================");
		BankApp.loggy.info("    Enter your choice: ");
		
	    userChoice = Integer.parseInt(scanner.nextLine());
	
	    return userChoice;
	}

	public void displayEnrollNow() {
	    System.out.println("================= Rich Bank Program =================");
	    System.out.println("       ");
	    System.out.println("    Enroll Now Page");
	    System.out.println("       ");
	    System.out.print("       1. Enter your FIRST name:");
        String firstName = scanner.nextLine();

        System.out.print("       2. Enter your LAST name:");
        String lastName = scanner.nextLine();

        System.out.print("       3. Enter your ACCOUNT Number: ");
        String accountNumber = scanner.nextLine();
        
        // validation method to check if last name and account number match with live accounts.
        // call controller to validate the user
                
        if (bankServiceController.validateRegistration(firstName, lastName, accountNumber)){
        	
        	if (displayCreateLogin()) {
        		System.out.println("Success! Login username and password CREATED!");
        	} else {
        		System.out.println("Login username and password creation ABORTED!");
        	};
        	
        } else {
        	//System.out.println("Invalid user and account information.");
        	BankApp.loggy.error("Invalid user and account information.");
        };
                
        return;
	}
	
	public boolean displayCreateLogin() {
	    System.out.println("================= Rich Bank Program =================");
	    System.out.println("       ");
	    System.out.println("    Enroll Now Page");
	    System.out.println("       ");
	    System.out.print("       1. Create your LOGIN NAME:");
        String loginName = scanner.nextLine();
        System.out.println("loginName entered: " + loginName);

        System.out.print("       2. Enter your LOGIN PASSWORD:");
        String password1 = scanner.nextLine();

        System.out.println("password1 entered: " + password1);

        System.out.print("       3. RE-ENTER LOGIN PASSWORD: ");
        String password2 = scanner.nextLine();

        System.out.println("password2 entered: " + password2);

		return true;
	}
		

}
