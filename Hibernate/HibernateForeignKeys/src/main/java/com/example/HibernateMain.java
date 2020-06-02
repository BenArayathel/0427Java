package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.dao.MoonDao;
import com.example.dao.PlanetDao;
import com.example.model.Moon;
import com.example.model.Planet;
import com.example.util.HibernateUtil;

public class HibernateMain {
	
	public static PlanetDao pdao = new PlanetDao();
	public static MoonDao mdao = new MoonDao();
	
	public static void main(String[] args) {
		
		insertIntialValue();
		
//		System.out.println(pdao.selectByName("Earth"));
//		System.out.println(pdao.selectByName("Mars"));
//		System.out.println(pdao.selectByName("Mercury"));
		
		System.out.println(pdao.selectAll());
		
		HibernateUtil.closeSession();
		
	}
	
	public static void insertIntialValue() {
		
//		pdao.insert(new Planet(0, "Earth", "Bit Blue"));
//		pdao.insert(new Planet(0, "Mars", "Bit Red"));
//		pdao.insert(new Planet(0, "Mercury", null));
		
		Planet Mercury = new Planet(0, "Mercury", "Closest to the Sun");
		Planet Pluto = new Planet(0,"Pluto","Furthest from the Sun");
		Planet Mars = new Planet(0,"Mars", "A bit red");
		Planet Earth = new Planet(0,"Earth", "Very Blue");
		
		Moon Phobos = new Moon(0, "Phobos", Mars);
		Moon Deimos = new Moon(0, "Deimos", Mars);
		Moon Moon = new Moon(0, "The Moon", Earth);
		
		List<Moon> moonsOfMars = new ArrayList<>();
		List<Moon> moonsOfEarth = new ArrayList<>();
		
		moonsOfMars.add(Phobos);
		moonsOfMars.add(Deimos);
		moonsOfEarth.add(Moon);
		
		Mars.setMoons(moonsOfMars);
		Earth.setMoons(moonsOfEarth);
		
		pdao.insert(Pluto);
		pdao.insert(Mars);
		pdao.insert(Earth);
		pdao.insert(Mercury);
		
		mdao.insert(Phobos);
		mdao.insert(Deimos);
		mdao.insert(Moon);
	}

}
