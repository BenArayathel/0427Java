package user.cust.account.controller;

import java.util.Scanner;

import bank.transaction.dao.BankDaoImpl;
import bank.transaction.dao.Employee;
import user.cust.account.models.Customer;
import user.cust.account.models.User;

public class CustApplyForAcct {

	private String name;
	private String phone;
	private String address;
	private String city;
	private String state;
	private String zip;
	public static Scanner scanner = new Scanner(System.in);

	void acctForm(User user) {
		
		System.out.println("Welcome to Application For Acct");
		
		System.out.println("Enter Name: ");
		if (scanner.hasNext()) {

			this.name = scanner.nextLine();
		}
		
		System.out.println("Enter Phone: ");
		if (scanner.hasNext()) {

			this.phone = scanner.nextLine();
		}
		
		System.out.println("Enter Address: ");
		if (scanner.hasNext()) {

			this.address = scanner.nextLine();
		}
		
		System.out.println("Enter City: ");
		if (scanner.hasNext()) {

			this.city = scanner.nextLine();
		}
		
		System.out.println("Enter State: ");
		if (scanner.hasNext()) {

			this.state = scanner.nextLine();
		}
		
		System.out.println("Enter Zip: ");
		if (scanner.hasNext()) {

			this.zip = scanner.nextLine();
		}
		
		Customer c = new Customer(user.getUserName(), user.getPassword(), name, phone, address, city, state, zip);
		BankDaoImpl bankDaoImpl = new BankDaoImpl();
		bankDaoImpl.customerApplicationForAccount(c);

	}

}
