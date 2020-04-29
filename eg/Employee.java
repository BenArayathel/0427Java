package eg;

public class Employee extends Person {

	private String designation;
	private String techStack;
	private Address address;
	private Organization organization;
	
	public Employee() {
		//Empty constructor
	}
	
	public Employee(String inPersonName, int inPersonId, String designation, String techStack, Address address, Organization organization) {
		super(inPersonId, inPersonName);
		this.designation = designation;
		this.techStack = techStack;
		this.address = address;
		this.organization = organization;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getTechStack() {
		return techStack;
	}

	public void setTechStack(String techStack) {
		this.techStack = techStack;
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
	
	public void printEmployee() {
		super.printPerson();
		System.out.println("Employee Designation is: " + designation);
		System.out.println("Tech Stack: " +techStack);
		System.out.println("Address: " + address.printAddress());
		System.out.println("Organization: " + organization.printOrganization());
		
	}
	
}
