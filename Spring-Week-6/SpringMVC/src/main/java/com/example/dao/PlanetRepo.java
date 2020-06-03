package com.example.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Planet;

@Transactional
@Repository("planetRepo")
public class PlanetRepo {
	
	private SessionFactory sesFact;
	
	@Autowired
	public PlanetRepo(SessionFactory sesFact) {
		this.sesFact = sesFact;
	}
	
	
	public void insert(Planet planet) {
		
		sesFact.getCurrentSession().save(planet);
		
	}
	
	public void delete(Planet planet) {
		
		sesFact.getCurrentSession().delete(planet);
		
	}
	
	public void update(Planet planet) {
		
		sesFact.getCurrentSession().update(planet);
	}
	
	public Planet selectById(int id) {
		
		Planet planet = sesFact.getCurrentSession().get(Planet.class, id);
		
		return planet;
		
	}
	
	public List<Planet> selectAll() {
		
		List<Planet> pList;
		
		pList = sesFact.getCurrentSession().createQuery("from Planet", Planet.class).list();
		
		return pList;
		
	}

}
