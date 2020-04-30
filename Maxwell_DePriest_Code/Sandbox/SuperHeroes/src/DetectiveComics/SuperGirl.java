package DetectiveComics;

public class SuperGirl extends Hero implements Abilities{
	
	private String ability1 = "Flight";
	private String ability2 = "Super Strength";
	private String ability3 = "Heat Vision";

	public SuperGirl(String name, String gender, boolean hasSidekick, String secretIdentity, String location) {
		super(name, gender, hasSidekick, secretIdentity, location);
		addToAbilityArrayList(ability1);
		addToAbilityArrayList(ability2);
		addToAbilityArrayList(ability3);

	}

	@Override
	public boolean canFly() {
		return true;
	}

	@Override
	public boolean impenetrableSkin() {
		return true;
	}

	@Override
	public boolean superStrength() {
		return true;
	}
	
	public void printSuperGirl() {
		printHero();
		
	}
	

}
