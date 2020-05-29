package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.example.model.Planet;
import com.example.util.HibernateUtil;

public class PlanetDao {
	
	/*
	 * save()				SQL INSERT
	 * update()				SQL UPDATTE
	 * delete()				SQL DELETE
	 * saveOrUpdate()		SQL INSERT OR UPDATE
	 * get()				SQL SELECT
	 * 
	 * How do we create complex queries in Hibernate?
	 * 		Hibernate Query Language (HQL)
	 * 			Creates complex queries using a combination of OOP and Native SQL
	 * 			Targets Java objects, not SQL tables
	 * 
	 * 		CriteriaAPI
	 * 			Creates complex queries programmatically.
	 * 			That is, it does so using only OOP principles.  No SQL involved.
	 * 			As with HQL, Criteria targets Java objects, not SQL tables.
	 * 
	 * 		Native SQL
	 * 			Basically SQL
	 * 			AVOID USING NATIVE SQL, because it tightly couples you to a particular database.
	 */
	
	public void insert(Planet p) {
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();
		
		sesh.save(p);
		tx.commit();
		sesh.close();
	}
	
	public void update(Planet p) {
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();
		
		sesh.update(p);
		tx.commit();
		sesh.close();
	}
	
	public Planet selectByName(String name) {
		Session sesh = HibernateUtil.getSession();
		
		//HQL - Hibernate Query Language
		// List<Planet> planetList = sesh.createQuery("from Planet where name = '" + name + "'", Planet.class).list();
		
		// CRITERIA API
		List<Planet> planetList = sesh.createCriteria(Planet.class).add(Restrictions.ilike("name", name)).list();
		
		// Native SQL
		// List<Planet> planetList = sesh.createNativeQuery("SELECT * FROM Planet_table WHERE NAME= '" + name + "'", Planet.class).list();
		
		return planetList.get(0);
	}
	
	public Planet selectById(int id) {
		Session sesh = HibernateUtil.getSession();
		
		Planet p = sesh.get(Planet.class, id);
		sesh.close();
		
		return p;
	}
	
	public List<Planet> selectAll() {
		Session sesh = HibernateUtil.getSession();
		
		List<Planet> planetList = sesh.createQuery("from Planet", Planet.class).list();
		
		return planetList;
	}
	
	public void delete(Planet p) {
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();
		
		sesh.delete(p);
		tx.commit();
		sesh.close();
	}
}
