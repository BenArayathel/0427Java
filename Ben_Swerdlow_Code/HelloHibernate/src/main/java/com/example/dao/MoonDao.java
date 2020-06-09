package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.model.Moon;
import com.example.util.HibernateUtil;

public class MoonDao {

	public void insert(Moon  m) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(m);
		tx.commit();
//		ses.close();
	}
	
	public List<Moon> selectAll() {
		Session ses = HibernateUtil.getSession();
		
		// HQL
		List<Moon> moonList = ses.createQuery("from Moon", Moon.class).list();
		
		return moonList;
	}
	
}
