package DetectiveComics;

public class Robin extends Hero implements Abilities{
	
	private String weapon1 = "Bo Staff";
	private String weapon2 = "Shurikens";
	private String weapon3 = "Bolas";

	public Robin(String name, String gender,boolean hasSidekick, String secretIdentity,
			String location) {
		super(name, gender, hasSidekick, secretIdentity, location);
		addToWeaponArrayList(weapon1);
		addToWeaponArrayList(weapon2);
		addToWeaponArrayList(weapon3);

	}

	@Override
	public boolean masterFighter() {
		return true;
	}
	
	public void printRobin() {
		printHero();
		
	}
	
	
	
	

}
