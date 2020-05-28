package com.example;

import com.example.dao.PlanetDao;
import com.example.model.Planet;

public class HibernateMain {
	
	public static PlanetDao pdao = new PlanetDao();
	
	public static void main(String[] args) {
		
		insertIntialValue();
		
		System.out.println(pdao.selectByName("Earth"));
		System.out.println(pdao.selectByName("Mars"));
		System.out.println(pdao.selectByName("Mercury"));
		
	}
	
	public static void insertIntialValue() {
		
		pdao.insert(new Planet(0, "Earth", "Bit Blue"));
		pdao.insert(new Planet(0, "Mars", "Bit Red"));
		pdao.insert(new Planet(0, "Mercury", null));
	}

}
