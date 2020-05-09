package user.cust.account.controller;

import java.util.Scanner;

import user.cust.account.models.User;

public class UserOptionsDirectory {
	
	public static Scanner scanner = new Scanner(System.in);

	
	public void userOptionsDir(User user) {
		
		System.out.println("\nSelect User Options");
		System.out.println("1 - Apply to Become a Customer");
		System.out.println("2 - Employee Portal");
		//System.out.println("Deposit: 3");
		//System.out.println("Withdraw: 4");
		System.out.println("3 - Quit");


		if (scanner.hasNext()) {

			int nav = Integer.parseInt(scanner.nextLine());
			
			if (nav == 1) {
				UserRegToBecomeCustomer ur = new UserRegToBecomeCustomer();
				ur.acctForm(user);
			}
			
			if (nav == 2) {
				Emp_Login e = new Emp_Login();
				e.employeeLogin();
			}
			
			if (nav == 3) {
				System.exit(0);
			}
			

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
