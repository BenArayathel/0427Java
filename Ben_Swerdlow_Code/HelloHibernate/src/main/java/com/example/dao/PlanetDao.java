package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.example.model.Planet;
import com.example.util.HibernateUtil;

public class PlanetDao {
	
	/*
	 * Additional Hibernate DAO methods
	 * save()			-> sql INSERT
	 * update()			-> sql UPDATE
	 * delete()			-> sql DELETE
	 * saveOrUpdate() 	-> sql INSERT or sql UPDATE 
	 * get()			-> sql SELECT
	 * 
	 * How do we create complex queries in hibernate?
	 * 3 different ways
	 * 	1) Hibernate Query Language (HQL)
	 * 		Creates complex queries using a combination of OOP and native SQL
	 * 		HQL targets Java objects, NOT SQL tables
	 * 	2) Criteria API
	 * 		Create complex queries programmatically, i.e. using ONLY OOP (no SQL)
	 * 		Criteria API targets Java objects, NOT SQL tables
	 * 	3) Native SQL
	 * 		Plain old SQL (avoid using this; it tightly couples you to a database format)
	 */
	
	public void insert(Planet p) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(p);
		tx.commit();
//		ses.close();
	}
	
	public void update(Planet p) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.update(p);
		tx.commit();
//		ses.close();
	}
	
	public List<Planet> selectAll() {
		Session ses = HibernateUtil.getSession();
		
		// HQL
		List<Planet> planetList = ses.createQuery("from Planet", Planet.class).list();
		
		// Criteria API
//		List<Planet> planetList = ses.createCriteria(Planet.class).list();
		
		// Native SQL
//		List<Planet> planetList = ses.createNativeQuery("SELECT * from Planet_table", Planet.class).list();
		
//		ses.close();
		
		return planetList;
	}
	
	public Planet selectByName(String name) {
		Session ses = HibernateUtil.getSession();
		
		// HQL - Hibernate Query Language
		List<Planet> planetList = ses.createQuery(
				"from Planet where name = '"+name+"'", Planet.class).list();
		// looks like SQL syntax, but note we're calling Planet, not planet_table, so it's not
		// Even though name is unique, this is the general format for getting multiple entities
		
		// CRITERIA API (syntax is deprecated, but the latest version requires more code to be written
//		List<Planet> planetList = ses.createCriteria(Planet.class).add(Restrictions.ilike("name", name)).list();
		
		// Native SQL
//		List<Planet> planetList = ses.createNativeQuery("SELECT * from Planet_table WHERE PLANET_NAME = '"+name+"'", Planet.class).list();
		
		return planetList.get(0);
	}
	
	public Planet selectById(int id) {
		Session ses = HibernateUtil.getSession();
		Planet p = ses.get(Planet.class, id);
		return p;
	}
	
	public void delete(Planet p) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.delete(p);
		tx.commit();
//		ses.close();
	}

}
