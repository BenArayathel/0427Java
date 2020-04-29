package eg;

public class Employee extends Person {

	private String designation;
	private String techStack;
	private Address address;
	private Organization organization;
	
	
	public Employee() {
	}


	public Employee(String designation, String techStack, Address address, Organization organization) {
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


	public void printEmp() {
		System.out.println(
				"Employee [designation=" + designation 
				+ ", techStack=" + techStack 
				+ ", address=");
				this.address.printAddress();
				System.out.println(", organization="); 
				this.organization.printOrg();
				System.out.println("]");
		
	}
	
	
}
