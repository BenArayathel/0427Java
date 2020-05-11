package com.company.view;

import java.util.Scanner;

import com.company.controller.BankServiceController;
import com.company.model.Customer;

public class EnrollNow {
	
    private final BankServiceController bankServiceController = new BankServiceController();
    private Scanner scanner  = new Scanner(System.in);
	
	public void displayEnrollNow() {
	    BankApp.loggy.info("================= Rich Bank Program =================");
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info("    Enroll Now Page");
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info("       1. Enter your FIRST name:");
        String firstName = scanner.nextLine();

        BankApp.loggy.info("       2. Enter your LAST name:");
        String lastName = scanner.nextLine();

        System.out.print("       3. Enter your ACCOUNT ID: ");
        String accountId = scanner.nextLine();
        
        // validation method to check if user (name and account number match) with live accounts.
        // call controller to validate the user
          
        Customer customer = bankServiceController.validateCustomerAccount(firstName, lastName, accountId);
        
        // If there is a match and account is approved, customer record will be returned.
        if (customer != null){

    		BankApp.loggy.info("Customer validated! Now, create a login name and password!");
        	
//        	if (displayCreateLogin(accountId)) {
//        		BankApp.loggy.info("Success! Login username and password CREATED!");
//        	} else {
//        		BankApp.loggy.info("Login username and password creation ABORTED!");
//        	};
        	
        } 
                
        return;
	}
	
	public boolean displayCreateLogin(String accountId) {
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
        
        if (password1 != password2) {
            BankApp.loggy.info("Your password did not match.");
            return false;
        }
        
        //bankServiceController.registerUser(accountId, loginName, password1);

		return true;
	}


}
