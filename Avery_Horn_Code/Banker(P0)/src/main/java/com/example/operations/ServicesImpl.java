package com.example.operations;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.example.operations.UserInput;
import com.example.interfaces.Services;
import com.example.models.Customer;
import com.example.models.Employee;
import com.example.presentations.EmployeeMenu;
import com.example.presentations.MainMenu;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;

public class ServicesImpl implements Services {

	/**
	 * This class implements the services interface and handles most
	 * of the Java operations underneath the main menu
	 */
	final static Logger superlog = Logger.getLogger(ServicesImpl.class);
	private DAOImpl operator = new DAOImpl();
	
	@Override
	public void deleteAccount(String acct, Customer c) {
		superlog.setLevel(Level.INFO); 
		superlog.info("Are you certain you wish to delete account? Enter yes or no.");
		String response = UserInput.getInput();
		while (!response.equals("yes") && !response.equals("no"))
		{
			superlog.info("Please enter 'yes' or 'no' only");
			response = UserInput.getInput();
		}
		if (response.equals("yes"))
		{
            //call check to see if acct exists here
			operator.deleteAcct(acct);
			c.deletion(acct);
			MainMenu.Menu(c);
		}
		else 
		{
			MainMenu.Menu(c);
		}
	}

	@Override
	public void newAccount(Customer c) {
		superlog.info("Specify the name of the account.");
		String name = UserInput.getInput();
		String input;
		Double balance;
		superlog.info("Specify account balance.");
		input = UserInput.getInput();
		while(!input.matches("[0-9]*.[0-9]{2}"))
		{
			superlog.info("Invalid input, please re-enter in 000.00 format.");
			input = UserInput.getInput();
		}
		balance = Double.parseDouble(input);
		c.newAccount(name, balance);
		operator.newAcct(c, name);
		superlog.info("Account created.");
		MainMenu.Menu(c);
	}

	@Override
	public void newCustomer() {
		Customer c = new Customer();
		String name;
		String input;
		Double balance;
		superlog.info("Please enter your account name.");
		name = UserInput.getInput();
		c.setName(name);
		superlog.info("Please enter a password");
		c.setPassword(UserInput.getInput());
		superlog.info("Please enter the name of your first account.");
		String accname = UserInput.getInput();
		superlog.info("Please enter a starting balance for your first account.");
		input = UserInput.getInput();
		while(!input.matches("[0-9]*.[0-9]{2}"))
		{
			superlog.info("Invalid input, please re-enter in 000.00 format.");
			input = UserInput.getInput();
		}
		balance = Double.parseDouble(input);
		c.setUserid(operator.createUser(c, name, accname, balance).getUserid());
		superlog.info("Your userid is " + c.getUserid() + ". Please copy it for reference.");
		superlog.info("Account applied for. Please try logging in later. Thank you.");
	}

	@Override
	public void newEmployee() {
		Employee e = new Employee();
		superlog.info("Enter your name");
		e.setName(UserInput.getInput());
		superlog.info("Enter your email.");
		e.setEmail(UserInput.getInput());
		superlog.info("Set password.");
		e.setPassword(UserInput.getInput());
		operator.createEmployee(e);
		superlog.info("Redirecting to employee menu");
		EmployeeMenu.Menu(e);
	}

	@Override
	public Customer transferBalance(Customer c, String acc1, String acc2, Double amount) {
	c.setBalance(acc1, (c.getBalance(acc1) - amount));
	c.setBalance(acc2, (c.getBalance(acc2) + amount));
	operator.updateAcct(c.getBalance(acc1), acc1);
	operator.updateAcct(c.getBalance(acc2), acc2);
	return c;
	}

	@Override
	public Customer updateAcc(Customer c, String acc, Double amount) {
		c.setBalance(acc, (c.getBalance(acc) + amount));
		operator.updateAcct(c.getBalance(acc), acc);
		return c;
	}

	@Override
	public Customer validLogin(String user, String pass) {
		Customer c = operator.validate(user, pass);
		return c;
	}

	@Override
	public void employeeValid() {
	superlog.info("Enter your email");
	String email = UserInput.getInput();
	superlog.info("Enter your password");
	String pass = UserInput.getInput();
	Employee e = new Employee();
	e.setEmail(operator.EmployeeLogin(email, pass).getEmail());
	if(e.getEmail()!=null)
	{
		superlog.info("Validated. Redirecting you to employee menu");
		EmployeeMenu.Menu(e);
	}
	else
	{
		superlog.info("Invalid login. Please try again later");
		System.exit(0);
	}
	}

	@Override
	public Customer transferTo(Customer c, String acc1, String acc2, Double amount) {
		c.setBalance(acc1, (c.getBalance(acc1) - amount));
		operator.updateAcct(c.getBalance(acc1), acc1);
		operator.Transferrer(acc2, amount);
		return c;
	}



}
