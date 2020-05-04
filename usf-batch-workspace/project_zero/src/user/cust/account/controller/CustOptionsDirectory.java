package user.cust.account.controller;

import java.util.Scanner;

import user.cust.account.models.Customer;
import user.cust.account.models.User;

public class CustOptionsDirectory {
	
	public static Scanner scanner = new Scanner(System.in);

	public void select(Customer customer) {
		
		System.out.println("Select from Options");
		System.out.println("Apply for Account: 1");
		System.out.println("View Balance: 2");
		System.out.println("Deposit: 3");
		System.out.println("Withdraw: 4");


		if (scanner.hasNext()) {

			int nav = Integer.parseInt(scanner.nextLine());
			
			if (nav == 1) {
			CustApplyForAcct c = new CustApplyForAcct();
			c.acctForm(customer);
			}
			
			if (nav == 2){
				CustViewBalance_Deposit_Withdraw c = new CustViewBalance_Deposit_Withdraw();
				c.viewBalance();
			}
			
			if (nav == 3){
				CustViewBalance_Deposit_Withdraw c = new CustViewBalance_Deposit_Withdraw();
				c.deposit();
			}
				
			if (nav == 4){
				CustViewBalance_Deposit_Withdraw c = new CustViewBalance_Deposit_Withdraw();
				c.withdraw();
			}
			
		}
	}

}
