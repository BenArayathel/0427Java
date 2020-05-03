package com.mainportal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * To help make app easier to use, all choices are number coded. 1 for checking and 2 for savings
 */

public class Lobby {
	

	public Lobby() {
		
	}

	public static void main(String[] args) {
		//Lobby l = new Lobby();
		Scanner sc = new Scanner(System.in);
		
//		Account a = new Account(123, 456, 1000.00, 2000.00, "max@email.com");
//		Account a2 = new Account(189, 476, 1500.00, 2700.00, "friar@email.com");
//		Account beta = new Account(987, 654, 1200.00, 5000.00, "lil_john@email.com");
//		User u1 = new Customer("Max DePriest", "max@email.com", "867-5309", "guest", a);
//		User u2 = new Employee("Little John", "lil_john@email.com", "123-3456", "password", beta);
//		Employee e1 = new Employee("Friar Tuck", "friar@email.com", "123-6543", "godIsGood", a2 );
		//ArrayList<Account> myView = e1.getCustomerAccounts();

		System.out.println();
		System.out.println("Would you like to activate all pending accounts? Y or N");
		String activate = sc.nextLine().toLowerCase();
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
		
		System.out.println("\nWelcome to Loxely Savings and Loans where we save for the rich and loan to the poor. How may we direct you?");
		//l.menuNav(sc, u2);
		sc.close();

	}
	
	
	public void menuNav(Scanner sc, User u) { 
		
			
		
		boolean again = true;
		
		while (again == true) {
			int response = 0;
			
			try {
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
					again = false;
				} 
			}
			
			else {
				System.out.println("Error! Please enter one of the numbers provided.\n");

			} // End if 0<r<7  
			
		} // End of try block
		catch (InputMismatchException e) {
			System.out.println("Error! Please try again and enter a number between 1 and 7\n");
			//again = false;
		}
			finally {
				if (response == 9) {
					break;
				}
				else {
					System.out.println("Welcome to Loxely Savings and Loans where we save for the rich and loan to the poor. How may we direct you?");
				}
				
				
			}
			
		}// End of while loop
	
		
	} // end of menuNav
	
	public void nextScreen() {
		for (int x = 0; x < 3; x++) {
			System.out.println();
		}
	}
	
	public String signIn(Scanner sc) {
		System.out.println("Please enter your email address");
		String em = sc.nextLine();
		System.out.println("Please enter your password");
		String pass = sc.nextLine();
		String passE =  User.passwordEncryption(pass);
		
		// pull record, if there is one, from ArrayList with matching email. Check password comparisons
		// if true, send to menuNav with email
		// if false, start new User registration
		
		return "email";
	}
	
	public String registerNewUser() {
		Customer temp = new Customer();
		return temp.getEmail();
	}
	
	public void createFile(String fN) {
		Account a4 = new Account(234, 567, 100.00, 200.00, "tomH@email.com");
		Account a5 = new Account(224, 517, 1000.00, 2500.00, "timH@email.com");
		ArrayList<Customer> tempArray = new ArrayList<>();
		tempArray.add(new Customer("Tom Hardy", "tomH@email.com", "1233455", "hardyHo", a4));
		tempArray.add(new Customer("Tom Hardy", "tomH@email.com", "1233455", "hardyHo", a5));
		
		writeObjects(fN, tempArray);
		
		
	}
	
	public ArrayList<Customer> readObject(String filename) {
		ArrayList<Customer> customerRecs = new ArrayList<Customer>();
		
		try(ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(filename))){
			ArrayList<Object> obj1 = (ArrayList<Object>)objIn.readObject();
			
			for(Object oneObj : obj1) {
				customerRecs.add((Customer)oneObj);
			}
			
			
			
//			obj = ois.readObject();
			//arrList = (ArrayList<Customer>)obj; // look at 4/30 hw
			
		} catch (FileNotFoundException e) {
			System.out.println("Error. File not found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error. Please try again");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Error. Please try again");
			e.printStackTrace();
		} finally {
			System.out.println("I'm in finally!!!");
//			return null;
		}
		return customerRecs;
		
	}// End of readObject
	
	public Customer readSingleObject(String fN)
	{
		Customer c = null;
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fN))){
			
			Object obj = ois.readObject();
			c = (Customer)obj; 
			
		} catch (FileNotFoundException e) {
			System.out.println("We could not find the appropriate file");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("We have encountered an error");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("We have encountered an error");
			e.printStackTrace();
		} 
		
		return c;
	}

	
	public void writeObjects(String filename, ArrayList<Customer> custs) {
		
		try(ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(filename))){
			objOut.writeObject(custs); //serialization
		} catch (FileNotFoundException e) {
			System.out.println("Error. File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error. Please try again");
			e.printStackTrace();
		}
	}
	

	
} // End of class
	
	
	
	
	
	
	


