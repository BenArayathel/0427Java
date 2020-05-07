package com.example.model;

public class Planet {
	
	private int planetId;
	private String planetName;
	private boolean hasRings;
	private int numberOfMoons;
	private String slogan;
	
	public Planet() {
		super();
	}

	public Planet(int planetId, String planetName, boolean hasRings, int numberOfMoons, String slogan) {
		super();
		this.planetId = planetId;
		this.planetName = planetName;
		this.slogan = slogan;
		this.hasRings = hasRings;
		this.numberOfMoons = numberOfMoons;
	}

	public int getPlanetId() {
		return planetId;
	}

	public void setPlanetId(int planetId) {
		this.planetId = planetId;
	}

	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
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

}
