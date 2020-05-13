package com.company.view.customer;

import java.util.List;
import java.util.Scanner;

import com.company.controller.BankServiceController;
import com.company.model.Account;
import com.company.model.Customer;
import com.company.model.Transaction;
import com.company.view.BankApp;
import com.company.view.OpenCustomerAccount;
import com.company.view.admin.ApprovePendingAccount;
import com.company.view.admin.ViewCustomerAccount;
import com.company.view.admin.ViewTransactionLog;

public class CustomerPage {

	private final BankServiceController bankServiceController = new BankServiceController();

    private int userChoice = 0;
    private Scanner scanner  = new Scanner(System.in);
	
	public int displayCustomerMenu(Customer customer) {
		BankApp.loggy.info("================= Rich Bank Program =================");
		BankApp.loggy.info("       ");
		BankApp.loggy.info("       **** Hi, " + customer.getFirstName() + " ****");
	    BankApp.loggy.info("       (Customer ID :" + customer.getCustomerId() + ")");
	    BankApp.loggy.info("    First Name: " + customer.getFirstName());
	    BankApp.loggy.info("    Last Name: " + customer.getLastName());
//	    BankApp.loggy.info("       Birthday: " + customer.getBirthday());
//	    BankApp.loggy.info("       State: " + customer.getState());
			
		List<Account> aList = bankServiceController.getAccountListByCustomerId(customer.getCustomerId());
	
	    BankApp.loggy.info("================ ACTIVE ACCOUNT(S) ================");
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info(" Account_ID        TYPE         Balance ");
	    BankApp.loggy.info(" -----------     --------    --------------");
	    aList.forEach(account -> 
	    	BankApp.loggy.info("       " + account.getAccountId() + "         " + account.getAccountType() + "         " + account.getBalance())
	    );
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info("   - - - - Choose Desired Action - - - - -     ");
		BankApp.loggy.info("           1. Deposit");
		BankApp.loggy.info("           2. Withdraw");
		BankApp.loggy.info("           3. Transfer");	    
		BankApp.loggy.info("       ");
		BankApp.loggy.info("           0. Log off");
		BankApp.loggy.info("       ");
		BankApp.loggy.info("=====================================================");
		BankApp.loggy.info("    Enter your choice: ");
		
	    userChoice = Integer.parseInt(scanner.nextLine());
	
	    return userChoice;
	}

	
    public void customerOptions(Customer customer) {
    	
    	//OpenCustomerAccount openCustomerAccount = new OpenCustomerAccount();
    	
	    do {
	        userChoice = displayCustomerMenu(customer);
	        switch(userChoice) {
	            case 1:
	                DepositPage depositPage = new DepositPage();
	                depositPage.depositForm(customer);
	                break;
	            case 2:
	                WithdrawPage withdrawPage = new WithdrawPage();
	                withdrawPage.withdrawForm(customer);
	                break;
	            case 3:
	                TransferPage transferPage = new TransferPage();
	                transferPage.transferForm(customer);
	                break;
	            case 0:
	            	BankApp.loggy.info("\n *** Exiting customer page... ***");
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
