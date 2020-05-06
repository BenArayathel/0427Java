package com.application.bank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;

/*import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/*
/*
 * To help make app easier to use, all choices are number coded. 1 for checking and 2 for savings
 */

public class Lobby {
	final static Logger loggy = Logger.getLogger(Lobby.class);
	
	//private String filename = "./customerRecords.txt";
	private User currentUser = new User();

	public Lobby() {
		
	}

	public static void main(String[] args) {
		//Lobby l = new Lobby();
		Scanner sc = new Scanner(System.in);
//
//		System.out.println();
//		System.out.println("Would you like to activate all pending accounts? Y or N");
//		String activate = sc.nextLine().toLowerCase();
//		if (activate.equals("y") || activate.equals("n")) {
//			if (activate.equals("y")) {
//				e1.activateCustomerAccounts(myView);
//			}
//			else {
//				System.out.println("The peasants will have to wait one more day.");
//			}
//		}
//		else {
//			System.out.println("Incorrect entry. Please try again");
//		}
		//l.menuNav(sc, u2);
		sc.close();

	}
	
	
	public void menuNav(User u) {
		this.setCurrentUser(u);
		boolean again = true;
		
		while (again == true) {
			int response = 0;
			
			try {
			Scanner sc = new Scanner(System.in);
			System.out.println("\nPlease choose one of the following:");
			System.out.println("1. Check balance");
			System.out.println("2. Withdraw from an account");
			System.out.println("3. Deposit to an account");
			//System.out.println("4. Transfer funds");
			if (u.getStatus().equals("employee")) {
				System.out.println("5. Employee Options");
			}
			

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
					System.exit(0);
				} 
			}
			
			else {
				System.out.println("Error! Please enter one of the numbers provided.\n");

			} // End if 0<r<7  
			
			} // End of try block
			catch (InputMismatchException e) {
				loggy.error("InputMismatchException caught");
				System.out.println("Error! Please try again and enter a number between 1 and 7\n");
				again = false;
			}
			finally {
				if (response == 9) {
					loggy.info("Exiting Program");
					System.exit(0);
				}
				else {
					System.out.println("\n\nWelcome to Loxely Savings and Loans where we save for the rich and loan to the poor. How may we direct you?");
				}
				
				
			} // End of try/catch/finally
		} // End of while loop
	} // end of menuNav
	
	public void nextScreen() {
		for (int x = 0; x < 3; x++) {
			System.out.println();
		}
	}
	
	public User signIn() {
		Lobby lob = new Lobby();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your email address or type quit to Quit");
		String em = sc.nextLine();
		if (em.equalsIgnoreCase("quit")) {
			loggy.info("Exiting Program");
			System.exit(0);
		}
		System.out.println("Please enter your password");
		String pass = sc.nextLine();

	 String tempPassword = pass; //User.passwordEncryption(password);
		User cU = new User();
		/*
		ArrayList<Customer> arrList = readCustomers(fN);

		for (Customer c : arrList) {
			if (c.getEmail().equalsIgnoreCase(em)) {
				System.out.println("Entered password- " + pass + " saved password- " + c.getPassword());
				if (c.getPassword().equals(pass)) {
					System.out.println("Password accepted");
					loggy.info("Password matches. Logging in");
					nextScreen();
					cU = c;
					System.out.println("Welcome to Loxely Savings and Loans where we save for the rich and loan to the poor. How may we direct you?");
					menuNav(cU);
				}
				else {
					System.out.println("Incorrect Password");
					lob.signIn();
				} // end of inner if block
			} 
			else {
				registerNewUser();
			} // End of outer if block
			
		}  // End of for loop*/
		
		
		sc.close();
		return cU;
	}
	
	public void registerNewUser() {
		Customer temp = new Customer();
		temp.setStatus("customer");
		System.out.println("After temp variable creation");
		
		//System.out.println("Welcome to Loxely Savings and Loans where we save for the rich and loan to the poor. How may we direct you?");
		//menuNav(returnCustomer);
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

} // End of class
