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
		
		Session ses = RobberUtil.getSession();
		
		//HQL - Hibernate Query Language
		// because we are using HQL, this doesn't work with bank_robber, the name of the table
		// in SQL..it works with 'Robber', the name of the object in our 
		// model class
		List<Robber> robberList = ses.createQuery("from Robber where name = '" + name + "'", Robber.class).list();
				
		return robberList.get(0);
		
	}

}
