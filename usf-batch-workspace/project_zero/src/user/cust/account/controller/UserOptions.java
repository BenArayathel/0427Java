package user.cust.account.controller;

import java.util.Scanner;

import user.cust.account.models.User;

public class UserOptions {
	
	public static Scanner scanner = new Scanner(System.in);

	
	public void select(User user) {
		
		System.out.println("Select User Options");
		System.out.println("1 - Apply to Become a Customer");
		//System.out.println("View Balance: 2");
		//System.out.println("Deposit: 3");
		//System.out.println("Withdraw: 4");
		System.out.println("Provide possible exit/logout options here");


		if (scanner.hasNext()) {

			int nav = Integer.parseInt(scanner.nextLine());
			
			if (nav == 1) {
				UserRegToBecomeCustomer ur = new UserRegToBecomeCustomer();
				ur.acctForm(user);
			}
			
//			if (nav == 2){
//				CustViewBalance_Deposit_Withdraw c = new CustViewBalance_Deposit_Withdraw();
//				c.viewBalance();
//			}
//			
//			if (nav == 3){
//				CustViewBalance_Deposit_Withdraw c = new CustViewBalance_Deposit_Withdraw();
//				c.deposit();
//			}
//				
//			if (nav == 4){
//				CustViewBalance_Deposit_Withdraw c = new CustViewBalance_Deposit_Withdraw();
//				c.withdraw();
//			}
			
		}
	}

}
