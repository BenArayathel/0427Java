package user.cust.account.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import bank.transaction.dao.BankDaoImpl;
import user.cust.account.models.User;

public class UserWelcome {

	//private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
		BankDaoImpl b = new BankDaoImpl();
		List<User> u = b.getAllUsers();
		for (User user : u) {
			System.out.println(user.toString());
		}

		Scanner scanner = new Scanner(System.in);

		UserWelcome uw = new UserWelcome();
		uw.greetUser(scanner);
		//greetUser();
		//greetUser(scanner);
		scanner.close();
	}

	private void greetUser(Scanner scanner) {

		System.out.println("Hello, welcome to the Bank.");
		System.out.println("1 to Register: ");
		System.out.println("2 to Login: ");
		System.out.println("3 to Quit: ");

		if (scanner.hasNextLine()) {

			int input = Integer.parseInt(scanner.nextLine());
			//scanner.close();

			if (input == 1) {
				UserRegistration ur = new UserRegistration();
				ur.registerUserName();
			}
			if (input == 2) {
				UserLogin u = new UserLogin();
				u.getUserName();
			}
			if (input == 3) {
				System.exit(0);
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
