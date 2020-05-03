package com.mainportal;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/*
 * To help make app easier to use, all choices are number coded. 1 for checking and 2 for savings
 */

public class Lobby {

	public Lobby() {
		
	}

	public static void main(String[] args) {
		Lobby l = new Lobby();
		Scanner sc = new Scanner(System.in);
		Account a = new Account(123, 456, 1000.00, 2000.00, "max@email.com");
		Account beta = new Account(987, 654, 1200.00, 5000.00, "lil_john@email.com");
		User u1 = new User("Max DePriest", "max@email.com", "867-5309", "guest", a);
		User u2 = new User("Little John", "lil_john@email.com", "123-3456", "password", beta);
			
			
		l.menuNav(sc, u2);
		sc.close();

	}
	
	public void menuNav(Scanner sc, User u) { 
		
			
		
		boolean again = true;
		
		while (again == true) {
			int response = 0;
			
			try {
			System.out.println("\nWelcome to Loxely Savings and Loans where we save for the rich and loan to the poor. How may we direct you?");
			System.out.println("\nPlease choose one of the following:");
			System.out.println("1. Check balance");
			System.out.println("2. Withdraw from an account");
			System.out.println("3. Deposit to an account");
			System.out.println("4. Transfer funds");
			// Check to see if instanceOf Employee. if so, show "View records"
			//System.out.println("5. View Records");

			System.out.println("\nType 9 to quit");
			response = sc.nextInt();
			nextScreen();
			if (String.valueOf(response).matches("[1-6]{1}") || response == 9) {
					
			
				if (response == 1) {
					System.out.println("Which account would you like to check?");
					System.out.println("1. Checking Account");
					System.out.println("2. Savings Account");
					int wA1 = sc.nextInt();
					if (String.valueOf(wA1).matches("[1-2]{1}")) {
						System.out.printf("You have $%.2f" ,u.getAccount().checkBalance(wA1));
					}
					else {
						System.out.println("Invalid Entry. Please try again.");
					}
					
				}
				
				else if (response == 2) {
					System.out.println("Which account would you like to withdraw from?");
					System.out.println("1. Checking Account");
					System.out.println("2. Savings Account");
					int wA2 = sc.nextInt();
					if (String.valueOf(wA2).matches("[1-2]{1}")) {
						System.out.println("What amount? (Whole numbers only)");
						int amt2 = sc.nextInt();
						System.out.printf("Here's $%.2f%n", (amt2 / 1.00));
						u.getAccount().withdrawMoney(amt2, wA2);
					}
					else {
						System.out.println("Invalid entry. Please try again");
					}
					
					
				}
				
				else if (response == 3) {
					System.out.println("Which account would you like to deposit money into?");
					System.out.println("1. Checking Account");
					System.out.println("2. Savings Account");
					int wA3 = sc.nextInt();
					if (String.valueOf(wA3).matches("[1-2]{1}")) {
						System.out.println("What amount? (Whole numbers only)");
						int amt3 = sc.nextInt();
						u.getAccount().depositMoney(amt3, wA3);
					}
					else {
						System.out.println("Invalid Entry. Please try again.");
					}
					
				}
				
				else if (response == 9) {
					again = false;
				} 
			}
			
			else {
				System.out.println("Error! Please enter one of the numbers provided.\n");

			} // End if 0<r<7  
			
		} // End of try block
		catch (InputMismatchException e) {
			System.out.println("Error! Please try again and enter a number between 1 and 7\n");
			again = false;
		}
		
			
		nextScreen();
		}// End of while loop
	
		
	} // end of menuNav
	
	public void nextScreen() {
		for (int x = 0; x < 3; x++) {
			System.out.println();
		}
	}
	

	
} // End of class
	
	
	
	
	
	
	


