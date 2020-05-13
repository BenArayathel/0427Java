package com.example.presentations;

import com.example.models.*;
import com.example.operations.DAOImpl;
import com.example.operations.UserInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class EmployeeMenu {
	final static Logger superlog = Logger.getLogger(Main.class);
	private static DAOImpl operator = new DAOImpl();
	public static void Menu(Employee e)
	{	
		int I;	
		do
		{
			String input;
			superlog.setLevel(Level.INFO);
			superlog.info("Please choose from one of the following options:");
			superlog.info("1. Approve or deny a user");
			superlog.info("2. View an account");
			superlog.info("3. View transactions.");
			superlog.info("4. Exit System");
			System.out.println();
			superlog.info("Input the number of your choice now.");
			input = UserInput.getInput();
			while (!input.matches("[1-4]{1}"))
			{
				superlog.info("Invalid input; please re-enter.");
				input = UserInput.getInput();
			}
			I = Integer.parseInt(input);
			switch(I)
			{
			case 1:
				superlog.info("Returning all pending accounts.");
				List<Customer>pending = new ArrayList<Customer>();
				pending.addAll(operator.viewAllAccts());
				for(Customer c : pending)
				{
					superlog.info("Name: " + c.getName());
					superlog.info("User ID: " + c.getUserid());
					superlog.info(c.getAccounts());
				}
				System.out.println();
				superlog.info("Enter the account name of the account to approve or deny.");
				input = UserInput.getInput();
				boolean validated = false;
				for(Customer c : pending)
				{
					if (c.getAccounts().containsKey(input))
					{
						validated = true;
						Customer x = c;
						superlog.info("Enter 1 to approve or 2 to deny");
						String choice = UserInput.getInput();
						while(!choice.matches("[1-2]"))
						{
							superlog.info("Invalid input; please re-enter.");
							choice = UserInput.getInput();
						}
						if (choice.equals("1"))
						{
							operator.approveAccount(x, input);
							superlog.info("Account approved");
						}
						else
						{
							operator.denyAccount(input);
							superlog.info("Account removed from pending table");
						}
						break;
					}
				}
				if (!validated)
				{
					superlog.info("Invalid username; please try again.");
				}

				break;
				
			case 2:
				superlog.info("Enter the name of the account you want to view.");
				input = UserInput.getInput();
				HashMap<String, Double> accViewer = operator.viewAcct(input);
				superlog.info("The value of account " + input + " is " + accViewer.get(input));
				break;
				
			case 3:
				superlog.info("A simplified log of all transactions is: ");
				List<HashMap<String, Double>> trViewer = operator.transactionLog();
				superlog.info(trViewer);
				superlog.info("Observe the table directly for a more detailed log.");
				break;
			
			case 4:
				superlog.info("Logging out...");
				break;
			}
			
				
		}while (I != 4);

	}
}
