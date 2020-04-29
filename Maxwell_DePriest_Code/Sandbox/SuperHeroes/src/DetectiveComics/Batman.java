package DetectiveComics;

public class Batman extends Hero implements Powers {
	
	private boolean isSidekick;
	private String secretIdentity;
	private String location;

	public Batman(String name, String gender, boolean isSidekick, String secretIdentity, String location) {
		super(name, gender);
		this.location = location;
		this.secretIdentity = secretIdentity;
	}

	@Override
	public boolean isGenius() {	
		return true;
	}

	@Override
	public boolean masterFighter() {
		return true;
	}


	public String getSecretIdentity() {
		return secretIdentity;
	}

	public void setSecretIdentity(String secretIdentity) {
		this.secretIdentity = secretIdentity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public boolean getIsSidekick() {
		return isSidekick;
	}
	
	public void setIsSidekick(boolean isSidekick) {
		this.isSidekick = isSidekick;
	}
	
	public void printBatman() {
		printHero();
		System.out.println("Secret Identity: " + secretIdentity);
		System.out.println("Location: " + location);
		if(isSidekick) {
			System.out.println("Is a sidekick");
		}
		else {
			System.out.println("Is not a sidekick");
		}
	}


}
