package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Planet;


@Repository("planetRepo")
public class PlanetRepo {
	
	private SessionFactory sesFact;
	
	@Autowired
	public PlanetRepo(SessionFactory sesFact) {
		this.sesFact = sesFact;
	}
	
	// Tells spring this will contain a transaction
	@Transactional
	public List<Planet> selectAll() {
		Session ses = sesFact.openSession();
		// HQL
		List<Planet> planetList = ses.createQuery("from Planet", Planet.class).list();
		
		ses.close();
		
		return planetList;
	}
	
	public Planet selectById(int id) {
		Planet p=null;
		Session ses = sesFact.openSession();
		// HQL
		List<Planet> planetList = ses.createQuery("from Planet", Planet.class).list();
		
		p = planetList.get(0);
		
		ses.close();
		
		return p;
	}
	
	public void insert(Planet p) {
		// Get session
		Session ses = sesFact.openSession();
		// Make the transaction
		Transaction tx = ses.beginTransaction();
		// Save the object to the session (within the transaction we created)
		ses.saveOrUpdate(p);
		// Commit the full transaction
		tx.commit();
		// Close the session
		ses.close();
	}
	
	public void update(Planet p) {
		
	}
	
	@Transactional
	public void delete(Planet p) {
		
//		Session ses = sesFact.openSession();
//		Transaction tx = ses.beginTransaction();
//		ses.delete(p);
//		tx.commit();
//		ses.close();
		
		sesFact.getCurrentSession().delete(p);
		
	}

}
