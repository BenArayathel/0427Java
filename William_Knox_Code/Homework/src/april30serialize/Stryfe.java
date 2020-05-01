package april30serialize;

public class Stryfe extends Blaster {
	
	public Stryfe() {
		super(1, 0, 1);
	}
	
	public Stryfe(Magazine magazine) {
		this();
		this.setMagazine(magazine);
	}
	
	public void twirl() {
		System.out.println("You twirl the Stryfe in your hand.");
	}
}
