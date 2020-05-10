package com.bank.presentation;

import com.bank.main.Main;
import com.bank.models.User;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.service_interface.UserServiceInterface;
import com.bank.tools.BankException;
import com.bank.tools.QuitOption;

public class CreateUserAccountView {
	
	public static void userCreate() {
		
		// create usi to access methods, and blank user object
		UserServiceInterface usi = new UserServiceImplementation();
		User myUser = new User();
		
		// interface for user
		System.out.println("You are pre-approved to apply for a new account with Beaver Trust Fiduciary!");
		System.out.println("Please enter your preferred username: ");
		myUser.setUsername(Main.scan.nextLine().toString());
		System.out.println("Please enter your preferred password: ");
		myUser.setPassword(Main.scan.nextLine().toString());
		
		try {
			myUser = usi.createUser(myUser);
			if (true) {
				System.out.println("Thank you. Application pending. Please check back later to log in to your new account.");				
			} else {
				//a message about the appropriate problem
			}
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//errors to look out for: already existing username, do regex checks,
	
	
	
	
//		String createUsername = null;
//		String createPassword = null;
////		Scanner createScan = new Scanner(System.in);
//		
//		System.out.println("Enter your prefered username: ");
//		createUsername = myScanner.scan.nextLine().toString();
//		
////		for now, making sure credentials just don't equal current hardcoded version
////		if username doesn't exist, accept
//
//		if (createUsername.equalsIgnoreCase("hc")) {
//			System.out.println("Username is not available! Try again please");
//			userCreate();
//		} else if (createUsername.equalsIgnoreCase("quit")) {
//			quitOption.quit();
//		} else {
//			System.out.println("Username is available. Please enter your desired password.");
//			createPassword = myScanner.scan.nextLine();
//		}
//		
//		System.out.println("Your username is " + createUsername + " and your password is " + createPassword);
//		System.out.println("You are now logged into your new account.");
//		UserHome.userWelcome(createUsername);
//	}

}
