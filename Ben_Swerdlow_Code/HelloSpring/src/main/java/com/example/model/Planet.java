package com.example.model;

public class Planet {
	
	private int planetId;
	private String planetName;
	
	public Planet() {
		super();
	}
	
	public Planet(int planetId, String planetName) {
		super();
		this.planetId = planetId;
		this.planetName = planetName;
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
	
	@Override
	public String toString() {
		return "Planet [planetId=" + planetId + ", planetName=" + planetName + "]";
	}

}
