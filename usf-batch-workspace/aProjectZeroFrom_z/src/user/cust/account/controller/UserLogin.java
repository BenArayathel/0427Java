package user.cust.account.controller;

import java.util.Scanner;

import bank.transaction.dao.BankDaoImpl;
import log.Log;
import user.cust.account.models.User;

public class UserLogin {
	
	//private static String username;
	//private static String password;
	//public static Scanner scanner = new Scanner(System.in);
	
	public void getUserName() {
		Scanner scanner = new Scanner(System.in);
		String username = "";

		System.out.println("Enter Username: ");

		if (scanner.hasNext()) {

			username = scanner.nextLine();
		}
		getPassword(scanner,username);

	}
	
	public void getPassword(Scanner scanner, String username) {

		System.out.println("Enter Password: ");
		if (scanner.hasNext()) {

			String password = scanner.nextLine();
			
			
			// call the db
			BankDaoImpl b = new BankDaoImpl();
			User user = new User(username, password);
			//user.setUserName(username);
			//user.setPassword(password);
			//b.login(user);
			
			
			if (b.login(user)) {
				System.out.println("user_id upfront : " + user.getUser_id());
				//System.out.println("Thanks, you are now logged into the Bank");
				Log.logger("Thanks, you are now logged into the Bank");
//				UserOptions uo = new UserOptions();
//				uo.seeOptions(user);
			} else {
				//System.out.println("Please check credentials..");
				Log.logger("Please check credentials..");
			}
			scanner.close();
		}

	}

}
