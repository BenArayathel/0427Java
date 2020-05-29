package com.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.demo.model.Customer;
import com.demo.model.Laptop;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Every customer can purchase a laptop (one customer can purchase *only* one laptop
		 */
		
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		
		SessionFactory factory = configuration.buildSessionFactory(builder.build());
		
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
//		Laptop l1 = new Laptop("Abc Laps", 12.222);
//		Laptop l2 = new Laptop("Abc Laps", 12.222);
//		Laptop l3 = new Laptop("Abc Laps", 11.222);
//		Laptop l4 = new Laptop("Xyz Laps", 12.222);
//		Laptop l5 = new Laptop("Xyz Laps", 11.222);
//		
//		Customer c1 = new Customer("Ben", l5);
//		Customer c2 = new Customer("Ben Again", l4);
//		Customer c3 = new Customer("Ben Again & Again", l5);
//		
//		// Laptops need to be saved first so customer can buy/have it
//		session.save(l1);
//		session.save(l2);
//		session.save(l3);
//		session.save(l4);
//		session.save(l5);
//		
//		session.save(c1);
//		session.save(c2);
//		session.save(c3);
		
		// JDBC used ? for placeholders, in Hibernate :filedName is the namedParameter (placeholder, but better)
		Query<Customer> query = session.createQuery("from com.demo.model.Customer where name=:name");
		query.setParameter("name", "Ben");
//		query.setString("name", "Ben");
		List<Customer> l = query.list();
		for (Customer c : l) {
			// Will be eager; get too much information (we don't need to know about customer laptop details, but we'll get them anyway
			// This can really slow down the time necessary if the extra information is very large
			// We changed this to lazy loading (see Laptop) so this would not happen
			System.out.println("Name = "+ c.getName()+" Id = "+c.getCid());
		}
		// Now that we changed it to lazy loading, it will just fetch this laptop for first customer because we asked for it
		System.out.println(l.get(0).getLaptop());
		
		
		transaction.commit();
		
		session.close();
		
		factory.close();

	}

}
