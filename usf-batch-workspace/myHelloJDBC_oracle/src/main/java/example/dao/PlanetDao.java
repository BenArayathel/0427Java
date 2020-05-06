package example.dao;

import models.Planet;

public interface PlanetDao {

	public void readAllPlanets(Planet p);
	
	public void insertPlanet(Planet p);
	public void readPlanet(Planet p);
	public void updatePlanet(Planet p);
	public void deletePlanet(Planet p);
	
	
	
	
}
