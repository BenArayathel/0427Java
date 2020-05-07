package com.example.service;

import com.example.dao.PlanetDao;
import com.example.dao.PlanetDaoImpl;
import com.example.models.Planet;

public class PlanetServiceImpl implements PlanetService{

		private PlanetDao planetDao = new PlanetDaoImpl();
	@Override
	public void destroyPlanet(Planet p) {

		//pre-database call business logic would go here
		//check if planets exists
		planetDao.deletePlanet(p);
		//post-database business logic would go here.
		//reponse with if succesful destruction 
		
		
	}

	@Override
	public void destroyALLPlanets() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createSolarSystem() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Planet getMeAPlanet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void giveRingsToPlanet(Planet p) {
		// TODO Auto-generated method stub
		//p.isHasRings(true);
		planetDao.updatePlanet(p);
		
	}

}
