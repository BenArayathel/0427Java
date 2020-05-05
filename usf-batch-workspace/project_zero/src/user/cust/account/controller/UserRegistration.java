package user.cust.account.controller;

import java.util.Scanner;

import bank.transaction.dao.BankDaoImpl;
import user.cust.account.models.User;
import util.helper.Hm;

public class UserRegistration {
	
	private static String username;
	private static String password;
	public static Scanner scanner = new Scanner(System.in);
	
	void getUserName() {
		
		System.out.println("Welcome to Registration");
		System.out.println("Create a Username: ");


		if (scanner.hasNext()) {

			username = scanner.nextLine();
			System.out.println("Hello: " + username);
		}
		getPassword();

	}
	
	public static void getPassword() {

		System.out.println("Create a password: ");
		if (scanner.hasNext()) {

			password = scanner.nextLine();
			//scanner.close();
			User user = new User(username, password);
			BankDaoImpl b = new BankDaoImpl();
			if (b.createUser(user)) {
				
				System.out.println(user.toString());
				System.out.println("\nThanks, you are now a: User of the Bank");
				UserOptions a = new UserOptions();
				a.select(user);
			}
			else {
				System.out.println("Failure to Create user.");
			}
		}
		scanner.close();
	}

}
