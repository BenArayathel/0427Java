package com.example.dao;

import java.util.List;

import com.example.model.Planet;

public interface PlanetDao {
	
	/*
	 * DAO DESIGN PATTERN - data access object
	 * Purpose?
	 * 		Introduces a database layer
	 * 		separates database interaction with business logic
	 * 
	 * Provides the CRUD operations
	 * 		CREATE
	 * 		READ
	 * 		UPDATE
	 * 		DELETE
	 */
	
	//CREATE
	public void insertPlanet(Planet p);
	//UPDATE
	public void updatePlanet(Planet p);
	//READ
	public List<Planet> selectAllPlanets();
	public Planet selectPlanetByName(String planetName);
	public Planet selectPlanetByID(String planetID);
	//DELETE
	public void deletePlanet();
	

}
