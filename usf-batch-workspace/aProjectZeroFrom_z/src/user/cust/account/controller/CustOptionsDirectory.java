package user.cust.account.controller;

import java.util.Scanner;

import user.cust.account.models.Customer;
import user.cust.account.models.User;

public class CustOptionsDirectory {
	
	public static Scanner scanner = new Scanner(System.in);
	

	public void select(User user) {
		
		System.out.println("\nWelcome Customer");
		System.out.println("Select Customer Options");
		System.out.println("1 - Apply for Account");
		System.out.println("2 - View Balance");
		System.out.println("3 - Deposit");
		System.out.println("4 - Withdraw");
		System.out.println("5 - Transfer");
		System.out.println("6 - Employee Portal");
		System.out.println("7 - Quit");


		if (scanner.hasNext()) {

			int nav = Integer.parseInt(scanner.nextLine());
			
			if (nav == 1) {
			CustApplyForAcct c = new CustApplyForAcct();
			c.acctForm(user);
			}
			
			if (nav == 2){
				CustViewBalance_Deposit_Withdraw c = new CustViewBalance_Deposit_Withdraw();
				c.viewBalance(user);
			}
			
			if (nav == 3){
				CustViewBalance_Deposit_Withdraw c = new CustViewBalance_Deposit_Withdraw();
				c.deposit(user);
			}
			
			if (nav == 4){
				CustViewBalance_Deposit_Withdraw c = new CustViewBalance_Deposit_Withdraw();
				c.withdraw(user);
			}
			
			if (nav == 5){
				CustViewBalance_Deposit_Withdraw c = new CustViewBalance_Deposit_Withdraw();
				c.transfer(user);
			}
			
			if (nav == 6) {
				Emp_Login e = new Emp_Login();
				e.employeeLogin();
			}
//				
			if (nav == 7){
				System.exit(0);
			}
			
		}
	}

}
