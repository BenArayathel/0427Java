package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Cars;


@Repository("carRepo")
public class CarsRepo {

	private SessionFactory sesFact;
	
	@Autowired
	public CarsRepo(SessionFactory sesFact) {
		this.sesFact = sesFact;
	}
	
//	@Transactional
	public List<Cars> selectAllCars() {
		Session ses = sesFact.openSession();
		List<Cars> carList = ses.createQuery("from Cars",Cars.class).list();
		ses.close();
		return carList;
		
//		List<Cars> carList;
//		carList = sesFact.getCurrentSession().createQuery("from Cars",Cars.class).list();
//		return carList;

	}
	
	@Transactional
	public Cars selectCarById(int id) {
//		Session ses = sesFact.openSession();
//		Cars car = ses.get(Cars.class, id);
//		ses.close();
//		return car;
		
		Cars car = sesFact.getCurrentSession().get(Cars.class, id);
		return car;
	}
	
	@Transactional
	public void insertCar(Cars c) {
//		Session ses = sesFact.openSession();
//		Transaction tx = ses.beginTransaction();
//		ses.save(c);
//		tx.commit();
//		ses.close();
		
		sesFact.getCurrentSession().save(c); 

	}
	
	@Transactional
	public void deleteCar(Cars c) {
//		Session ses = sesFact.openSession();
//		Transaction tx = ses.beginTransaction();
//		ses.delete(c);
//		tx.commit();
//		ses.close();
		
		sesFact.getCurrentSession().delete(c); 

	}
	
	@Transactional
	public void updateCar(Cars c) {
//		Session ses = sesFact.openSession();
//		Transaction tx = ses.beginTransaction();
//		ses.update(c);
//		tx.commit();
//		ses.close();
		
		sesFact.getCurrentSession().update(c);

		
	}

}
