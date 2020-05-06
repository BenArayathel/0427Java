package com.example.dao;

import java.util.List;

import com.example.model.Planet;

public interface PlanetDao {
	
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
