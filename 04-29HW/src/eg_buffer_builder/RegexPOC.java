package eg_buffer_builder;

import java.util.Scanner;

/**
 * This class takes in a user input as if a website were to ask for the user to set up
 * a password that has to adhere to specific criteria
 * @author Dave Wroblewski
 * @version 1.0.0
 */

public class RegexPOC {

	public static void main(String[] args) {
		//Instantiate object
		RegexPOC poc = new RegexPOC();
		//Creates a scanner to accept user input
		Scanner scan = new Scanner(System.in);
		//Instructions for criteria
		System.out.println("Your password must start with at least one uppercase followed by up to 16 alphanumeric values\n and must contain at least "
				+ "one number and one special character.");
		System.out.print("\nEnter your new password: ");
		//Reads user input and stores it
		String password = scan.next();
		//Check against regex
		poc.checkPassword(password);
		//Closes scanner resource
		scan.close();
	}
	
	public RegexPOC() {
		
	}
	
	public void checkPassword(String inPW) {
		if(inPW.matches("[A-Z]{1,}[a-zA-Z0-9]{1,15}[0-9]{1}[^a-zA-Z0-9]{1}")) {
			System.out.println("Your password meets the criteria!");
		}else {
			System.out.println("Your password does not maatch the criteria, please try again!");
		}
	}

}
