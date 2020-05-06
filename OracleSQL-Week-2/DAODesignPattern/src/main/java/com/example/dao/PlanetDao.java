package com.example.dao;

import java.util.List;

import com.example.models.Planet;

public interface PlanetDao { //DATABASE layer - relates with communication (CRUD methods) with the database
	
	/*
	 * DAO Design Pattern - Data Access Object
	 * Purpose?
	 * 		Introduces a database layer
	 * 		Seperates Database interaction with business logic 
	 * 
	 * Provides the CRUD operations
	 */
	
	//CREATE 
	public void insertPlanet(Planet p);
	
	//UPDATE 
	public void updatePlanet(Planet p);
	
	//READ 
	public List<Planet> selectAllPlanets();
	public Planet selectPlanetByName(String name);
	
	//DELETE 
	public void deletePlanet(Planet p);

}
