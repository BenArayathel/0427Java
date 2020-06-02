package com.example.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.Planet;
import com.example.dao.PlanetDao;
//import com.example.dao.PlanetDaoImpl;

@Component("PlanService")
public class PlanetServiceImpl implements PlanetService {
	
//	PlanetDao pDao = new PlanetDaoImpl();
	// Avoid autowiring the field directly: destroys encapsulation, no setter logic, tightly couples you to spring (or the other) framework
//	@Autowired
	private PlanetDao pDao;
	
	public static int howManyServicesDidIMake = 0;
	
	@Override
	public List<Planet> getAllPlanets() {
		return pDao.selectAll();
	}

	// Constructors (for Spring)
	public PlanetServiceImpl() {
		super();
		howManyServicesDidIMake++;
		System.out.println("Inside no-args constructor (PlanetServiceImpl)");
	}

	@Autowired
	public PlanetServiceImpl(PlanetDao pDao) {
		super();
		howManyServicesDidIMake++;
		System.out.println("Inside one-args constructor (PlanetServiceImpl)");
		this.pDao = pDao;
	}
	public PlanetServiceImpl(PlanetDao pDao, int i) {
		super();
		howManyServicesDidIMake++;
		System.out.println("Inside two-args constructor (PlanetServiceImpl)");
		System.out.println(i);
		this.pDao = pDao;
	}

	// Getters/Setters (for Spring)
	public PlanetDao getpDao() {
		return pDao;
	}

//	@Autowired
	public void setpDao(PlanetDao pDao) {
		System.out.println("Inside setter (PlanetServiceImpl)");
		this.pDao = pDao;
	}

}
