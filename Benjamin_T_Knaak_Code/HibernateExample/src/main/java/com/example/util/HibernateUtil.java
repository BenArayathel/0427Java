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
	 * SessionFactory (interface)
	 * 		Its job is to create sessions and store information on HOW to make connections to your database.
	 * 		Once it is configured it's immutable.
	 * 
	 * Session (Interface)
	 * 		Session manages the connection to our database and provides for its CRUD operations.
	 * 
	 * Transaction (Interface)
	 * 		It manages your transactions, natch.
	 * 
	 * What is ACID?
	 * 		Atomicity	-	Transactions are "all or nothing." You cannot have part of a transaction happen.	
	 * 		Consistency	-	Only valid data (e.g. right # of columns) gets saved. A transaction in the database will keep the schema intact.
	 * 		Isolation	-	Transactions cannot interfere with one another.
	 * 		Durability	-	Data will persist through issues (like power loss)
	 */
	
	private static SessionFactory sf = 
			new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	public static Session getSession() {
		return sf.openSession();
	}
}
