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
	
	@Transactional
	public List<Planet> selectAll(){

		Session ses = sesFact.openSession();
		
		List<Planet> planetList = ses.createQuery("from Planet",Planet.class).list();
		
		ses.close();
		
		return planetList;
		
	}
	
	public Planet selectById(int id) {
		Planet p = null;
		
		return p;
	}
	
	@Transactional
	public void insert(Planet p) {
		
		try(Session ses = sesFact.openSession()){
		Transaction tx = ses.beginTransaction();
		
		ses.save(p);
		
		tx.commit();
		ses.close();
		}finally {
			//session is closed
		}
		
	}
	
	@Transactional
	public void delete(Planet p) {
		
//		Session ses = sesFact.openSession();
//		Transaction tx = ses.beginTransaction();
//		
//		ses.delete(p);
//		
//		tx.commit();
//		ses.close();
		
		sesFact.getCurrentSession().delete(p); //Using contextuall sessions
		
	}
	
	public void update(Planet p) {
		
		
		
	}

}
