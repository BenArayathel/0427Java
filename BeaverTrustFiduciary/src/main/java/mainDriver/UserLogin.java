package mainDriver;

import java.util.Scanner;

public class UserLogin {
	//temporary login credentials: username == hardcoded, password == password
	
	public static void login() {
		String userLoginU = null;
		
		System.out.println("Enter your username: ");
		Scanner loginScanU = new Scanner(System.in);
		userLoginU = loginScanU.nextLine();
		
		if (userLoginU.equals("hardcoded")) {
			loginScanU.close();
			validatePassword();
		} else {
			reTryLogin();
		}
		
	}
	
	public static void reTryLogin() {
		String reenterUsername = null;
		Scanner tryAgain = new Scanner(System.in);
		reenterUsername = tryAgain.nextLine();
		
		System.out.println("Username not recognized. Try again? Y or N: ");
		if (reenterUsername.equalsIgnoreCase("y")) {
			tryAgain.close();
			login();
		} else if (reenterUsername.equalsIgnoreCase("n")) {
			quitOption.quit();
		} else {
			reTryLogin();
		}
	}
	
	public static void validatePassword() {
		String userLoginP = null;
		
		System.out.println("Enter your password: ");
		Scanner loginScanP = new Scanner(System.in);
		userLoginP = loginScanP.nextLine();
		
		if (userLoginP.equalsIgnoreCase("password")) {
//			loginScanP.close();
			System.out.println("working");
			UserHome.userWelcome();
		} else {
			System.out.println("That username and password combination was not found.");
			reTryLogin();
		}

	}

}
