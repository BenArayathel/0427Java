package com.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.dao.PlanetDao;
import com.example.dao.SolarDao;
import com.example.model.Planet;
import com.example.model.SolarSystem;

public class HibernateMain {
	
	public static PlanetDao pdao = new PlanetDao();
	public static SolarDao sdao = new SolarDao();
	public static List<SolarSystem> systems = new ArrayList<>();
	
	public static void main(String[] args) {
		
		insertIntialValue();
		
		System.out.println(pdao.selectByName("Earth"));
		System.out.println(pdao.selectByName("Mars"));
		System.out.println(pdao.selectByName("Mercury"));
		
		System.out.println("\n\n");
		for (SolarSystem sol : systems) {
			
			System.out.println(sol.toString());
			
		}
		
	}
	
	public static void insertIntialValue() {
		
		pdao.insert(new Planet(0, "Earth", "Bit Blue"));
		pdao.insert(new Planet(0, "Mars", "Bit Red"));
		pdao.insert(new Planet(0, "Mercury", null));
		
		// SolarSystem(String name, int id, int sunCount, String sizeDescription)
		
		SolarSystem s = new SolarSystem("system1", 1, 5, "big");
		SolarSystem s2 = new SolarSystem("system22", 2, 25, "bigger");
		SolarSystem s3 = new SolarSystem("system33", 3, 125, "biggest");
		
		systems.add(s);
		systems.add(s2);
		systems.add(s3);
	}

}
