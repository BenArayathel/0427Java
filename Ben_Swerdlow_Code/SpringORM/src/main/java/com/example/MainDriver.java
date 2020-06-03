package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.dao.PlanetRepo;
import com.example.model.Planet;

// Get Current session

public class MainDriver {
	
	public static ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	public static PlanetRepo planetRepo = appContext.getBean("planetRepo", PlanetRepo.class);
	
	public static void main(String[] args) {
		planetRepo.insert(new Planet(0, "Earth"));
		
		System.out.println(planetRepo.selectAll());
		
		planetRepo.delete(new Planet(1, "Earth"));
		
		System.out.println(planetRepo.selectAll());
	}

}
