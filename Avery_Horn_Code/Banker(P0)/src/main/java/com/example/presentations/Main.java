package com.example.presentations;


import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.example.models.Customer;
import com.example.models.Employee;
import com.example.operations.ServicesImpl;
import com.example.operations.UserInput;

public class Main {
	/**
	 * 
	 */
	
	final static Logger superlog = Logger.getLogger(Main.class);
	final static ServicesImpl options = new ServicesImpl();
	public static void main(String[] args) {
		String input;
		int I;
		do
		{
			superlog.setLevel(Level.INFO);
			superlog.info("Welcome to Console Bank.");
			superlog.info("Please choose from one of the following options.");
			superlog.info("1. Log in as a customer");
			superlog.info("2. Log in as an employee");
			superlog.info("3. Create a new customer account");
			superlog.info("4. Create a new employee account");
			superlog.info("5. Exit system");
			System.out.println();
			superlog.info("Input the number of your choice now.");
			input = UserInput.getInput();
			while (!input.matches("[1-5]{1}"))
			{
				superlog.info("Invalid input; please enter 1-5 only.");
				input = UserInput.getInput();
			}
			I = Integer.parseInt(input);

			switch(I)
			{
			case 1:
				superlog.info("Input username");
				String username = UserInput.getInput();
				superlog.info("Input password");
				String password = UserInput.getInput();
				Customer c = options.validLogin(username, password);
				if (c.getUserid().isEmpty())
				{
					superlog.info("Invalid login. Check your account details and try again.");
					break;
				}
				else {
					superlog.info("Login validated.");
					MainMenu.Menu(c);
				break;
				}
			case 2:
				options.employeeValid();					
				break;
				
			case 3:
				options.newCustomer();
				break;
			case 4:
				options.newEmployee();
				break;
			case 5:
				superlog.info("Thank you for visiting. Goodbye.");
				System.exit(0);
				break;
			
		}
	}while(I != 5);

}
}
