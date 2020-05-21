package com.bank.presentation;

import com.bank.dao_implementation.UserDAOImplementation;
import com.bank.main.Main;
import com.bank.models.User;
import com.bank.presentation.UserHomeView;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.tools.BankException;
import com.bank.tools.QuitOption;

public class UserLoginView {
	
	//first, check the users username via input
	public static void validateLogin() throws BankException {
		User user = new User();
		
		UserServiceImplementation usi = new UserServiceImplementation();
		UserDAOImplementation udi = new UserDAOImplementation();
		String username;
		String password;
		
		Main.myLog.info("Enter your username: ");
		username = Main.scan.nextLine().toString();
		Main.myLog.info("Enter your password: ");
		password = Main.scan.nextLine().toString();
		

		
		try {
			// They are the Banker
			if (username.equalsIgnoreCase("employee") && password.equalsIgnoreCase("employee")) {
				EmployeeView.banking();
			// They are found in the database
			} else if (usi.loginUser(username, password)) {
				Main.myLog.info("\nLog in successful.");
				
				// this is where the NEW ACTION is happening
				// it creates an object to use from here on into the program
				user = udi.accessUserObject(username);
				UserHomeView.userWelcome(user);
			} else {
				Main.myLog.info("Username/Password combo not found. Please try again");
				validateLogin();
			}
		} catch (BankException e) {
			Main.myLog.error(e.getMessage() + e.getStackTrace());
			Main.myLog.info("Something went wrong, please try again later.");
			WelcomeView.welcome();
		}
	}
}
