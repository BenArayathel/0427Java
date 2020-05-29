package com.example;

import com.example.dao.BallplayerDao;
import com.example.dao.PlanetDao;
import com.example.model.Ballplayer;
import com.example.model.Planet;

public class HibernateMain {
	
	public static PlanetDao pdao = new PlanetDao();
	public static BallplayerDao bdao = new BallplayerDao();

	public static void main(String[] args) {
		insertInitialValue();
		
		System.out.println(pdao.selectByName("Earth"));
		System.out.println(pdao.selectAll());
		System.out.println(bdao.selectByName("Joe Mauer"));
		System.out.println(bdao.selectAll());
		
		updateValues();
		
		Ballplayer bp = bdao.selectByName("Joe Mauer");
		System.out.println("Name: " + bp.getName() + ", Slash: " + bp.getAvg() + "/" + bp.getSlg() + "/" + bp.getOps() + ", HR: " + bp.getHr());
	}
	
	public static void insertInitialValue() {
		pdao.insert(new Planet(0, "Earth", "bit blue, innit?"));
		bdao.insert(new Ballplayer(0, "Joe Mauer", 0, 0, 0, 0));
	}
	
	public static void updateValues() {
		Ballplayer b = bdao.selectByName("Joe Mauer");
		bdao.updateAvg(b, 0.365);
		bdao.updateSlg(b, 0.587);
		bdao.updateOps(b, 1.031);
		bdao.updateHr(b,  28);
	}

}
