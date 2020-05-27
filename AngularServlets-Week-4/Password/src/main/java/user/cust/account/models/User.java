package user.cust.account.models;

import java.util.Date;

public class User {

	private String userName;
	private long contactPhone;
	private String password;

	private String user_id; // after db generates id
	private String email;
	private Date dob; // register for account
	private String soc; // register to be customer
	private Double balance;
	private int a_access;
	private String utility;

	// username, password, email, contact
	
	

	// for log in
	public User(String userName, String password) {
		// super();
		this.userName = userName;
		this.password = password;
	}

	public User() {
		super();
	}

	// CONSTRUCTOR DEFAULT
	public User(String userName, String password, String email, long contact) {
		// super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.contactPhone = contact;
	}

	// CONSTRUCTOR database
	public User(String userName, String password, String email, long contact, String user_id) {
		// super();
		this.userName = userName;
		this.password = password;
		this.user_id = user_id;
		this.email = email;
		this.contactPhone = contact;
	}

	// AFTER DOB
	public User(String userName, String password, String email, long contact, String user_id, Date dob) {
		super();
		this.userName = userName;
		this.contactPhone = contact;
		this.password = password;
		this.user_id = user_id;
		this.email = email;
		this.dob = dob;
	}

	// FOR TRANSITION TO CUSTOMER
	public User(String userName, String password, String user_id) {
		super();
		this.userName = userName;
		this.password = password;
		this.user_id = user_id;
	}

	// After getting Account
	public User(String userName, long contactPhone, String password, String user_id, String email, Date dob, String soc,
			Double balance) {
		super();
		this.userName = userName;
		this.contactPhone = contactPhone;
		this.password = password;
		this.user_id = user_id;
		this.email = email;
		this.dob = dob;
		this.soc = soc;
		this.balance = balance;
	}

	// constructor after account access ?
	public User(String userName, long contactPhone, String password, String user_id, String email, Date dob, String soc,
			Double balance, int a_access) {
		super();
		this.userName = userName;
		this.contactPhone = contactPhone;
		this.password = password;
		this.user_id = user_id;
		this.email = email;
		this.dob = dob;
		this.soc = soc;
		this.balance = balance;
		this.a_access = a_access;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSoc() {
		return soc;
	}

	public void setSoc(String soc) {
		this.soc = soc;
	}

	public long getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(long contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public int getA_access() {
		return a_access;
	}

	public void setA_access(int a_access) {
		this.a_access = a_access;
	}
	
	public String getUtility() {
		return utility;
	}
	
	public void setUtility(String utility) {
		this.utility = utility;
	}
	

	// FULL VERSION
//	@Override
//	public String toString() {
//		return "User [userName=" + userName + ", contactPhone=" + contactPhone + ", password=" + password + ", user_id="
//				+ user_id + ", email=" + email + ", dob=" + dob + ", soc=" + soc + ", balance=" + balance
//				+ ", a_access=" + a_access + "]";
//	}


	@Override
	public String toString() {
		return "Name=" + userName  
				+ ",\t soc=" + soc
				+ ", bal=" + balance
				+ ", access=" + a_access
				+ ", dob=" + dob
				+ ", _id="+ user_id 
				 
				
				+ ", pass=" + password
				 +"";
	}
	
	

}
