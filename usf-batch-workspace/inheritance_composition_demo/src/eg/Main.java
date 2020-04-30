package eg;

public class Main {

	public static void main(String[] args) {

		// team_id, teamName, coachName, noOfPlayers
		Team t=new Team(200, "Sparts", "Coach", 100);
		
		// id, name, score, Team
		Player p1=new Player(100, "Jim", 11111, t);
		//p1.printPlayer();

		Player p2=new Player(101, "Bob", 22222, t);
		//p2.printPlayer();
		
		// SET EMPLOYEE
		// String designation, String techStack, Address address, Organization organization
		Employee employee1 = new Employee();
		employee1.setDesignation("designation-Java");
		employee1.setTechStack("Java");
		
		Employee employee2 = new Employee();
		employee2.setDesignation("designation-Angular");
		employee2.setTechStack("Angular");
		
		// address
		// int aid, int zipcode, String city
		Address emp1_Address = new Address(p1.getId(), 84107, "Kingwood");
		employee1.setAddress(emp1_Address);
		
		Address emp2_Address = new Address(p2.getId(), 84107, "Kingwood");
		employee2.setAddress(emp2_Address);
		
		// Organization
		// int oid, String orgName, String headOffice
		Organization org1 = new Organization(1, "Revature", "Remote");
		Organization org2 = new Organization(2, "Client-of-Rev", "Remote");
		employee1.setOrganization(org1);
		employee2.setOrganization(org2);
		
		
//		org1.setOid(1);
//		org1.setOrgName("Revature");
//		org1.setHeadOffice("Remote");
		
//		org2.setOid(2);
//		org2.setOrgName("Client-of-Revature");
//		org2.setHeadOffice("Remote");
		
		System.out.println("Person 1");
		p1.printPlayer();
		employee1.printEmp();
		System.out.println("\n");
		
		System.out.println("Person 2");
		p2.printPlayer();
		employee2.printEmp();
		System.out.println("\n");

	}

}
