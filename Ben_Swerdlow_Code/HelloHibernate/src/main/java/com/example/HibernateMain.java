package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.dao.MoonDao;
import com.example.dao.PlanetDao;
import com.example.dao.StarDao;
import com.example.model.Moon;
import com.example.model.Planet;
//import com.example.model.Star;
import com.example.util.HibernateUtil;

public class HibernateMain {
	
	public static PlanetDao pdao = new PlanetDao();
	public static StarDao sdao = new StarDao();
	public static MoonDao mdao = new MoonDao();
	
	public static void main(String[] args) {
		
		insertInitialValue();
		
//		System.out.println(pdao.selectByName("Earth"));
		// note that the planetId is not actually 0 because we asked Hibernate
		// to generate its own id
//		System.out.println(pdao.selectByName("Mars"));
//		System.out.println(pdao.selectByName("Mercury"));
		
		System.out.println(pdao.selectAll());
		System.out.println(mdao.selectAll());
		
//		System.out.println(sdao.selectByName("Sun"));
//		System.out.println(sdao.selectByName("Siris"));
//		System.out.println(sdao.selectByName("Polaris"));
//		
//		System.out.println(sdao.selectAll());
		
		HibernateUtil.closeSession();
		
	}
	
	public static void insertInitialValue() {
		
//		pdao.insert(new Planet(0, "Earth", "Bit Blue"));
//		pdao.insert(new Planet(0, "Mars", "Bit Red"));
//		pdao.insert(new Planet(0, "Mercury", null));
		
//		sdao.insert(new Star(0, "Sun", "Bit Yellow"));
//		sdao.insert(new Star(0, "Siris", "Bit Dog"));
//		sdao.insert(new Star(0, "Polaris", "Bit North"));
		
		Planet Mercury = new Planet(0, "Mercury", "Closest to the Sun");
		Planet Pluto = new Planet(0, "Pluto", "Furthest from the Sun");
		Planet Mars = new Planet(0, "Mars", "A bit Red");
		Planet Earth = new Planet(0, "Earth", "Very Blue");

		Moon Phobos = new Moon(0, "Phobos", Mars);
		Moon Deimos = new Moon(0, "Deimos", Mars);
		Moon theMoon = new Moon(0, "The Moon", Earth);
		
		List<Moon> moonsOfMars = new ArrayList<>();
		List<Moon> moonsOfEarth = new ArrayList<>();
		
		moonsOfMars.add(Phobos);
		moonsOfMars.add(Deimos);
		
		moonsOfEarth.add(theMoon);
		
		Mars.setMoons(moonsOfMars);
		Earth.setMoons(moonsOfEarth);
		
		pdao.insert(Mercury);
		pdao.insert(Pluto);
		pdao.insert(Mars);
		pdao.insert(Earth);
		
		mdao.insert(Phobos);
		mdao.insert(Deimos);
		mdao.insert(theMoon);
		
		
	}

}
