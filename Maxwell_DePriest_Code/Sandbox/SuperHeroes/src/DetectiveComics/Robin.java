package DetectiveComics;

public class Robin extends Hero implements Powers{
	
	private String weapons;

	public Robin(String name, String gender,boolean hasSidekick, String secretIdentity,
			String location) {
		super(name, gender, hasSidekick, secretIdentity, location);
		this.weapons = "Bo Staff, Batarangs, Bolas";

	}

	@Override
	public boolean masterFighter() {
		return true;
	}
	
	public void printRobin() {
		printHero();
		
	}
	
	
	
	

}
