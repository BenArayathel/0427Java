package DetectiveComics;

public class Superman extends Hero implements Powers {
	
	private String abilities;

	
	public Superman(String name, String gender, boolean hasSidekick, String secretIdentity, String location) {
		super(name, gender, hasSidekick, secretIdentity, location);
		this.abilities = "Ice Breath, Heat Vision, Super Strength";
		
		
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



	public void printSuperman() {
		printHero();
		
	}


}
