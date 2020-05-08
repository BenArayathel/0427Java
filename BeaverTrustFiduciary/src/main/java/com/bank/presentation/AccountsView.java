package com.bank.presentation;

import com.accounts.OLD.Deposit;
import com.bank.tools.myScanner;
import com.bank.tools.quitOption;

public class AccountsView {
	
	public static void view(String userName) {
		String accountAction = null;

		System.out.println("Here are your accounts, " + userName);
		//this will be connect to db, iterate and print their accounts
		System.out.println("The Accounts...with balances");
				
		System.out.println("What would you like to do?");
		System.out.println(".........................................");
		System.out.println("Enter '1' to make a deposit.");
		System.out.println("Enter '2' to make a withdrawal.");
		System.out.println("Enter '3' to transfer funds.");
		System.out.println("Enter '4' to go back.");
		System.out.println("Or, as always, enter 'Quit' to exit.");
		
		accountAction = myScanner.scan.nextLine().toString();
		
		if (accountAction.equalsIgnoreCase("1")) {
			Deposit.deposit();			
//		} else if (accountAction.equalsIgnoreCase("2")) {
//			Withdrawal.withdraw();			
//		} else if (accountAction.equalsIgnoreCase("3")) {
//			Transfer.transfer();			
		} else if (accountAction.equalsIgnoreCase("4")) {
			UserHome.userWelcome(userName);			
		} else if (accountAction.equalsIgnoreCase("quit")) {
			quitOption.quit();
		} else {
			System.out.println("Please try again.");
			view(userName);
		}
		

	}

}


/* options:
 * 1. withdraw (just subtract up to amount in account
 * 2. deposit (however much they want, add)
 * 3. transfer (up to amount in account, subtract and add)
 * 4. go back to user home (userWelcome(username)
 * 5. quit
 */

