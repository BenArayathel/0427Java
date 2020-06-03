package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.dao.PlanetDao;
import com.example.model.Planet;

public class MainDriver {
	
	public static ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	public static PlanetDao planetRepo = appContext.getBean("planetRepo",PlanetDao.class);
	
	public static void main(String[] args) {
		
		planetRepo.insertPlanet(new Planet(74,"Jupiter"));
		
		System.out.println(planetRepo.selectAllPlanets());
		
	}

}
