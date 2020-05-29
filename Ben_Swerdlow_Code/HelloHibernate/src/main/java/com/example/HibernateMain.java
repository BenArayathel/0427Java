package com.example;

import com.example.dao.PlanetDao;
import com.example.dao.StarDao;
import com.example.model.Planet;
import com.example.model.Star;

public class HibernateMain {
	
	public static PlanetDao pdao = new PlanetDao();
	public static StarDao sdao = new StarDao();
	
	public static void main(String[] args) {
		
		insertInitialValue();
		
		System.out.println(pdao.selectByName("Earth"));
		// note that the planetId is not actually 0 because we asked Hibernate
		// to generate its own id
		System.out.println(pdao.selectByName("Mars"));
		System.out.println(pdao.selectByName("Mercury"));
		
		System.out.println(pdao.selectAll());
		
		System.out.println(sdao.selectByName("Sun"));
		System.out.println(sdao.selectByName("Siris"));
		System.out.println(sdao.selectByName("Polaris"));
		
		System.out.println(sdao.selectAll());
		
	}
	
	public static void insertInitialValue() {
		
		pdao.insert(new Planet(0, "Earth", "Bit Blue"));
		pdao.insert(new Planet(0, "Mars", "Bit Red"));
		pdao.insert(new Planet(0, "Mercury", null));
		
		sdao.insert(new Star(0, "Sun", "Bit Yellow"));
		sdao.insert(new Star(0, "Siris", "Bit Dog"));
		sdao.insert(new Star(0, "Polaris", "Bit North"));
		
	}

}
