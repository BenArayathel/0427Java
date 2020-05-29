package com.example.dao;

import java.util.List;

import org.hibernate.Session;

import com.example.model.Planet;
import com.example.model.SolarSystem;
import com.example.util.HibernateUtil;

public class SolarDao {
	
	


	public List<SolarSystem> selectAll() {
		
		Session session = HibernateUtil.getSession();
//		List<Planet> planets = new ArrayList<>();
//		session.getTransaction();
//		
//		String hql = "from Planet_table";
//		TypedQuery<Planet> query = session.createQuery(hql, Planet.class);
//		planets = query.getResultList();
//		
//		session.getTransaction().commit();
//		session.close();
//		HibernateUtil.closeSession();
		
		List<SolarSystem> solarsystems = session.createNativeQuery("FROM Planet_table", SolarSystem.class).list();
		
		return solarsystems;
		
	}

}
