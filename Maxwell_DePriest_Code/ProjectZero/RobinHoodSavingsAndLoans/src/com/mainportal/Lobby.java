package com.mainportal;
import java.io.IOException;
import java.util.InputMismatchException;
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
		Account a = new Account(1, 123, 456, 1000.00, 2000.00);
		User u1 = new User("Max DePriest", "max@email.com", "867-5309", "customer", "guest", a);
		
		
		l.menuNav(sc, u1);
		sc.close();

	}
	
	public void menuNav(Scanner sc, User u) { //Add user object param with status. Pending only shows #1, Customer sees 1-5, Employee sees 1-6
		
			
		
		boolean again = true;
		
		while (again == true) {
			int response = 0;
			
			try {
			System.out.println("\nWelcome to Robin Hood Savings and Loans where we save for the rich and loan to the poor. How may we direct you?");
			System.out.println("\nPlease choose one of the following:");
	//		System.out.println("1. Edit Profile");
			System.out.println("2. Check balance");
			System.out.println("3. Withdraw from an account");
			System.out.println("4. Deposit to an account");
	//		System.out.println("5. Transfer funds");
	//		System.out.println("6. View records");
			System.out.println("7. Quit");
			response = sc.nextInt();
			nextScreen();
			if (String.valueOf(response).matches("[1-7]{1}")) {
					
			
				if (response == 2) {
					System.out.println("Which account would you like to check?");
					System.out.println("1. Checking Account");
					System.out.println("2. Savings Account");
					int wA2 = sc.nextInt();
					System.out.printf("You have $%.2f" ,u.getAccount().checkBalance(wA2));
					nextScreen();
				}
				
				else if (response == 3) {
					System.out.println("Which account would you like to withdraw from?");
					System.out.println("1. Checking Account");
					System.out.println("2. Savings Account");
					int wA3 = sc.nextInt();
					if (String.valueOf(wA3).matches("[1-2]{1}")) {
						System.out.println("What amount? (Whole numbers only)");
						int amt3 = sc.nextInt();
						System.out.printf("Here's $%.2f%n", (amt3 / 1.00));
						u.getAccount().withdrawMoney(amt3, wA3);
					}
					else {
						System.out.println("Invalid entry. Please try again");
					}
					
					nextScreen();
					
				}
				
				else if (response == 4) {
					System.out.println("Which account would you like to deposit money into?");
					System.out.println("1. Checking Account");
					System.out.println("2. Savings Account");
					int wA4 = sc.nextInt();
					if (String.valueOf(wA4).matches("[1-2]{1}")) {
						System.out.println("What amount? (Whole numbers only)");
						int amt4 = sc.nextInt();
						u.getAccount().depositMoney(amt4, wA4);
					}
					else {
						System.out.println("Invalid Entry. Please try again.");
					}
					
					nextScreen();
				}
				
				else if (response == 7) {
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
		
		}// End of while loop
	
		
	} // end of menuNav
	
	//Simulates screen change
	public void nextScreen() {
		for (int x = 0; x < 3; x++) {
			System.out.println();
		}
	}
	

	
} // End of class
	
	
	
	
	
	
	


