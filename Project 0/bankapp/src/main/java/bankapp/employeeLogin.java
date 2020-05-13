package bankapp;

import java.util.Scanner;

import BankException.bankException;
import dao.employeeDAOImplementation;

import java.util.List;

public class employeeLogin extends employeeDAOImplementation
{
	public static void employeeLogin() throws bankException 
	{
		int i = 0;
		System.out.println("Please input your username (case sensitive)");
		Scanner username = new Scanner(System.in);
		String un = username.next();
		if(un.equalsIgnoreCase("A"))//change from ignores case to regular
		{
			System.out.println("Please type in your password");
			Scanner password = new Scanner(System.in);
			String pw = password.next();
			if(pw.equalsIgnoreCase("B"))//change from ignores case to regular
			{
				System.out.println("Welcome Employee of Barolia Bank!");
				System.out.println("Please choose from the following choices (1-4)");
				Scanner scan = new Scanner(System.in);
				int choice = 0;
				do 
				{
					System.out.println("Employee Menu");
					System.out.println("-------------------------------");
					System.out.println("1 - View Pending Apps");
					System.out.println("2 - Approve Customer Account");
					System.out.println("3 - Reject Customer Account");
					System.out.println("4 - Search for Account by SSN");
					System.out.println("5 - View transactions log");
					System.out.println("6 - Logout/Return to Main Menu");
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
							System.out.println("Here is the list of pending account approvals");
							employeeDAOImplementation a = new employeeDAOImplementation();
							a.getAllPendingApps();
							break;
						case 2:
							Scanner b = new Scanner(System.in);
							System.out.println("Which account would you like to approve?");
							System.out.println("Please enter their SSN to get them approved!");
							String ssna = b.nextLine();
							employeeDAOImplementation c = new employeeDAOImplementation();
							c.approveApp(ssna);
							break;
						case 3:
							Scanner d = new Scanner(System.in);
							System.out.println("Which account would you like to reject?");
							System.out.println("Please enter their SSN to remove them!");
							String ssnr = d.nextLine();
							employeeDAOImplementation e = new employeeDAOImplementation();
							e.approveApp(ssnr);
							break;
						case 4:
							Scanner f = new Scanner(System.in);
							System.out.println("Please type in the Account you're looking for by using the SSN");
							String ssnfindapp = f.nextLine();
							employeeDAOImplementation g = new employeeDAOImplementation();
							g.approveApp(ssnfindapp);
							break;
						case 5:
							System.out.println("Here are the Transactions so far:");
							break;
						case 6:
							System.out.println("Going back to Main Menu...");
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
			else 
			{
				do 
				{
					System.out.println("Invalid password, please retype it");
					Scanner pWSecondAttempt = new Scanner(System.in);
					String pw2 = password.next();
				} 
				while(i!=0);
			}
		}
	}
}
