package user.cust.account.controller;

import java.util.Scanner;

import bank.transaction.dao.BankDaoImpl;
import connection.utilities.Hm;
import user.cust.account.models.User;

public class UserRegistration {
	
	private static String username;
	private static String password;
	private static long contact;
	private static String email;
	public static Scanner scanner = new Scanner(System.in);
	
	void registerUserName() {
		
		System.out.println("Welcome to Registration");
		System.out.println("Create a Username: ");


		if (scanner.hasNext()) {

			username = scanner.nextLine();
		}
		registerEmail();
		registerContact();
		registerPassword();

	}
	


	private void registerEmail() {
		System.out.println("Enter Email");
		if (scanner.hasNext()) {

			email = scanner.nextLine();
		}
		
	}
	
	private void registerContact() {
		
		System.out.println("Enter Phone Number");
		if (scanner.hasNext()) {

			contact = Long.parseLong(scanner.nextLine());
		}
		
	}



	public static void registerPassword() {

		System.out.println("Create a password: ");
		if (scanner.hasNext()) {

			password = scanner.nextLine();
			//scanner.close();
			User user = new User(username, password, email, contact);
			BankDaoImpl b = new BankDaoImpl();
			if (b.createUser(user)) {
				
				System.out.println(user.toString());
				System.out.println("\nThanks, you are now a: User of the Bank");
				UserOptions a = new UserOptions();
				a.seeOptions(user);
			}
			else {
				System.out.println("Failure to Create user.");
			}
		}
		scanner.close();
	}
	


}
