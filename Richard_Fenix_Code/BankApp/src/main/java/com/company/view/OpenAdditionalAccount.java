package com.company.view;

import java.math.BigDecimal;
import java.util.Scanner;

import com.company.controller.BankServiceController;

public class OpenAdditionalAccount {
	
    private Scanner scanner  = new Scanner(System.in);

    private final BankServiceController bankServiceController = new BankServiceController();

    public void displayForm() {
	    BankApp.loggy.info("================= Rich Bank Program =================");
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info("    CREATE ADDITIONAL ACCOUNT Page");
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info("       1. Enter Customer ID:");
        String customerId = scanner.nextLine();
        
        BankApp.loggy.info("       2. Account Type (CHEC, SAVI, BROK, CRED, MORT): ");
        String accountType = scanner.nextLine();
        
        BankApp.loggy.info("       3. Initial Deposit (i.e. 500.00):");
        String initBalance = scanner.nextLine();

        // Convert initBalance from String to BigDecimal.
        BigDecimal bigDecimalBalance = new BigDecimal(initBalance);
        
        // Convert customerId from string to Integer
        Integer custId = Integer.parseInt(customerId);
        
        if (bankServiceController.createAdditionalAccount(custId, accountType, bigDecimalBalance)) {;
        	BankApp.loggy.info("Thanks for applying. Bank will review for approval.");
        } else {
            BankApp.loggy.info("New account NOT created.");
        }

    };


}
