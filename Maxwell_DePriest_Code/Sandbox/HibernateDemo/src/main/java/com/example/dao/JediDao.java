package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.example.model.Jedi;
import com.example.util.HibernateUtil;

public class JediDao {

	public JediDao() {
		
	}
	
	public void insertJedi(Jedi j) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(j);
		
		tx.commit();
		ses.close();
	}
	
	public void update(Jedi j) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(j);
		
		tx.commit();
		ses.close();
	}
	
	public List<Jedi> selectAll() {
		Session ses = HibernateUtil.getSession();
		List<Jedi> jediList = ses.createQuery("from Jedi", Jedi.class).list();
		return jediList;
	}
	
	public void delete(Jedi j) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.delete(j);
		
		tx.commit();
		ses.close();
	}
	public Jedi selectByName(String name) {
		Session ses = HibernateUtil.getSession();
		
		List<Jedi> jediList = ses.createQuery("from Jedi where name ='" + name + "'", Jedi.class).list();
		
		return jediList.get(0);
	
	}
	
	

}
