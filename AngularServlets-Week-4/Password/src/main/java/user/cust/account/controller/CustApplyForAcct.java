package user.cust.account.controller;

import java.util.Date;
import java.util.Scanner;

import bank.transaction.dao.BankDaoImpl;
import log.Log;
//import not.used.Customer;
import user.cust.account.models.User;

public class CustApplyForAcct {


	private String dob;
	private double balance;
	public static Scanner scanner = new Scanner(System.in);

	public void acctForm(User user) {
		
		Log.logger("Welcome to Application For Acct");
		
	
		
		Log.logger("Enter Date of Birth: ");
		Log.logger("Format dd-mm-yyyy");
		if (scanner.hasNext()) {

			this.dob = scanner.nextLine();
		}
		
		Log.logger("Starting Balance: ");
		if (scanner.hasNext()) {

			this.balance = Double.parseDouble(scanner.nextLine());
		}
		
		// String userName, String password, int user_id, String email
		//Customer c = new Customer();----------------------------------------these two lines were commented out because ...
		//c.setUser(user);  ...  ---------- I am no-longer using Customer whatsoever.
		BankDaoImpl bankDaoImpl = new BankDaoImpl();
		bankDaoImpl.customerApplicationForAccount(user, this.dob, this.balance);

	}

}
