package eg;

public class Main {

	public static void main(String[] args) {
		Team t=new Team(200, "Sparts", "Coach", 100);
		
		Player p1=new Player(100, "Sachin", 10000, t);
		p1.printPlayer();
		
		Player p2=new Player(101, "Sourav", 10021, t);
		p2.printPlayer();

	}

}
