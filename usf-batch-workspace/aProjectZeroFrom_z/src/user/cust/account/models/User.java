package user.cust.account.models;

import java.util.Date;

public class User {

	private String userName;
	private long contact;
	private String password;

	private String user_id; // after db generates id
	private String email; // register to be customer
	private Date dob;
	private String soc;
	
	// username, password, email, contact

	
	// for log in
	public User(String userName, String password) {
		// super();
		this.userName = userName;
		this.password = password;
	}

	// CONSTRUCTOR DEFAULT
	public User(String userName, String password, String email, long contact) {
		// super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.contact = contact;
	}

	// CONSTRUCTOR database 
	public User(String userName, String password, String email, long contact, String user_id) {
		// super();
		this.userName = userName;
		this.password = password;
		this.user_id = user_id;
		this.email = email;
		this.contact = contact;
	}
	
	// AFTER DOB 
	public User(String userName, String password, String email, long contact, String user_id, Date dob) {
		super();
		this.userName = userName;
		this.contact = contact;
		this.password = password;
		this.user_id = user_id;
		this.email = email;
		this.dob = dob;
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

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {

		this.contact = contact;
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

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", user_id=" + user_id + ", email=" + email
				+ "]";
	}

}
