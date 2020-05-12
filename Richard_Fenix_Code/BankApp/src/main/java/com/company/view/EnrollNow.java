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
        
        // validation method to check if:
        // user (name(s) and account number match) and it is an active/approved account.          
        Customer customer = bankServiceController.validateCustomerAccount(firstName, lastName, accountId);

        // customer is returned if he can register.
        if (customer != null){

    		BankApp.loggy.info("Customer validated! Now, create a login name and password!");
        	
        	if (displayCreateLogin(customer.getCustomerId())) {
        		BankApp.loggy.info("Success! Login username and password CREATED!");
        	} else {
        		BankApp.loggy.info("Login username and password creation ABORTED!");
        	};
        	
        } 
                
        return;
	}
	
	public boolean displayCreateLogin(Integer customerId) {
		
		boolean isRegistered = false;
		
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
        
        if (password1.equals(password2)) {
        	isRegistered = bankServiceController.registerUser(customerId, loginName, password1);
        } else {
            BankApp.loggy.info("Your password did not match.");
            isRegistered = false;
        }
        
        return isRegistered;
	}


}
