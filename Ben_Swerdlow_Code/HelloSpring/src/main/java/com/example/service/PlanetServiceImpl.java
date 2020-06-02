package com.example.service;


import java.util.List;

import com.example.model.Planet;
import com.example.dao.PlanetDao;
//import com.example.dao.PlanetDaoImpl;

public class PlanetServiceImpl implements PlanetService {
	
//	PlanetDao pDao = new PlanetDaoImpl();
	private PlanetDao pDao;
	
	@Override
	public List<Planet> getAllPlanets() {
		return pDao.selectAll();
	}

	// Constructors (for Spring)
	public PlanetServiceImpl() {
		super();
		System.out.println("Inside no-args constructor (PlanetServiceImpl)");
	}

	public PlanetServiceImpl(PlanetDao pDao) {
		super();
		System.out.println("Inside one-args constructor (PlanetServiceImpl)");
		this.pDao = pDao;
	}

	public PlanetServiceImpl(PlanetDao pDao, int i) {
		super();
		System.out.println("Inside two-args constructor (PlanetServiceImpl)");
		System.out.println(i);
		this.pDao = pDao;
	}

	// Getters/Setters (for Spring)
	public PlanetDao getpDao() {
		return pDao;
	}

	public void setpDao(PlanetDao pDao) {
		System.out.println("Inside setter (PlanetServiceImpl)");
		this.pDao = pDao;
	}

}
