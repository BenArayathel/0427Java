package hellojdbc;

import java.util.List;

import hellojdbc.model.Planet;

public interface PlanetDao {




/*
DAO - Data Access Object

Intros a database layer
Separates Database interaction with business layer

provides the crud ops

 */
  //create 
	public void insertPlanet(Planet p);
		// update
		public void updatePlanet(Planet p);
	// read
	public List<Planet> selectAllPlanets();
	public Planet selectPlanetByName(String name);
	// delete
	
	public void deletePlanet(Planet p);
	
	
	
}