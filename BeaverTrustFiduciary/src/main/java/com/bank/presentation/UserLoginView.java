package com.bank.presentation;

import com.bank.dao_implementation.UserDAOImplementation;
import com.bank.main.Main;
import com.bank.models.User;
import com.bank.presentation.UserHomeView;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.tools.BankException;
import com.bank.tools.QuitOption;

public class UserLoginView {
	//temporary login credentials: username == hc, password == pass

	
	//first, check the users username via input
	public static void validateLogin() {
		User user = new User();
		
		UserServiceImplementation usi = new UserServiceImplementation();
		UserDAOImplementation udi = new UserDAOImplementation();
		String username = null;
		String password = null;
		
		System.out.println("Enter your username: ");
		username = Main.scan.nextLine().toString();
		System.out.println("Enter your password: ");
		password = Main.scan.nextLine().toString();
		

		
		try {
			if (username.equals("employee") && password.equals("employee")) {
				EmployeeView.Banking();
			} else if (usi.loginUser(username, password)) {
				System.out.println("Log in successful.");
				// this is where the NEW ACTION is happening
				// it creates an object to use from here on into the program
				user = udi.accessUserObject(username);
				UserHomeView.userWelcome(user);
			} else {
				System.out.println("Username/Password combo not found. Please try again");
				validateLogin();
			}
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
//		//either found in db, quit to exit, or try again on failed attempt
//		//and if it is found, move on to validate password
//		if (userLoginU.equalsIgnoreCase("hc")) {
//			validatePassword();
//		} else if (userLoginU.equalsIgnoreCase("quit")) {
//			QuitOption.quit();
//		} else {
//			System.out.println("Username not recognized. Please try again.");
//			validateUsername();
//		}
//		
//	}
//
//	
//	public static void validatePassword() {
//		String userLoginP = null;
//		
//		System.out.println("Enter your password: ");
//		userLoginP = Main.scan.nextLine();
//		
//		//hardcoded default password, will be "is in database + connected to username
//		if (userLoginP.equalsIgnoreCase("pass")) {
//			System.out.println(userLoginU);
//			UserHomeView.userWelcome(userLoginU);
//		} else if (userLoginP.equalsIgnoreCase("quit")) {
//			QuitOption.quit();
//		} else {
//			System.out.println("That username and password combination was not found. Please try again.");
//			validateUsername();
//		}

	}
	

}
