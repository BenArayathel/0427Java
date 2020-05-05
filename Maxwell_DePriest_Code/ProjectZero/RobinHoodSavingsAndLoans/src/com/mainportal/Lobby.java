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
	
	private String filename = "./customerRecords.txt";
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
			System.out.println("Error! Please try again and enter a number between 1 and 7\n");
			again = false;
		}
			finally {
				if (response == 9) {
					break;
				}
				else {
					System.out.println("\n\nWelcome to Loxely Savings and Loans where we save for the rich and loan to the poor. How may we direct you?");
				}
				
				
			}
			
		}// End of while loop
	
		
	} // end of menuNav
	
	public void nextScreen() {
		for (int x = 0; x < 3; x++) {
			System.out.println();
		}
	}
	
	public User signIn() {
		Lobby lob = new Lobby();
		Scanner sc = new Scanner(System.in);
		String fN = getFilename();
		System.out.println("Please enter your email address or type quit to Quit");
		String em = sc.nextLine();
		if (em.equalsIgnoreCase("quit")) {
			System.exit(0);
		}
		System.out.println("Please enter your password");
		String pass = sc.nextLine();

	 String tempPassword = pass; //User.passwordEncryption(password);
		User cU = new User();
		ArrayList<Customer> arrList = readCustomers(fN);

		for (Customer c : arrList) {
			if (c.getEmail().equalsIgnoreCase(em)) {
				System.out.println("Entered password- " + pass + " saved password- " + c.getPassword());
				if (c.getPassword().equals(pass)) {
					System.out.println("Password accepted");
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
			
		}  // End of for loop
		sc.close();
		return cU;
	}
	

	
	public void registerNewUser() {
		Customer temp = new Customer();
		temp.setStatus("customer");
		System.out.println("After temp variable creation");
		Lobby.addNewCustomer("./customerRecords", temp);
		
		//System.out.println("Welcome to Loxely Savings and Loans where we save for the rich and loan to the poor. How may we direct you?");
		//menuNav(returnCustomer);
	}
	
	// Just in case I need to populate the txt files again
	public static void createFile(String fN) {
		Account a4 = new Account(22334, 56217, 100.00, 200.00, "tom@email.com");
		Account a5 = new Account(22434, 51007, 1000.00, 2500.00, "tim@email.com");
		Account a6 = new Account(11344, 51897, 150.00, 500.00, "todd@email.com");
		Account a7 = new Account(18976, 56434, 2000.00, 2500.00, "mike@email.com");
		Account a8 = new Account(18906, 56034, 5000.00, 3500.00, "max@email.com");
		ArrayList<Customer> tempArray = new ArrayList<>();
		tempArray.add(new Customer("Tom Hardy", "tomH@email.com", "1233455", "hardyho", a4));
		tempArray.add(new Customer("Tim Hardy", "timH@email.com", "1233455", "password", a5));
		tempArray.add(new Customer("Todd Hardy", "todd@email.com", "1233455", "todd", a6));
		tempArray.add(new Customer("Mike Hardy", "mike@email.com", "1233455", "guest", a7));
		//tempArray.add(new Employee("Max DePriest", "max@email.com", "8675309" ,"willie", a8));
		//tempArray.add(new Employee("Hank D. Cowdog", "hank@email.com", "8673209" ,"drover", a7));
		
		writeCustomers("./customerRecords.txt", tempArray);
		
		
	}
	
	public static ArrayList<Customer> readCustomers(String filename) {
		ArrayList<Customer> customerRecs = new ArrayList<Customer>();
		
		try(ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(filename))){
			ArrayList<Object> obj1 = (ArrayList<Object>)objIn.readObject();
			
			for(Object oneObj : obj1) {
				customerRecs.add((Customer)oneObj);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Error. File not found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error. Please try again");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Error. Please try again");
			e.printStackTrace();
		}
		return customerRecs;
		
	}// End of readObject

	
	public static void writeCustomers(String filename, ArrayList<Customer> custs) {
		
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
	
	public static void writeEmployees(String filename, ArrayList<Employee> emps) {
		
		try(ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(filename))){
			objOut.writeObject(emps); //serialization
		} catch (FileNotFoundException e) {
			System.out.println("Error. File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error. Please try again");
			e.printStackTrace();
		}
	}
	
	public static void addNewCustomer(String filename, Customer newCustomer) {
		System.out.println("Beginning of addNewCustomer: newCustomer- " + newCustomer);
		ArrayList<Customer> custArr = readCustomers("./customerRecords.txt");
		custArr.add(newCustomer);
		System.out.println(custArr);
		try(ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("./customerRecords.txt"))){
			objOut.writeObject(custArr); //serialization
		} catch (FileNotFoundException e) {
			System.out.println("Error. File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error. Please try again");
			e.printStackTrace();
		}
		
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	
	
	

	
} // End of class
	
	
	
	
	
	
	


