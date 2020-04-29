package eg;

public class Organization {

	private int oid;
	private String orgName;
	private String headOffice;
	
	public Organization() {
		//Empty constructor
	}

	public Organization(int oid, String orgName, String headOffice) {
		super();
		this.oid = oid;
		this.orgName = orgName;
		this.headOffice = headOffice;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
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
	
	public String printOrganization() {
//		System.out.println("Organization ID: " + oid);
//		System.out.println("Name: " + orgName);
//		System.out.println("Head Office Location: " + headOffice);
		return "\nOrganization ID: " + oid +
				"\nName: " + orgName +
				"\nHead Office Location: " + headOffice;
	}
	
}
