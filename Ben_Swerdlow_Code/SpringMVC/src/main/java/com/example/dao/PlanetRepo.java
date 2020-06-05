package com.example.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Planet;

@Transactional
@Repository
public class PlanetRepo {
	
	// SessionFactory is necessary for us to communicate with our DB
	private SessionFactory sesFact;
	
	// Need @Autowired because Spring needs it to inject sesFact when we call PlanetRepo 
	@Autowired
	public PlanetRepo(SessionFactory sesFact) {
		this.sesFact = sesFact;
	}

	public void insert(Planet planet) {
		sesFact.getCurrentSession().save(planet);
	}
	
	public void delete(int id) {
		Planet planet = selectById(id);
		sesFact.getCurrentSession().delete(planet);
	}
	
	public Planet selectById(int id) {
		Planet planet = sesFact.getCurrentSession().get(Planet.class, id);
		return planet;
	}
	
	public List<Planet> selectAll() {
		List<Planet> planetList = sesFact.getCurrentSession().createQuery("from Planet", Planet.class).list();
		return planetList;
	}
	
	public void update(Planet planet) {
		sesFact.getCurrentSession().update(planet);
	}
	
}
