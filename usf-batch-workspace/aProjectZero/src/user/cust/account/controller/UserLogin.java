package user.cust.account.controller;

import java.util.Scanner;

import bank.transaction.dao.BankDaoImpl;
import user.cust.account.models.User;

public class UserLogin {
	
	private static String username;
	private static String password;
	public static Scanner scanner = new Scanner(System.in);
	
	public static void getUserName() {

		System.out.println("Enter Username: ");

		if (scanner.hasNext()) {

			username = scanner.nextLine();
		}
		getPassword();

	}
	
	private static void getPassword() {

		System.out.println("Enter Password: ");
		if (scanner.hasNext()) {

			password = scanner.nextLine();
			scanner.close();
			
			// call the db
			BankDaoImpl b = new BankDaoImpl();
			User user = new User(username, password);
			
			
			if (b.login(user)) {
				System.out.println("Thanks, you are now logged into the Bank");
				
			} else {
				System.out.println("Please check credentials..");
			}
		}

	}

}
