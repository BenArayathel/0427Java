package user.cust.account.controller;

import java.util.Date;
import java.util.Scanner;

import bank.transaction.dao.BankDaoImpl;
import user.cust.account.models.Customer;
import user.cust.account.models.User;

public class CustApplyForAcct {


	private String dob;
	private double balance;
	public static Scanner scanner = new Scanner(System.in);

	public void acctForm(User user) {
		
		System.out.println("Welcome to Application For Acct");
		
	
		
		System.out.println("Enter Date of Birth: ");
		System.out.println("Format dd-mm-yyyy");
		if (scanner.hasNext()) {

			this.dob = scanner.nextLine();
		}
		
		System.out.println("Starting Balance: ");
		if (scanner.hasNext()) {

			this.balance = Double.parseDouble(scanner.nextLine());
		}
		
		// String userName, String password, int user_id, String email
		Customer c = new Customer();
		c.setUser(user);
		BankDaoImpl bankDaoImpl = new BankDaoImpl();
		bankDaoImpl.customerApplicationForAccount(user, this.dob, this.balance);

	}

}
