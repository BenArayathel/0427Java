package mainDriver;

import java.util.Scanner;

public class WelcomeView {
	
	public static void welcome() {
		String hasAccount = null;

		System.out.println("Welcome to Beaver Trust Fiduciary");
		System.out.println("Do you already have an account? Y or N: ");
		Scanner welcomeScan = new Scanner(System.in);
		
		hasAccount = welcomeScan.nextLine();
		
		if (hasAccount.equals("Y") || hasAccount.equals("y")) {
			UserLogin.login();
		} else if (hasAccount.equals("N") || hasAccount.equals("n")) {
			CreateAccount.userCreate();
		} else if (hasAccount.equalsIgnoreCase("quit")) {
			quitOption.quit();
		} else {
			welcomeScan.close();
			welcome();
		}
	}

}
