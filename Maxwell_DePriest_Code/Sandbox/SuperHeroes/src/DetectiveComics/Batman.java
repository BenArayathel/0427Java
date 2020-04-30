package DetectiveComics;
import java.util.ArrayList;

public class Batman extends Hero implements Abilities {

	private String weapon1 = "Batarang";
	private String weapon2 = "Batclaw";
	private String weapon3 = "Batmobile";


	public Batman(String name, String gender, boolean hasSidekick, String secretIdentity, String location) {
		super(name, gender, hasSidekick, secretIdentity, location);
		addToWeaponArrayList(weapon1);
		addToWeaponArrayList(weapon2);
		addToWeaponArrayList(weapon3);
	}

//	@Override
//	public boolean isGenius() {	
//		return true;
//	}
//
//	@Override
//	public boolean masterFighter() {
//		return true;
//	}
	
	public void printBatman() {
		printHero();
	}

}
