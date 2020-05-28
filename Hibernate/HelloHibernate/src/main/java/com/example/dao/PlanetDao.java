package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.example.model.Planet;
import com.example.util.HibernateUtil;

public class PlanetDao {
	
	/*
	 * save() 				results in a sql insert
	 * update()				results in a sql update
	 * delete()				results in a sql delete
	 * saveOrUpdate()		results in a sql insert or sql update
	 * get()				results in a sql select
	 * 
	 * How do we create complex queries in Hibernate?
	 * 
	 * 3 different way to create complex queries:
	 * 		Hibernate Query Language (HQL)
	 * 			Creates complex queries using a combination of OOP and Native SQL 
	 * 			HQL targets java objects NOT SQL tables
	 * 
	 * 		CriteriaAPI
	 * 			Create complex queries PROGRAMMATICALLY. That is, it does so using 
	 * 			ONLY OOP principles. No SQL involved;
	 * 			Criteria targets java objects NOT SQL tables
	 * 
	 * 		Native SQL
	 * 			Basically SQL 
	 * 			AVOID using Native SQL, because it tightly couples you to a database
	 */
	
	public void insert(Planet p) {
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(p);
		
		tx.commit();
		ses.close();
	}
	
	public void update(Planet p) {
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(p);
		
		tx.commit();
		ses.close();
		
	}
	
	public List<Planet> selectAll() {
		return null;
		
	}
	
	public void delete(Planet p) {
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.delete(p);
		
		tx.commit();
		ses.close();
		
	}
	
	public Planet selectByName(String name) {
		Session ses = HibernateUtil.getSession();
		
		//HQL - Hibernate Query Language
//		List<Planet> planetList = ses.createQuery("from Planet where name = '" + name + "'", Planet.class).list();
		
		//CRITERIA API
//		List<Planet> planetList = ses.createCriteria(Planet.class).add(Restrictions.ilike("name", name)).list();
		
		//Native SQL
		List<Planet> planetList = ses.createNativeQuery("SELECT * FROM Planet_table WHERE planet_name = '" + name + "'", Planet.class).list();
		
		return planetList.get(0);
	}
	
	public Planet selectById(int id) {
		Session ses = HibernateUtil.getSession();
		
		Planet p = ses.get(Planet.class, id);
		
		return p;
	}
}
