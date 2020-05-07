package user.cust.account.controller;

import java.util.Scanner;

import bank.transaction.dao.BankDaoImpl;
import user.cust.account.models.Customer;
import user.cust.account.models.User;

public class UserRegToBecomeCustomer {
	
	private String email;
	
	public static Scanner scanner = new Scanner(System.in);

	void acctForm(User user) {
		
		System.out.println("Welcome to Application To be Customer");
		
		System.out.println("Enter Email: ");
		if (scanner.hasNext()) {

			this.email = scanner.nextLine();
			// https://howtodoinjava.com/regex/java-regex-validate-email-address/
			if (this.email.matches("^(.+)@(.+)$")) {
				user.setEmail(this.email);
				BankDaoImpl bankDaoImpl = new BankDaoImpl();
				bankDaoImpl.userRegistrationToBecomeCustomer(user);
			} else {
				System.out.println("Sorry that is not a valid format");
			}
		}
		
		//Customer c = new Customer(user.getUserName(), user.getPassword(), name, phone, address, city, state, zip);


	}

}
