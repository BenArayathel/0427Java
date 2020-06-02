package com.example.service;

import java.util.List;

import com.example.dao.PlanetDao;
import com.example.dao.PlanetDaoImpl;
import com.example.model.Planet;

public class PlanetServiceImpl implements PlanetService{
	
//	PlanetDao pDao = new PlanetDaoImpl();
	private PlanetDao pDao;

	@Override
	public List<Planet> getAllPlanets() {
		
		//business logic 
		
		return pDao.selectAll();
	}

	//CONSTRUCTORS
	public PlanetServiceImpl() {
		super();
		System.out.println("INSIDE NO ARGS CONSTRUCTOR");
		
		// TODO Auto-generated constructor stub
	}

	public PlanetServiceImpl(PlanetDao pDao) {
		super();
		this.pDao = pDao;
		
		System.out.println("INSIDE 1 ARGS CONSTRUCTOR");
	}
	
	public PlanetServiceImpl(PlanetDao pDao, int i) {
		super();
		this.pDao = pDao;
		
		System.out.println("INSIDE 2 ARGS CONSTRUCTOR " + i);
	}
	
	//GETTERS AND SETTERS

	public PlanetDao getpDao() {
		return pDao;
	}

	public void setpDao(PlanetDao pDao) {
		System.out.println("INSIDE SETTER");
		this.pDao = pDao;
	}
	
	

}
