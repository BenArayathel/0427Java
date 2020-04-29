package eg;

public class Address {

	private int aid;
	private int zipcode;
	private String city;
	
	public Address() {
		//Empty constructor
	}
	
	public Address(int aid, int zipcode, String city) {
		super();
		this.aid = aid;
		this.zipcode = zipcode;
		this.city = city;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String printAddress() {
//		System.out.println("Address ID: " + aid);
//		System.out.println("Zip Code: " + zipcode);
//		System.out.println("City: " + city);
		return "\nAddress ID: " + aid + 
				"\nZip Code: " + zipcode +
				"\nCity: " + city;
	}
	
}
