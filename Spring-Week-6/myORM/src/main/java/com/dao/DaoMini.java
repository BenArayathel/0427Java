package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.Car;
import com.model.Mini;

@Repository("miniRepoDao")
public class DaoMini {
	
	private SessionFactory sf;
	
	@Autowired
	public DaoMini(SessionFactory sf) {
		this.sf = sf;
	}
	
	// CREATE
	@Transactional
	public void createCar(Mini mini) {
		
//		Session session = sf.openSession();
//		Transaction tx = session.beginTransaction();
//		
//		session.save(car);
//		
//		tx.commit();
//		session.close();
		
		sf.getCurrentSession().save(mini);
		
		
	}
	
	// READ
	@Transactional
	public List<Mini> selectAll() {
		
		Session session = sf.openSession();
		List<Mini> miniList = session.createQuery("from car", Mini.class).list();
		session.close();
		return miniList;
		
	}
	
	// READ
	public Car getCar_ById(int id) {
		
		return null;
	}
	
	// UPDATE
	public void updateCar(Car car) {
		
		
	}
	
	
	// DELETE
	@Transactional
	public void deleteCar(Car car) {
		
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		session.delete(car);
		
		tx.commit();
		session.close();
	}


}
