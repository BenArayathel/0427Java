package com;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class RobberDao {
	
	public void insert(Robber robber) {
		
		Session session = RobberUtil.getSession();
		Transaction trans = session.beginTransaction();
		
		session.save(robber);
		
		trans.commit();
		session.close();
	}
	
	public Robber selectByName(String name) {
		
		Session session = RobberUtil.getSession();
		
		//HQL - Hibernate Query Language
		
		// because we are using HQL, this doesn't work with bank_robber, the name of the table
		// in SQL..it works with 'Robber', the name of the object in our 
		// model class
		List<Robber> robberList = session.createQuery("from Robber where name = '" + name + "'", Robber.class).list();
				
		return robberList.get(0);
		
	}
	
	public void update(Robber robber) {
		
		Session session = RobberUtil.getSession();
		Transaction trans = session.beginTransaction();
		
		session.update(robber);
		
		trans.commit();
		session.close();
		
	}
	
	public List<Robber> selectAll() {
		
		Session session = RobberUtil.getSession();
		
		// would it be better to do this differently?
		// depreciated
//		List<Robber> robberList = session.CriteriaQuery(Robber.class).list();
		
		// my first idea
//		List<Robber> robberList = session.createQuery("from Robber", Robber.class).list();
		
		// using different syntax (JPQL?)
		List<Robber> robberList = session.createQuery("SELECT a FROM Robber a", Robber.class).getResultList();
		
		return robberList;
		
	}
	
	public void delete(Robber robber) {
		
		Session ses = RobberUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.delete(robber);
		
		tx.commit();
		ses.close();
		
	}
	
	public Robber selectById(int id) {
		Session session = RobberUtil.getSession();
		
		Robber robber = session.get(Robber.class, id);
		
		return robber;
	}

}
