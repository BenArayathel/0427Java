package com.bank.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bank.exception.BankException;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.service.CustomerService;
import com.bank.service.impl.CustomerServiceImpl;

public class MainDriver {
	
	final static Logger loggy = Logger.getLogger(clazz)

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Moss Bank Solutions");
		System.out.println("------------------------------");
		CustomerService service = new CustomerServiceImpl();
		int choice = 0;
		do {
			System.out.println("Login");
			System.err.println("Select an option below");
			System.out.println("---------------------------");
			System.out.println("1) Are you a Customer?");
			System.out.println("2) Are you an Employee");
			System.out.println("3) Exit");
			try {
				choice=Integer.parseInt(scanner.nextLine());
				
			} catch (NumberFormatException e) {
			}
			switch (choice) {
			case 1:
			int choice2 = 0;	
				do {
					
				System.out.println("Welcome to the Customer portal!");
				System.out.println("-------------------------------");
				System.out.println("1) Existing Customer");
				System.out.println("2) New Customer");
				System.out.println("3) Exit");
				try {
					choice2=Integer.parseInt(scanner.nextLine());
				} catch (NumberFormatException e) {

				}
				switch (choice2) {
				case 1:
				int choice3 = 0;
				////////////need to login
					do {
						System.out.println("Welcome back!");
						System.out.println("-------------------------------");
						System.out.println("1) View Balance");
						System.out.println("2) Make Withdrawl");
						System.out.println("3) Make Deposit");
						System.out.println("4) Make Transfer");
						System.out.println("5) Accept Transfer");
						System.out.println("6) Apply for new Account");
						System.out.println("7) Exit");
						try {
							 choice3=Integer.parseInt(scanner.nextLine());
						} catch (NumberFormatException e) {

						}
						switch (choice3) {
						case 1:
							
							break;
						case 2:
							
							break;
						case 3:
							
							break;
						case 4:
							
							break;
						case 5:
							
							break;
						case 6:
							
							break;
						case 7:
							System.out.println("Thankq for using our App.......");
							System.exit(0);
							break;

						default:
							System.out.println("Entered choice should be between(1-7)");
							break;
						}
						choice3=0;
					} while (choice3 !=7);
				case 2:
					int choice4=0;
					do {
						System.out.println("Welcome new customer!");
						System.out.println("-------------------------------");
						System.out.println("1) Create Account");
						System.out.println("2) Exit");
						try {
							 choice4=Integer.parseInt(scanner.nextLine());
						} catch (NumberFormatException e) {

						}
						switch (choice4) {
						
						case 1:
							Customer customer = new Customer();
							System.out.println("Enter Username");
							customer.setUsername(scanner.nextLine());
							try {
								System.out.println("Enter Password");
								customer.setPassword(scanner.nextLine());
								System.out.println("Enter Name");
								customer.setName(scanner.nextLine());
								System.out.println("Enter birthdate in dd.MM.yyyy format");
								String birthdate = scanner.nextLine();
								//customer.setBirthdate(CustomerServiceImpl.isValidDate(birthdate));
								System.out.println("Enter Address");
								customer.setAddress(scanner.nextLine());
								customer = service.createCustomerAccount(customer);
								System.out.println("Customer Registered");
								System.out.println(customer);
							} catch (BankException e) {
								System.out.println(e.getMessage());
							}
							break;
						case 2:
							System.out.println("Thankq for using our App.......");
							System.exit(0);
							break;
						
						default:
							System.out.println("Entered choice should be between (1-2)");
							break;
						} 
						choice4 = 0;
					} while (choice4 !=2);
				case 3:
					System.out.println("Thankq for using our App.......");
					System.exit(0);
					break;

				default:
					System.out.println("Entered choice should be between(1-3)");
					break;
				}
				choice2=0;
				} while (choice2 !=3);
				
			case 2:
				int choice5 = 0;	
				do {
					
				System.out.println("Welcome to the Employee portal!");
				System.out.println("-------------------------------");
				System.out.println("1) Sign In");
				System.out.println("2) Exit");
				try {
					choice5=Integer.parseInt(scanner.nextLine());
				} catch (NumberFormatException e) {

				}
				switch (choice5) {
				case 1:

				int choice6 = 0;
				do {					
					Employee employee = new Employee();
					System.out.println("Enter Username");
					employee.setUsername(scanner.nextLine());
					System.out.println("What would you like to do today");
					System.out.println("-------------------------------");
					System.out.println("1) Approve Accounts");
					System.out.println("2) Reject Accounts");
					System.out.println("3) View Customer Account");
					System.out.println("4) View Transaction Logs");
					System.out.println("5) Exit");
					try {
						choice6=Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {

					}
					switch (choice6) {
					case 1:
						
						break;
					case 2:
						
						break;
					case 3:
		
						break;
					case 4:
		
						break;
					case 5:
						System.out.println("Thanks for using our App.......");
						System.exit(0);
						break;

					default:
						System.out.println("Entered choice should be between(1-5)");
						break;
					}
						choice6 = 0;
					} while (choice6 !=5);
					
				case 2:
					System.out.println("Thanks for using our App.......");
					System.exit(0);
					break;

				default:
					System.out.println("Entered choice should be between(1-2)");
					break;
				}
				choice5 =0;
				} while (choice5 !=2);
			
			case 3:
				System.out.println("Thanks for using our App.......");
				System.exit(0);
				break;
			default:
				System.out.println("Entered choice should be between(1-3)");
				break;
			}
			choice=0;
		} while (choice !=3);
	}

}
