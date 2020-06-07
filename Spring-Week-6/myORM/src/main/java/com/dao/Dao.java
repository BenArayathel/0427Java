package com.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.Car;

@Repository("carRepoDao")
public class Dao {
	
//	private static SessionFactory sf;
	private SessionFactory sf;
	
	@Autowired
	public Dao(SessionFactory sf) {
		this.sf = sf;
	}
	
	// CREATE
	@Transactional
	public void createCar(Car car) {
		
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(car);
		
		tx.commit();
		session.close();
	}
	
	// READ
	@Transactional
	public List<Car> selectAll() {
		
		Session session = sf.openSession();
		List<Car> carList = session.createQuery("from Car", Car.class).list();
		session.close();
		return carList;
		
	}
	
	// READ
	public Car getCar_ById(int id) {
	
		Session session = sf.openSession();
		Car car = (Car) session.createQuery("from Car where car_id=" + id);
		
		return car;
	}
	
	// UPDATE
	public void updateCar(Car car) {
		
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		session.update(car);
		
		tx.commit();
		session.close();
		
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
