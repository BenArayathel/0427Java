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

        BankApp.loggy.info("       2. Enter LAST name:");
        String lastName = scanner.nextLine();

        BankApp.loggy.info("       3. Enter Birthday (MM/dd/yyyy): ");
        String birthday = scanner.nextLine();
        
        BankApp.loggy.info("       4. Enter State (i.e. GA, NY, CA): ");
        String usState = scanner.nextLine();
        
        BankApp.loggy.info("       5. Account Type (CHEC, SAVI, BROK, CRED, MORT): ");
        String accountType = scanner.nextLine();
        
        BankApp.loggy.info("       6. Initial Deposit (i.e. 500.00):");
        String initBalance = scanner.nextLine();

        // Convert initBalance from String to BigDecimal.
        
        BigDecimal bigDecimalBalance = new BigDecimal(initBalance);
        
        bankServiceController.createCustomerAccount(firstName, lastName, birthday, usState, accountType, bigDecimalBalance);
        
        BankApp.loggy.info("Thanks for applying. Bank will review for approval.");

    };
    

}
