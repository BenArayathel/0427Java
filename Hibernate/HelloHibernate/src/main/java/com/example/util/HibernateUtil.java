package com.example.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	/*
	 * Hibernate's important objects and interfaces
	 * 
	 * Configuration (class)
	 * 		Configuration's job is to gather information from the hibernate.cfg.xml
	 * 		and to use that info to create a session factory 
	 * 
	 * SessionFactory (Interface)
	 * 		It's job is to create sessions and sotre information on HOW to make 
	 * 		connections to your database. Once it's ocnfigured it's immutable. 
	 * 
	 * Sesiion (Interface)
	 * 		Session manages the connection to your databse and provides,
	 * 		create, read, update and delete operations
	 * 
	 * Transaction (interface)
	 * 		It manages your transactions. 
	 * 
	 * What is ACID?
	 * 
	 * 		Atomicity - transactions are "all or nothing". You cannot have part of a transation happen.
	 * 		Consistency - only valid data gets saved, a transaction in the DB will keep the schema intact.
	 * 		Isolation - transactions cannot interfere with one another
	 * 		Durable - data will persist through issues (like power loss)
	 * 
	 */
	
	private static SessionFactory sf =
			new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	public static Session getSession() {
		return sf.openSession();
	}

}
