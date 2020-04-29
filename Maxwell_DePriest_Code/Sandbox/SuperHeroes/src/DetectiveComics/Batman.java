package DetectiveComics;

public class Batman extends Hero implements Powers {

	private String weapons;
	// Add an array or something that goes through all of the powers and saves the ones that are true
	// and adds them to another Powers array. Build a weapons interface as well.

	public Batman(String name, String gender, boolean hasSidekick, String secretIdentity, String location) {
		super(name, gender, hasSidekick, secretIdentity, location);
		this.weapons = "Batarangs, Smoke Bombs, His Fists";
	}

	@Override
	public boolean isGenius() {	
		return true;
	}

	@Override
	public boolean masterFighter() {
		return true;
	}
	
	public void printBatman() {
		printHero();
	}

}
