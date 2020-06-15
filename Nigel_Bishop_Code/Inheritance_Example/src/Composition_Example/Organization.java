package Composition_Example;

public class Organization {
	private int oID;
	private String orgName;
	private String headOffice;

	public Organization() {
		// TODO Auto-generated constructor stub
	}

	public int getoID() {
		return oID;
	}

	public void setoID(int oID) {
		this.oID = oID;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getHeadOffice() {
		return headOffice;
	}

	public void setHeadOffice(String headOffice) {
		this.headOffice = headOffice;
	}

	public Organization(int oID, String orgName, String headOffice) {
		super();
		this.oID = oID;
		this.orgName = orgName;
		this.headOffice = headOffice;
	}
	
	public void printOrganization() {
		System.out.println("***  ORGANIZATION INFORMATION  ***");
		System.out.println("Organization ID: " + oID);
		System.out.println("Org. Name: " + orgName);
		System.out.println("Head Office: " + headOffice);
	}
}
