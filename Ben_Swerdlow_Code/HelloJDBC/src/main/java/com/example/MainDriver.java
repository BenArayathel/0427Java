package com.example;

import java.util.List;

import com.example.dao.PlanetDao;
import com.example.dao.PlanetDaoImplementation;
import com.example.model.Planet;

public class MainDriver {
	
	public static void main(String[] args) {
		
//		Planet p = new Planet(9, "Pluto", "PLANET PROPAGANDA!!!!", false, 1);
		
		PlanetDao pDaoImpl = new PlanetDaoImplementation();
		//loggy.info("Trying to connect");
		
//		pDaoImpl.insertPlanet(p);
		
		List<Planet> planets = pDaoImpl.selectAllPlanets();
		for (Planet i : planets) {
			System.out.println(i);
		}
	}

}
