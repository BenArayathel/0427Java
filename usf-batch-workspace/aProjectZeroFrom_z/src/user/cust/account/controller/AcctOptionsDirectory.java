package user.cust.account.controller;

import java.util.Scanner;

import user.cust.account.models.Account;
import user.cust.account.models.Customer;

public class AcctOptionsDirectory {
	
	public static Scanner scanner = new Scanner(System.in);

	public void select(Account acct) {
		
		System.out.println("Select from Options");
		
		System.out.println("1 - View Balance");
		System.out.println("2 - Deposit");
		System.out.println("3 - Withdraw");
		//System.out.println("Apply for Another Account");


		if (scanner.hasNext()) {

			int nav = Integer.parseInt(scanner.nextLine());
			
			if (nav == 1) {
			//CustApplyForAcct c = new CustApplyForAcct();
			//c.acctForm(customer);
				System.out.println("\nYour balance: " + acct.getBalance());
			}
			
			if (nav == 2){
				CustViewBalance_Deposit_Withdraw c = new CustViewBalance_Deposit_Withdraw();
				c.viewBalance();
			}
			
			if (nav == 3){
				CustViewBalance_Deposit_Withdraw c = new CustViewBalance_Deposit_Withdraw();
				c.deposit();
			}
			
		}
	}

}
