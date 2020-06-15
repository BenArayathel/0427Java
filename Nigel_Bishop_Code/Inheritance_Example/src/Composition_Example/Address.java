package Composition_Example;

public class Address {
	
	private int aID;
	private String street;
	private String city;
	private String state;
	private int zipcode;
	
	public Address() {
		// TODO Auto-generated constructor stub
	}

	public int getaID() {
		return aID;
	}

	public void setaID(int aID) {
		this.aID = aID;
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

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public Address(int aID, String street, String city, String state, int zipcode) {
		super();
		this.aID = aID;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}
	
	public void printAddress() {
		System.out.println("***  ADDRESS INFORMATION  ***");
		System.out.println("Address ID: " + aID);
		System.out.println("Street: " + street);
		System.out.println("City: " + city);
		System.out.println("State: " + state);
		System.out.println("ZipCode: " + zipcode);
	}
}
