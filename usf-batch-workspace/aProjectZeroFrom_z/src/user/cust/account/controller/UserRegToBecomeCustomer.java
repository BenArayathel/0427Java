package user.cust.account.controller;

import java.util.Scanner;

import bank.transaction.dao.BankDaoImpl;
import user.cust.account.models.Customer;
import user.cust.account.models.User;

public class UserRegToBecomeCustomer {
	
	private String dob;
	private String soc;
	
	public static Scanner scanner = new Scanner(System.in);
	
	void acctForm(User user) {
		
		System.out.println("Welcome to Application To be Customer");
		
		System.out.println("Enter Social Sec.  ***-**-****");
		if (scanner.hasNext()) {

			this.soc = scanner.nextLine();
			
			

			
			//user.setEmail(this.email);
			BankDaoImpl bankDaoImpl = new BankDaoImpl();
			user.setSoc(soc);
			
			if (bankDaoImpl.userRegistrationToBecomeCustomer(user)) {

				System.out.println("Application called back to FRONT !!");
				//CustOptionsDirectory co = new CustOptionsDirectory();
				//Customer c = new Customer();
				//co.select(customer);
			} else {
				System.out.println("Sorry that is not a valid format");
			}
		}
		
		//Customer c = new Customer(user.getUserName(), user.getPassword(), name, phone, address, city, state, zip);


	}

	void acctForm1(User user) {
		
		System.out.println("Welcome to Application To be Customer");
		
		System.out.println("Enter Date of Birth in dd.mm.yyyy");
		if (scanner.hasNext()) {

			this.dob = scanner.nextLine();
			
			
			// https://howtodoinjava.com/regex/java-regex-validate-email-address/
			//this.email.matches("^(.+)@(.+)$")
			
			//user.setEmail(this.email);
			BankDaoImpl bankDaoImpl = new BankDaoImpl();
			
			
			if (bankDaoImpl.userRegistrationToBecomeCustomer(user, this.dob)) {

				System.out.println("Application called back to FRONT !!");
			} else {
				System.out.println("Sorry that is not a valid format");
			}
		}
		
		//Customer c = new Customer(user.getUserName(), user.getPassword(), name, phone, address, city, state, zip);


	}

}
