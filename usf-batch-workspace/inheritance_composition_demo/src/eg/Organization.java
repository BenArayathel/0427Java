package eg;

public class Organization {

	private int oid;
	private String orgName;
	private String headOffice;
	
	public Organization() {
		// TODO Auto-generated constructor stub
	}
	
	public Organization(int oid, String orgName, String headOffice) {
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

	public void printOrg() {
		System.out.println("Organization [oid=" + oid + ", orgName=" + orgName + ", headOffice=" + headOffice + "]");
		
	}
	
	
	
	
	
}
