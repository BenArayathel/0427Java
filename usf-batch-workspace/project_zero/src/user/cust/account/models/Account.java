package user.cust.account.models;

public class Account extends Customer {
	
	private int acct_id;
	private double balance;

	// DEFAULT CONSTRUCTOR
	public Account(String userName, String password, int cust_id, String name, String phone, String address,
			String city, String state, String zip) {
		super(userName, password, cust_id, name, phone, address, city, state, zip);
		// TODO Auto-generated constructor stub
	}
	
	public Account(String userName, String password, int cust_id, String name, String phone, String address,
			String city, String state, String zip, int acct_id, double balance) {
		super(userName, password, cust_id, name, phone, address, city, state, zip);
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
