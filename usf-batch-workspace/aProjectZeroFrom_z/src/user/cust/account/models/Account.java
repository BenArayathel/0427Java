package user.cust.account.models;

import java.util.List;

public class Account extends Customer {
	
	private int acct_id;
	private double balance;
	private List<String> list_of_transactions = null;

	// DEFAULT CONSTRUCTOR
	// basic User & Customer necessities  &&
	// starting Balance
	public Account(String userName, String password, String user_id, String email, int cust_id, String name, String phone, String address,
			String city, String state, String zip, double balance) {
		super(userName, password, user_id, email, cust_id, name, phone, address, city, state, zip);
		this.balance = balance;
		// TODO Auto-generated constructor stub
		// String userName, String password, int user_id, String email, String name, String phone, String address,
		// String city, String state, String zip
	}
	
	// CONSTRUCTOR FOR AFTER DATABASE GENERATES ACCOUNT ID
	public Account(String userName, String password, String user_id, String email, int cust_id, String name, String phone, String address,
			String city, String state, String zip, int acct_id, double balance) {
		super(userName, password, user_id, email, cust_id, name, phone, address, city, state, zip);
		this.acct_id = acct_id;
		this.balance = balance;
	}

	public int getAcct_id() {
		return acct_id;
	}

	public void setAcct_id(int acct_id) {
		this.acct_id = acct_id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [acct_id=" + acct_id + ", balance=" + balance + "]";
	}

	

}
