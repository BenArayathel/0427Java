package user.cust.account.models;

public class Customer extends User {
	
	private int cust_id;
	
	private String email;
	
	private String name;
	private String phone;
	private String address;
	private String city;
	private String state;
	private String zip;
	
	// DEFAULT CONSTRUCTOR
	public Customer(String userName, String password, String email) {
		super(userName, password);
		// TODO Auto-generated constructor stub
		this.email = email;
	}

	// CONSTRUCTOR FOR AFTER DB
	public Customer(String userName, String password, int cust_id, String name, String phone, String address,
			String city, String state, String zip) {
		super(userName, password);
		this.cust_id = cust_id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	// CONSTRUCTOR FOR APPLICATION OF ACCOUNT
	public Customer(String userName, String password, String name, String phone, String address, String city,
			String state, String zip) {
		super(userName, password);
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
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

	
	
	
	


}
