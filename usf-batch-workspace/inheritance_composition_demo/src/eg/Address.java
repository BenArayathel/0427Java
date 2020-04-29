package eg;

public class Address {

	private int aid;
	private int zipcode;
	private String city;
	
	
	public Address() {
	}


	public Address(int aid, int zipcode, String city) {
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


	public void printAddress() {
		System.out.println("Address [aid=" + aid + ", zipcode=" + zipcode + ", city=" + city + "]");
		
	}
	
	
}
