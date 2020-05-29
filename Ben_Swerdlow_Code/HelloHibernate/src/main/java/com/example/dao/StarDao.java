package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.model.Star;
import com.example.util.HibernateUtil;

public class StarDao {
	
	public void insert(Star s) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(s);
		tx.commit();
//		ses.close();
	}
	
	public void update(Star s) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.update(s);
		tx.commit();
//		ses.close();
	}
	
	public List<Star> selectAll() {
		Session ses = HibernateUtil.getSession();
		
		// HQL
		List<Star> starList = ses.createQuery("from Star", Star.class).list();
		
		// Criteria API
//		List<Planet> planetList = ses.createCriteria(Planet.class).list();
		
		// Native SQL
//		List<Planet> planetList = ses.createNativeQuery("SELECT * from Planet_table", Planet.class).list();
		
		return starList;
	}
	
	public Star selectByName(String name) {
		Session ses = HibernateUtil.getSession();
		
		// HQL - Hibernate Query Language
		List<Star> starList = ses.createQuery(
				"from Star where name = '"+name+"'", Star.class).list();
		// looks like SQL syntax, but note we're calling Planet, not planet_table, so it's not
		// Even though name is unique, this is the general format for getting multiple entities
		
		// CRITERIA API (syntax is deprecated, but the latest version requires more code to be written
//		List<Planet> planetList = ses.createCriteria(Planet.class).add(Restrictions.ilike("name", name)).list();
		
		// Native SQL
//		List<Planet> planetList = ses.createNativeQuery("SELECT * from Planet_table WHERE PLANET_NAME = '"+name+"'", Planet.class).list();
		
		return starList.get(0);
	}
	
	public Star selectById(int id) {
		Session ses = HibernateUtil.getSession();
		Star s = ses.get(Star.class, id);
		return s;
	}
	
	public void delete(Star s) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.delete(s);
		tx.commit();
//		ses.close();
	}

}
