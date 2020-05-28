package com.example;

import com.example.dao.PlanetDao;
import com.example.model.Planet;

public class HibernateMain {
	
	public static PlanetDao pdao = new PlanetDao();
	
	public static void main(String[] args) {
		
		insertInitialValue();
		
		System.out.println(pdao.selectByName("Earth"));
		// note that the planetId is not actually 0 because we asked Hibernate
		// to generate its own id
		System.out.println(pdao.selectByName("Mars"));
		System.out.println(pdao.selectByName("Mercury"));
		
		System.out.println(pdao.selectAll());
		
	}
	
	public static void insertInitialValue() {
		
		pdao.insert(new Planet(0, "Earth", "Bit Blue"));
		pdao.insert(new Planet(0, "Mars", "Bit Red"));
		pdao.insert(new Planet(0, "Mercury", null));
		
	}

}
