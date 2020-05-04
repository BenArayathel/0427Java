package user.cust.account.controller;

import java.util.Scanner;

import util.helper.Hm;

public class UserWelcome {

	//private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		UserWelcome uw = new UserWelcome();
		uw.greetUser(scanner);
		//greetUser();
		//greetUser(scanner);
		scanner.close();
	}

	private void greetUser(Scanner scanner) {

		System.out.println("Hello, welcome to the Bank.");
		System.out.println("Enter: 1 to Register: ");
		System.out.println("Enter: 2 to Login: ");

		if (scanner.hasNextLine()) {

			int input = Integer.parseInt(scanner.nextLine());
			//scanner.close();

			if (input == 1) {
				UserRegistration ur = new UserRegistration();
				ur.getUserName();
			}
			if (input == 2) {
				UserLogin.getUserName();
			}
		}

	}
	
//	private static void greetUser() {
//
//		System.out.println("Hello, welcome to the Bank.");
//		System.out.println("Enter: 1 to Register: ");
//		System.out.println("Enter: 2 to Login: ");
//
//		if (Hm.getString() != null) {
//
//			int input = Integer.parseInt(Hm.input);
//			//Hm.scanner.close();
//
//			if (input == 1) {
//				UserRegistration.getUserName();
//			}
//			if (input == 2) {
//				UserLogin.getUserName();
//			}
//			
//		}
//
//	}
}
