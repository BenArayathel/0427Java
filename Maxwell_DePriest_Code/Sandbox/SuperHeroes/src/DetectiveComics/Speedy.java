package DetectiveComics;

public class Speedy extends Hero implements Abilities{
	
	private String weapon1 = "Bow";
	private String weapon2 = "Trick Arrows";
	private String weapon3 = "Throwing Knives";

	public Speedy(String name, String gender, boolean hasSidekick, String secretIdentity, String location) {
		super(name, gender, hasSidekick, secretIdentity, location);
		addToWeaponArrayList(weapon1);
		addToWeaponArrayList(weapon2);
		addToWeaponArrayList(weapon3);
	}
	
	public void printSpeedy() {
		printHero();
	}

}
