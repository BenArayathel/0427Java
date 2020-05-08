package user.cust.account.models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
	
	private int cust_id;
	
	private List<Account> acct = new ArrayList<>();  
	
	private String name;
	private String phone;
	private String address;
	private String city;
	private String state;
	private String zip;
	
	// DEFAULT CONSTRUCTOR
	// basic User & Customer necessities
	public Customer(String userName, String password, String user_id, String email) {
		super(userName, password, user_id, email);
		// TODO Auto-generated constructor stub
		//this.email = email;
	}
	
	/**
	 * THIS ONE IS JUST TEMPORARY
	 */
	// CONSTRUCTOR FOR AFTER foe employee check since there is no db to create a account id.
	public Customer(String userName, String password, String user_id, String email, String name, String phone, String address,
			String city, String state, String zip) {
		super(userName, password, user_id, email);
		//this.cust_id = cust_id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	

	// CONSTRUCTOR FOR AFTER DB generations of customer_id
	// 
	public Customer(String userName, String password, String user_id, String email, int cust_id, String name, String phone, String address,
			String city, String state, String zip) {
		super(userName, password, user_id, email);
		this.cust_id = cust_id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	


	// CONSTRUCTOR FOR APPLICATION OF ACCOUNT
	public Customer(String userName, String password, String user_id, String email, int cust_id, String name, String phone, String address,
			String city, String state, String zip, List<Account> acct) {
		super(userName, password, user_id, email);
		this.cust_id = cust_id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.acct = acct;
	}
	

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public List<Account> getAcct() {
		return acct;
	}

	public void setAcct(List<Account> acct) {
		this.acct = acct;
	}

	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", acct=" + acct + ", name=" + name + ", phone=" + phone + ", address="
				+ address + ", city=" + city + ", state=" + state + ", zip=" + zip + "]";
	}

	
	
	
	


}
