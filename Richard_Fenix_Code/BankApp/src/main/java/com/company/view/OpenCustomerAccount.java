package com.company.view;

import java.math.BigDecimal;
import java.util.Scanner;

import com.company.controller.BankServiceController;
import com.company.model.Customer;

public class OpenCustomerAccount {
	
    private Scanner scanner  = new Scanner(System.in);

    private final BankServiceController bankServiceController = new BankServiceController();

    public void displayForm() {
	    BankApp.loggy.info("================= Rich Bank Program =================");
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info("    CREATE NEW CUSTOMER ACCOUNT Page");
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info("       1. Enter FIRST name:");
        String firstName = scanner.nextLine();
        
        if (!isStringOnlyAlphabet(firstName)) {
        	BankApp.loggy.info("Name is not valid");
        	return;
        }
        

        BankApp.loggy.info("       2. Enter LAST name:");
        String lastName = scanner.nextLine();
        
        if (!isStringOnlyAlphabet(lastName)) {
        	BankApp.loggy.info("Name is not valid");
        	return;
        }

        BankApp.loggy.info("       3. Enter Birthday (MM/dd/yyyy): ");
        String birthday = scanner.nextLine();
        
        BankApp.loggy.info("       4. Enter State (i.e. GA, NY, CA): ");
        String usState = scanner.nextLine();
        
        BankApp.loggy.info("       5. Account Type (CHEC, SAVI, BROK, CRED, MORT): ");
        String accountType = scanner.nextLine();
        
        BankApp.loggy.info("       6. Initial Deposit (i.e. 500.00):");
        String initBalance = scanner.nextLine();
                
        try {
            // Convert initBalance from String to BigDecimal.
			BigDecimal bigDecimalDeposit = new BigDecimal(initBalance);
			// if input is negative...
			if (bigDecimalDeposit.compareTo(BigDecimal.ZERO) < 1) {
				BankApp.loggy.info("   WARNING: Amount is not valid. No deposit made.");
				return;
			};
//		} catch(NumberFormatException err) {
//			throw new BusinessException("Deposit amount should be a valid, positive number. ");
		} catch (Exception e) {
			BankApp.loggy.info("   WARNING: Initial amount is not valid. No deposit made.");
			BankApp.loggy.error("   Initial amount is not a valid, positive number.");
			return;
		} 
        
        bankServiceController.createCustomerAccount(firstName, lastName, birthday, usState, accountType, new BigDecimal(initBalance));
        
        BankApp.loggy.info("Thanks for applying. Bank will review for approval.");

    };
    
    // helper method
    // Function to check String for only Alphabets 
    public static boolean isStringOnlyAlphabet(String str){ 
        return ((str != null) 
                && (!str.equals("")) 
                && (str.matches("^[a-zA-Z]*$"))); 
    } 

}
