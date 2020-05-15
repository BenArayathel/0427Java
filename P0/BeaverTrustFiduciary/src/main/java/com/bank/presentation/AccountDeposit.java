package com.bank.presentation;

import com.bank.main.Main;
import com.bank.models.User;
import com.bank.service_implementation.AccountServiceImplementation;
import com.bank.tools.BankException;

public class AccountDeposit {

	public static void deposit(User user) throws BankException {
		AccountServiceImplementation asi = new AccountServiceImplementation();
		String username = user.getUsername();
		String accountName;
		String depositAmount;
		
		Main.myLog.info("Enter the account name that you'd like to deposit into: ");
		accountName = Main.scan.nextLine().toString();
		Main.myLog.info("Enter the amount you are depositing: ");
		depositAmount = Main.scan.nextLine();
		
		if (Double.parseDouble(depositAmount) >= 0) {
			asi.deposit(user, accountName, depositAmount);
			Main.myLog.info("\nDeposit of $" + depositAmount + " complete!");
			Main.myLog.info("-----------------------------------------------------");
			AccountsView.view(user);			
		} else {
			Main.myLog.info("Enter an amount greater than $0.");
			AccountDeposit.deposit(user);
		}
		
	}
	
}
