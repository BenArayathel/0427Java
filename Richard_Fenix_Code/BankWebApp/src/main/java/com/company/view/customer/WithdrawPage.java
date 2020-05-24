package com.company.view.customer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.company.controller.BankServiceController;
import com.company.model.Account;
import com.company.model.Customer;
import com.company.view.BankApp;

public class WithdrawPage {
	
	private final BankServiceController bankServiceController = new BankServiceController();

    private Scanner scanner  = new Scanner(System.in);

    public void withdrawForm(Customer customer) {
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
	    BankApp.loggy.info("   - - - - - WITHDRAW - - - - - -     ");
	    BankApp.loggy.info("       ");
	    System.out.print("       1. Enter Account ID:");
        String accountId = scanner.nextLine();
		BankApp.loggy.info("       ");
	    System.out.print("       2. Enter amount to Withdraw:");
        String withdrawAmount = scanner.nextLine();
		BankApp.loggy.info("       ");	    
		BankApp.loggy.info("       ");
		
		Account account = findAccountFromList(accountId, aList);
		
		if (account == null) {
			BankApp.loggy.info("   WARNING: Invalid account ID. No deposit made.");
			return;
		}
			
		try {
			// if input is not numeric.
			BigDecimal bigDecimalDeposit = new BigDecimal(withdrawAmount);
			// if input is negative...
			if (bigDecimalDeposit.compareTo(BigDecimal.ZERO) < 1) {
				BankApp.loggy.info("   WARNING: Amount is not valid. No withrawal made.");
				return;
			};
//		} catch(NumberFormatException err) {
//			BankApp.loggy.error("   Withdrawal amount is not a valid, positive number.");
//			throw new BusinessException("Withdrawal amount should be a valid, positive number. ", err);
		} catch (Exception e) {
			BankApp.loggy.info("   WARNING: Amount is not valid. No withdrawal made.");
			BankApp.loggy.error("   Withdrawal amount is not a valid, positive number.");
			return;
		}
		
		// if Account balance is less then withdraw amount, reject transaction.
		if (account.getBalance().compareTo(new BigDecimal(withdrawAmount)) < 1 ) {
			BankApp.loggy.info("   WARNING: Withdrawal amount must be less than Account Balance. No withdrawal made.");
			return;
		}
				
		account = bankServiceController.withdrawAmount(account, new BigDecimal(withdrawAmount));
		
		if (account == null) {
			BankApp.loggy.error("Error: Amount sent for withdrawal did not push through.");
			BankApp.loggy.info("Error encountered. Amount is not withdrawn.");			
		} else {
			BankApp.loggy.info("   Amount successfully withdrawn: " + withdrawAmount);
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
