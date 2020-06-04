package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Planet;

//@Transactional
@Repository("planetRepo")
public class PlanetRepo {
	
	private SessionFactory sesFact;

	@Autowired
	public PlanetRepo(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}
	
	//METHODS
	
	@Transactional
	public void insert(Planet planet) {
		
//		Session ses = sesFact.openSession();
//		Transaction tx = ses.beginTransaction();
//		
//		ses.save(planet);
//		
//		tx.commit();
//		ses.close();
		
		
		sesFact.getCurrentSession().save(planet);
		
	}
	
	@Transactional
	public void update(Planet planet) {
		
//		Session ses = sesFact.openSession();
//		Transaction tx = ses.beginTransaction();
//		
//		ses.update(planet);
//		
//		tx.commit();
//		ses.close();

		
		sesFact.getCurrentSession().update(planet);
		
	}
	
	@Transactional
	public Planet selectById(int id) {
//		Session ses = sesFact.openSession();
//		
//		Planet planet = ses.get(Planet.class, id);
//		
//		ses.close();
		
		
		Planet planet = sesFact.getCurrentSession().get(Planet.class, id);
		
		return planet;
		
	}
	
	@Transactional
	public List<Planet> selectAll(){
		
//		Session ses = sesFact.openSession();
//		
//		List<Planet> pList = ses.createQuery("from Planet", Planet.class).list();
		
		List<Planet> pList;
		
		pList = sesFact.getCurrentSession().createQuery("from Planet", Planet.class).list();
		return pList;
	}
	
	
	
	
	
}
