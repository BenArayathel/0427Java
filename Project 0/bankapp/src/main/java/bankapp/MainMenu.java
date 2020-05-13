package bankapp;

import java.util.Scanner;
import dao.CustomerDAOImplementation;
import dao.employeeDAOImplementation;
import BankException.bankException;

public class MainMenu 
{
	public static void mainMenu() throws bankException 
	{
		System.out.println("MAIN MENU");
		int choice = 0;
		do 
		{
			System.out.println("Welcome to the Barolia Bank App!");
			System.out.println("Tell me, are you a:");
			System.out.println("1 - New Customer");
			System.out.println("2 - Returning Customer");
			System.out.println("3 - New Employee");
			System.out.println("4 - Returning Employee");
			System.out.println("5 - Leave App");
			System.out.println("Please type in the number associated with your status");
			Scanner scan = new Scanner(System.in);
			try 
			{
				choice = Integer.parseInt(scan.nextLine());
			}
			catch (NumberFormatException e)
			{
			}
			switch (choice) 
			{
				case 1:
					CustomerDAOImplementation a = new CustomerDAOImplementation();
					a.createCustomer();
					break;
				case 2:
					customerLogin.customerLogin();
					break;
				case 3:
					employeeDAOImplementation b = new employeeDAOImplementation();
					b.createEmployee();
					break;
				case 4:
					employeeLogin.employeeLogin();
					break;
				case 5:
					System.out.println("Thank you for using our app, goodbye!");
					System.exit(0);
					break;
				default:
					System.out.println("You've entered an invalid option, please try again");
					break;
			}
			choice = 0;
		}
		while (choice != 4);
	}
}

