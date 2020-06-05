package com.protestorganizer.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.protestorganizer.model.Protest;

@Repository("protestRepo")
@Transactional
public class ProtestRepo {
	
	private SessionFactory sesFact;
	
	@Autowired
	public ProtestRepo(SessionFactory sesFact) {
		this.sesFact = sesFact;
	}
	
	public void insert(Protest p) {
		sesFact.getCurrentSession().save(p);
	}
	
	public List<Protest> selectAll() {
		return sesFact.getCurrentSession().createQuery("from Protest", Protest.class).list();
	}
	
	public Protest selectById(int id) {
		return sesFact.getCurrentSession().createQuery("from Protest where id="+id, Protest.class).list().get(0);
	}
	
	public void update(Protest p) {
		sesFact.getCurrentSession().update(p);
	}
	
	public void delete(Protest p) {
		sesFact.getCurrentSession().delete(p);
	}

}
