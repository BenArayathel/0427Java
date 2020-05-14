/*
	The Model Class
		Plain container for constructing an Planet object; doesn't implement any other behavior worth stressing.
		Contains private fields, constructors, getters, setters.
*/
public class Planet {

	private int planetID;
	private String planetName;
	private boolean hasRings;
	private int numberOfMoons;
	private String slogan;

	public Planet() {
	}

	public Planet(int planetID, String planetName, boolean hasRings, int numberOfMoons, String slogan) {
		this.planetID = planetID;
		this.planetName = planetName;
		this.hasRings = hasRings;
		this.numberOfMoons = numberOfMoons;
		this.slogan = slogan;
	}

	public int getPlanetID() {
		return planetID;
	}

	public void setPlanetID(int planetID) {
		this.planetID = planetID;
	}

	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}

	public boolean isHasRings() {
		return hasRings;
	}

	public void setHasRings(boolean hasRings) {
		this.hasRings = hasRings;
	}

	public int getNumberOfMoons() {
		return numberOfMoons;
	}

	public void setNumberOfMoons(int numberOfMoons) {
		this.numberOfMoons = numberOfMoons;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	@Override
	public String toString() {
		return "Planet {" +
				"planetID=" + planetID +
				", planetName='" + planetName + '\'' +
				", hasRings=" + hasRings +
				", numberOfMoons=" + numberOfMoons +
				", slogan='" + slogan + '\'' +
				'}';
	}
}
