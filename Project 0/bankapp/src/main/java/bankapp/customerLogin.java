package bankapp;

import java.util.Scanner;
import BankException.bankException;
import java.util.List;
import dao.CustomerDAOImplementation;

public class customerLogin 
{
	public static void customerLogin() throws bankException 
	{
		//will have to login in order to do anything
		//withdrawal/deposit
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
			System.out.println("1 - View Balance by SSN");
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
					System.out.println("Enter your ssn");
					Scanner av = new Scanner(System.in);
					String ssn = av.next();
					CustomerDAOImplementation bv = new CustomerDAOImplementation();
					System.out.println("Searching for account by ssn");
					bv.showCustomerDetails(ssn);
					break;
				case 2:
					System.out.println("Enter your ssn");
					Scanner at = new Scanner(System.in);
					String ssn2 = at.next();
					
					System.out.println("Please enter amount to transfer");
					Scanner bt = new Scanner(System.in);
					String transfer = bt.next();
					double ct = Double.parseDouble(transfer);
					
					System.out.println("Please type in the ssn of the person you would like to transfer it to");
					Scanner dt = new Scanner(System.in);
					String ssn3 = dt.next();
					CustomerDAOImplementation zt = new CustomerDAOImplementation();
					zt.transfer(ct, ssn2, ssn3);
					break;
				case 3:
					System.out.println("Enter your ssn");
					Scanner cw = new Scanner(System.in);
					String ssnw = cw.next();
					
					System.out.println("How much would you like to withdraw?");
					Scanner dw = new Scanner(System.in);
					String withdraw = dw.next();
					double ew = Double.parseDouble(withdraw);
					
					CustomerDAOImplementation fw = new CustomerDAOImplementation();
					fw.withdraw(ew, ssnw);
					break;
				case 4:
					System.out.println("Enter your ssn");
					Scanner cd = new Scanner(System.in);
					String ssnd = cd.next();
					
					System.out.println("How much would you like to deposit?");
					Scanner dd = new Scanner(System.in);
					String deposit = dd.next();
					double ed = Double.parseDouble(deposit);
					
					CustomerDAOImplementation fd = new CustomerDAOImplementation();
					fd.deposit(ed, ssnd);
					break;
				case 5:
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