package user.cust.account.controller;

import java.util.Scanner;

import bank.transaction.dao.BankDaoImpl;
import log.Log;
import not.used.Hm;
import user.cust.account.models.User;

public class UserRegistration {

	private static String username;
	private static String password;
	private static long contact;
	private static String email;
	public static Scanner scanner = new Scanner(System.in);

	void registerUserName() {

		// System.out.println("Welcome to Registration");
		Log.logger("Welcome to Registration");
		// System.out.println("Create a Username: ");
		Log.logger("Create a Username: ");

		if (scanner.hasNext()) {

			username = scanner.nextLine();
		}
		registerEmail();
		registerContact();
		registerPassword();

	}

	private void registerEmail() {
		// System.out.println("Enter Email");
		Log.logger("Enter Email");
		if (scanner.hasNext()) {

			email = scanner.nextLine();
		}

	}

	private void registerContact() {

		// System.out.println("Enter Phone Number");
		Log.logger("Enter Phone Number: 10 digits only");

		if (scanner.hasNextLong()) {

			contact = Long.parseLong(scanner.nextLine());
		}

		else {
			Log.logger("Invalid... only 10 digits");
		}

	}

	public static void registerPassword() {

		// System.out.println("Create a password: ");
		Log.logger("Create a password");
		if (scanner.hasNext()) {

			password = scanner.nextLine();
			// scanner.close();
			User user = new User(username, password, email, contact);
			BankDaoImpl b = new BankDaoImpl();
			if (b.createUser(user)) {

				// System.out.println(user.toString());
				// System.out.println("\nThanks, you are now a: User of the Bank");
				Log.logger("\nThanks, you are now a: User of the Bank");
				UserOptionsDirectory a = new UserOptionsDirectory();
				a.userOptionsDir(user);
			} else {
				// System.out.println("Failure to Create user.");
				Log.logger("Failure to create user.");
			}
		}
		scanner.close();
	}

}
