package bankapp;

import java.util.Scanner;

import BankException.bankException;

import java.util.List;

public class customerLogin 
{
	public static void customerLogin() throws bankException 
	{
		//will have to login in order to do anything
		//view balance
		//withdrawal/deposit
		//money transfer posting
		//accepting money transfer from someone else
		
		System.out.println("in Customer Login class");
		System.out.println("Welcome Customer of Barolia Bank!");
		System.out.println("Please choose from the following choices (1-4)");
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		do 
		{
			System.out.println("Customer Menu");
			System.out.println("----------------");
			System.out.println("1 - Search for Account by SSN");
			System.out.println("2 - Transfer money to different account");
			System.out.println("3 - Withdraw money");
			System.out.println("4 - Deposit money");
			System.out.println("5 - Logout/Return to Main Menu");
			try 
			{
				choice = Integer.parseInt(scan.nextLine());
			}
			catch (NumberFormatException e)
			{
			}
			switch(choice) 
			{
				case 1:
					System.out.println("Customer option 1");
					break;
				case 2:
					System.out.println("Customer option 2");
					break;
				case 3:
					System.out.println("Customer option 3");
					break;
				case 4:
					System.out.println("How much would you like to deposit?");
					break;
				case 4:
					System.out.println("Thank you for using our app, goodbye!");
					MainMenu.mainMenu();
					break;
				default:
					System.out.println("You've entered an invalid option, please try again");
					break;
			}
			choice = 0;
		}
		while(choice!= 4);
	}
}