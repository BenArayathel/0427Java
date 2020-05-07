package com.example;

import java.util.List;

import com.example.dao.PlanetDaoImpl;
import com.example.model.Planet;

public class MainDriver {
	
	public static void main(String[] args) {
		
		PlanetDaoImpl pDaoImpl = new PlanetDaoImpl();
		
		Planet p = new Planet(9,"Pluto",false,1,"I AM A PLANET!");
		
		pDaoImpl.insertPlanet(p);
		
		
		List<Planet> planets = pDaoImpl.selectAllPlanets();
		for(Planet i: planets) {
			System.out.println(i);
		}
	}

}
