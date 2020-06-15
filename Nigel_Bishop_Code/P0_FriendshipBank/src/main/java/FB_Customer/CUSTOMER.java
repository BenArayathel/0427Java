package FB_Customer;

public class CUSTOMER 
{

    private int customerID;
    private String lastName;
    private String firstName;
    private String emailAddress;
    private String socialSecurityNumber;
    private String street;
    private String city;
    private String state;
    private int zIP;
	
	public CUSTOMER() {
	
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	public int getzIP() {
		return zIP;
	}

	public void setzIP(int zIP) {
		this.zIP = zIP;
	}

	public CUSTOMER(int customerID, String lastName, String firstName, String emailAddress, String socialSecurityNumber,
			String street, String city, String state, int zIP) {
		super();
		this.customerID = customerID;
		this.lastName = lastName;
		this.firstName = firstName;
		this.emailAddress = emailAddress;
		this.socialSecurityNumber = socialSecurityNumber;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zIP = zIP;
	}

	@Override
	public String toString() {
		return "CUSTOMER [customerID=" + customerID + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", emailAddress=" + emailAddress + ", socialSecurityNumber=" + socialSecurityNumber + ", street="
				+ street + ", city=" + city + ", state=" + state + ", zIP=" + zIP + "]";
	}
	
	
}
