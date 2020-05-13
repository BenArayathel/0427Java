package com.company.view.customer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.company.controller.BankServiceController;
import com.company.model.Account;
import com.company.model.Customer;
import com.company.view.BankApp;

public class TransferPage {
	
	private final BankServiceController bankServiceController = new BankServiceController();

    private Scanner scanner  = new Scanner(System.in);

    public void transferForm(Customer customer) {
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
	    BankApp.loggy.info("   - - - - - TRANSFER - - - - - -     ");	    
	    BankApp.loggy.info("       From Account ");
		BankApp.loggy.info("     -----------------  ");
	    System.out.print("       1. Enter source Account ID:");
        String fromAccountId = scanner.nextLine();
	    System.out.print("       2. Enter amount to Transfer:");
        String transferAmount = scanner.nextLine();
		BankApp.loggy.info("       ");	    
		
		Account fromAccount = findAccountFromList(fromAccountId, aList);
		
		if (fromAccount == null) {
			BankApp.loggy.info("   WARNING: Invalid account ID. No transfer made.");
			return;
		}
			
		try {
			// if input is not numeric.
			BigDecimal bigDecimalDeposit = new BigDecimal(transferAmount);
			// if input is negative...
			if (bigDecimalDeposit.compareTo(BigDecimal.ZERO) < 1) {
				BankApp.loggy.info("   WARNING: Amount is not valid. No transfer made.");
				return;
			};
		} catch (Exception e) {
			BankApp.loggy.info("   WARNING: Amount is not valid. No transfer made.");
			return;
		}
		
		// if Account balance is less then transfer amount, reject transaction.
		if (fromAccount.getBalance().compareTo(new BigDecimal(transferAmount)) < 1 ) {
			BankApp.loggy.info("   WARNING: Transfer amount must be less than Account Balance. No withdrawal made.");
			return;
		}

	    BankApp.loggy.info("       To Account ");
		BankApp.loggy.info("     -----------------  ");
	    System.out.print("       3. Enter destination Account ID:");
        String toAccountId = scanner.nextLine();
	    System.out.print("       4. Enter FIRST name (for validation):");
        String toFirstName = scanner.nextLine();
	    System.out.print("       5. Enter LAST name (for validation):");
        String toLastName = scanner.nextLine();
		BankApp.loggy.info("       ");	    

		Customer toCustomer = bankServiceController.validateCustomerAccount(toFirstName, toLastName, toAccountId);
		
		if (toCustomer == null) {
			BankApp.loggy.info("   WARNING: Invalid account ID to transfer to. No transfer made.");
			return;
		}
		
		fromAccount = bankServiceController.transferAmount(fromAccount, toAccountId, new BigDecimal(transferAmount));
		
		if (fromAccount == null) {
			BankApp.loggy.error("Error: Amount sent for transfer did not push through.");
			BankApp.loggy.info("Error encountered. Amount is not transfered.");			
		} else {
			BankApp.loggy.info("  $ " + transferAmount + " successfully transfered to account " + toAccountId + ".");
			BankApp.loggy.info("   Source account " + fromAccount.getAccountId() + " has new balance of $ " + fromAccount.getBalance() + ".");			
		} 	
		
	    return;
    }
    
    // Helper method
    public Account findAccountFromList(
    		  String accountId, List<Account> aList) {
    	for (Account account : aList) {
    		if (account.getAccountId().equals(accountId)) {
    			return account;
    		}
    	}
    	return null;		
    }



}
