package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dao.PlanetDao;
import com.example.model.Planet;

@Component("PlanService")
public class PlanetServiceImpl implements PlanetService{
	
//	PlanetDao pDao = new PlanetDaoImpl();
	@Autowired //Don't do this, lack of encapsulation, not able to trigger setter logic, tightly couples you to Spring
	private PlanetDao pDao;
	
	public static int howManyServicesDidIMake = 0;

	@Override
	public List<Planet> getAllPlanets() {
		
		//business logic 
		
		return pDao.selectAll();
	}

	//CONSTRUCTORS
	@Autowired  //WILL BREAK IF THE ONLY OPTION GIVEN - no way for Spring to add dependency
	public PlanetServiceImpl() {
		super();
		howManyServicesDidIMake++;
		System.out.println("INSIDE NO ARGS CONSTRUCTOR");
		
		// TODO Auto-generated constructor stub
	}

//	@Autowired //CONSTRUCTOR INJECTION
	public PlanetServiceImpl(PlanetDao pDao) {
		super();
		this.pDao = pDao;
		howManyServicesDidIMake++;
		
		System.out.println("INSIDE 1 ARGS CONSTRUCTOR");
	}
	
	public PlanetServiceImpl(PlanetDao pDao, int i) {
		super();
		this.pDao = pDao;
		howManyServicesDidIMake++;
		
		System.out.println("INSIDE 2 ARGS CONSTRUCTOR " + i);
	}
	
	//GETTERS AND SETTERS

	public PlanetDao getpDao() {
		return pDao;
	}

	@Autowired //SETTER INJECTION
	public void setpDao(PlanetDao pDao) {
		System.out.println("INSIDE SETTER");
		this.pDao = pDao;
	}
	
	

}
