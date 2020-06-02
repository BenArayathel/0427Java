package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Planet;

public class PlanetDaoImpl implements PlanetDao {

	@Override
	public List<Planet> selectAll() {
		List<Planet> pList = new ArrayList<>();
		pList.add(new Planet(1, "Earth"));
		pList.add(new Planet(2, "Mars"));
		//Mars
		//Mercury
		return pList;
	}

}
