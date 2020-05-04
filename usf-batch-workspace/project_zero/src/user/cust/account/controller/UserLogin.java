package user.cust.account.controller;

import java.util.Scanner;

public class UserLogin {
	
	private static String username;
	private static String password;
	public static Scanner scanner = new Scanner(System.in);
	
	public static void getUserName() {

		System.out.println("Create a Username: ");

		if (scanner.hasNext()) {

			username = scanner.nextLine();
			System.out.println("Hello: " + username);
		}
		getPassword();

	}
	
	private static void getPassword() {

		System.out.println("Create a password: ");
		if (scanner.hasNext()) {

			password = scanner.nextLine();
			scanner.close();
			System.out.println("Thanks, you are now logged into the Bank");
		}

	}

}
