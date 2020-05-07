package com.example.service;

import com.example.model.Planet;

public interface PlanetService {
	/*
	 * This interface lays out all the fancy operations (business logic) that implement CRUD to do useful functions
	 * other examples: addUser
	 */

	public void destroyPlanet(Planet p);
	
	public void destroyALLPlanets();
	
	public void createSolarSystem();
	
	public Planet getMeAPlanet();
	
}
