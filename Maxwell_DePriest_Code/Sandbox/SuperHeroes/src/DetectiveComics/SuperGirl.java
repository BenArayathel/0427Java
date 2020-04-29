package DetectiveComics;

public class SuperGirl extends Hero implements Powers{
	
	private String abilities;

	public SuperGirl(String name, String gender, boolean hasSidekick, String secretIdentity, String location) {
		super(name, gender, hasSidekick, secretIdentity, location);
		this.abilities = "Flight, Super Strength, Ice Breath";

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
