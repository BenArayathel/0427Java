package eg1;

public class PlayerMain {

	public static void main(String[] args) {
		Player p1 = new Player(101, "Cherry");
		System.out.println(p1);
		Player p2 = new Player(102, "August");
		
		if(p1.equals(p2)) {
			System.out.println("Both Players are same");
		} else {
			System.out.println("Bothe Players are different");
		}
	}
}