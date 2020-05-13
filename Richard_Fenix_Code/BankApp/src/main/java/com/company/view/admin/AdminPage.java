package com.company.view.admin;

import java.util.Scanner;

import com.company.view.BankApp;
import com.company.view.OpenCustomerAccount;

public class AdminPage {
	
    private int userChoice = 0;
    private Scanner scanner  = new Scanner(System.in);
	
	public int displayAdminMenu() {
		BankApp.loggy.info("================= Rich Bank Program =================");
		BankApp.loggy.info("       ");
		BankApp.loggy.info("       **** ADMIN PAGE ****");
		BankApp.loggy.info("       ");
		BankApp.loggy.info("       1. View Customer Account");
		BankApp.loggy.info("       2. Approve Pending Account");
		BankApp.loggy.info("       3. View Transaction Log");	    
		BankApp.loggy.info("       4. Open Customer Account");
		BankApp.loggy.info("       ");
		BankApp.loggy.info("       0. Log off");
		BankApp.loggy.info("       ");
		BankApp.loggy.info("=====================================================");
		System.out.print("    Enter your choice: ");
		
	    userChoice = Integer.parseInt(scanner.nextLine());
	
	    return userChoice;
	}

	
    public void adminOptions() {
    	
    	//OpenCustomerAccount openCustomerAccount = new OpenCustomerAccount();
    	
	    do {
	        userChoice = displayAdminMenu();
	        switch(userChoice) {
	            case 1:
	                BankApp.loggy.info("View customer account...");
	                ViewCustomerAccount viewCustomerAccount = new ViewCustomerAccount();
	                viewCustomerAccount.customerAccountDetail();
	                break;
	            case 2:
	                ApprovePendingAccount approvePendingAccount = new ApprovePendingAccount();
	                approvePendingAccount.customerAccountDetail();
	                break;
	            case 3:
	                ViewTransactionLog viewTransactionLog = new ViewTransactionLog();
	                viewTransactionLog.displayAllTransactionLog();
	                break;
	            case 4:
	                OpenCustomerAccount openCustomerAccount = new OpenCustomerAccount();
	                openCustomerAccount.displayForm();;
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
