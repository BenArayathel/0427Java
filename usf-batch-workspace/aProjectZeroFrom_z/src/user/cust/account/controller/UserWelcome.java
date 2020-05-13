package user.cust.account.controller;

import java.util.List;
import java.util.Scanner;

import bank.transaction.dao.BankDAO;
import bank.transaction.dao.BankDaoImpl;
import connection.utilities.DAOUtilites;
import log.Log;
import user.cust.account.models.User;

public class UserWelcome {

	//private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		

		Log.logger("reference Users:\n");;
		
		BankDaoImpl b = new BankDaoImpl();
		//BankDAO b = DAOUtilites.getBankDAO();
		
		List<User> u = b.getAllUsers();
		for (User user : u) {
			//System.out.println(user.toString());
			Log.logger(user.toString());			
		}

		Scanner scanner = new Scanner(System.in);

		UserWelcome uw = new UserWelcome();
		uw.greetUser(scanner);
		//greetUser();
		//greetUser(scanner);
		scanner.close();
	}

	private void greetUser(Scanner scanner) {

		Log.logger("\nHello, Welcome to the Bank\n");
		Log.logger("-----------------------------------");;
		Log.logger("\n1 to Register: ");
		Log.logger("2 to Login: ");
		Log.logger("3 to Quit: ");

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
}
