package com.bank.presentation;

import com.bank.main.Main;
import com.bank.models.User;
import com.bank.service_implementation.AccountServiceImplementation;
import com.bank.tools.BankException;

public class AccountWithdrawal {
	
	public static void withdraw(User user) throws BankException {
		String username = user.getUsername();
		AccountServiceImplementation asi = new AccountServiceImplementation();
		String accountName;
		String withdrawalAmount;
		
		
		Main.myLog.info("Enter the account name that you'd like to withdraw from: ");
		accountName = Main.scan.nextLine().toString();
		Main.myLog.info("Enter the amount you are withdrawing: ");
		withdrawalAmount = Main.scan.nextLine().toString();
		
		if (Double.parseDouble(withdrawalAmount) >= 0) {
			try {
				asi.withdraw(user, accountName, withdrawalAmount);
				Main.myLog.info("\nWithdrawal of $" + withdrawalAmount + " complete!");
				Main.myLog.info("-----------------------------------------------------");
				AccountsView.view(user);				
			} catch (BankException e) {
				Main.myLog.error(e.getStackTrace());
				throw new BankException("Could not complete that withdrawal, please check amount");
			}
		} else {
			Main.myLog.info("Enter an amount greater than $0.");
			AccountWithdrawal.withdraw(user);
		}
		
	}

}
