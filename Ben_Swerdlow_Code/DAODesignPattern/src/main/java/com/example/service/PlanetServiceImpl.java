package com.example.service;

import com.example.dao.PlanetDao;
import com.example.dao.PlanetDaoImpl;
import com.example.model.Planet;

public class PlanetServiceImpl implements PlanetService {
	
	private PlanetDao planetDao = new PlanetDaoImpl();

	@Override
	public void destroyPlanet(Planet p) {

		//pre-database call business logic would go here
		// Maybe check to see if planet exist
		planetDao.deletePlanet();
		// post-database business logic would go here
		if (/*Delete Success is */true) {
			System.out.println("Successful Destruction!");
		}
		// respond if successful with "successful destruction"
		
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

}
