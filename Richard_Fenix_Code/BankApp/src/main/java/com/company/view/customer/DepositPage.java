package com.company.view.customer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.company.controller.BankServiceController;
import com.company.model.Account;
import com.company.model.Customer;
import com.company.view.BankApp;

public class DepositPage {

	private final BankServiceController bankServiceController = new BankServiceController();

    private Scanner scanner  = new Scanner(System.in);

    public void depositForm(Customer customer) {
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
	    BankApp.loggy.info("   - - - - - DEPOSIT - - - - - -     ");
	    BankApp.loggy.info("       ");
	    System.out.print("       1. Enter Account ID:");
        String accountId = scanner.nextLine();
		BankApp.loggy.info("       ");
	    System.out.print("       2. Enter deposit amount:");
        String depositAmount = scanner.nextLine();
		BankApp.loggy.info("       ");	    
		BankApp.loggy.info("       ");
		
		Account account = findAccountFromList(accountId, aList);
		
		if (account == null) {
			BankApp.loggy.info("   WARNING: Invalid account ID. No deposit made.");
			return;
		}
			
		try {
			BigDecimal bigDecimalDeposit = new BigDecimal(depositAmount);
			// if input is negative...
			if (bigDecimalDeposit.compareTo(BigDecimal.ZERO) < 1) {
				BankApp.loggy.info("   WARNING: Amount is not valid. No deposit made.");
				return;
			};
//		} catch(NumberFormatException err) {
//			throw new BusinessException("Deposit amount should be a valid, positive number. ");
		} catch (Exception e) {
			BankApp.loggy.info("   WARNING: Amount is not valid. No deposit made.");
			BankApp.loggy.error("   Deposit amount is not a valid, positive number.");
			return;
		} 
				
		account = bankServiceController.depositAmount(account, new BigDecimal(depositAmount));
		
		if (account == null) {
			BankApp.loggy.error("Error: Amount sent for deposit did not push through.");
			BankApp.loggy.info("Error encountered. Amount is not deposited.");			
		} else {
			BankApp.loggy.info("   Amount successfully deposited: " + depositAmount);
			BankApp.loggy.info("   Account_ID " + account.getAccountId() + " has new balance of " + account.getBalance());			
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
