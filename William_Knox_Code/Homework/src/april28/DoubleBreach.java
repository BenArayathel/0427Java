package april28;

public class DoubleBreach extends Blaster {
	public DoubleBreach() {
		super(2, 0, 2);
	}
	
	public DoubleBreach(Magazine magazine) {
		this();
		this.setMagazine(magazine);
	}
	
	public void shake() {
		System.out.println("You shake the Double Breach over your head in a menacing way.");
	}
}
