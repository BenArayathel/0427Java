package eg;

public class Main {

	public static void main(String[] args) {
		Team t=new Team(200, "Sparts", "Coach", 100);
		Employee e = new Employee("Dave Wroblewski", 0001, "Permanent", "Software Development", new Address(001, 14626, "Rochester"), new Organization(301998, "Big Red Games Development Company", "Rochester, NY"));
		
		e.printEmployee();
		
		
		
		
		
		Player p1=new Player(100, "Sachin", 10000, t);
		//p1.printPlayer();
		
		Player p2=new Player(101, "Sourav", 10021, t);
		//p2.printPlayer();
		
		

	}
	
	

}
