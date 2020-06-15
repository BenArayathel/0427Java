package Composition_Example;

public class Employee extends Person{
	
	private int eID;
	private String position;
	private Address address;
	private Organization organization;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public int geteID() {
		return eID;
	}

	public void seteID(int eID) {
		this.eID = eID;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Employee(int eID, String position, Address address, Organization organization) {
		super();
		this.eID = eID;
		this.position = position;
		this.address = address;
		this.organization = organization;
	}
	
	public void printEmployee() {
		System.out.println("***  EMPLOYEE INFORMATION  ***");
		System.out.println("Organization ID: " + eID);
		System.out.println("Org. Name: " + position);
		this.address.printAddress();
		this.organization.printOrganization();
	}
}
