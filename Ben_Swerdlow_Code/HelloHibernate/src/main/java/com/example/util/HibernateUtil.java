package com.example.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	/*
	 * Hibernate's imporatant objects and interfaces
	 * 
	 * Configuration (Class)
	 * 	- Configuration gathers information from teh hibernate.cfg.xml and to use that info to create a session factory
	 * SessionFactor (Interface)
	 * 	- Create sessions and store information on HOW to make connections to your database.
	 * 	- Once it's configured, it's immutable
	 * Session (Interface)
	 * 	- Session manages the connection to your database and provides CRUD operations
	 * Transaction (Interface)
	 * 	- It manages your transactions.
	 * 
	 * What is ACID?
	 * 	> Atomicity - Every transaction is a single entity that fails or succeeds. There are no partial transactions.
	 * 	> Consistency - All transactions change the database state from one valid state to another. No transaction will corrupt the database.
	 * 	> Isolation - Even if the database is accessed in parallel, changes to the database must occur as if they were serial. I.e. transactions don't interfere with each other.
	 * 	> Durability - Data will persist through issues (like power loss)
	 */
	
	private static SessionFactory sf = 
			new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
//	public static Session getSession() {
//		return sf.openSession();
//	}
	
	private static Session ses;
	
	// Put these two functions in to deal with lazy loading
	
	// Making a global session
	public static Session getSession() {
		if (ses==null) {
			return ses = sf.openSession();
		} else {
			return ses;
		}
	}
	
	// Closing a global session
	public static void closeSession() {
		ses.close();
		ses = null;
		sf.close();
	}

}
