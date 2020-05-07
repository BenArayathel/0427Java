package com.example.service;

import com.example.models.Planet;

public interface PlanetService {
	
	public void destroyPlanet(Planet p);
	
	public void destroyALLPlanets();
	
	public void createSolarSystem();
	
	public Planet getMeAPlanet();
	
	public void giveRingsToPlanet(Planet p);

}
