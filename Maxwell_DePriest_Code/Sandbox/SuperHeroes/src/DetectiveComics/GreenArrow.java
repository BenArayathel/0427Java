package DetectiveComics;
import java.util.ArrayList;

public class GreenArrow extends Hero implements Abilities {

	private String weapon1 = "Custom Bow";
	private String weapon2 = "Katana";
	private String weapon3 = "Trick Arrows";


	public GreenArrow(String name, String gender, boolean hasSidekick, String secretIdentity, String location) {
		super(name, gender, hasSidekick, secretIdentity, location);
		addToWeaponArrayList(weapon1);
		addToWeaponArrayList(weapon2);
		addToWeaponArrayList(weapon3);
	}
	
	public void printGreenArrow() {
		printHero();
	}

}
