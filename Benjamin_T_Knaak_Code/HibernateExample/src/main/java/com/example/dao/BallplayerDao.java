package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.model.Ballplayer;
import com.example.model.Planet;
import com.example.util.HibernateUtil;

public class BallplayerDao {
	
	public void insert(Ballplayer b) {
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();
		
		sesh.save(b);
		tx.commit();
		sesh.close();
	}
	
	public void update(Ballplayer b) {
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();
		
		sesh.update(b);
		tx.commit();
		sesh.close();
	}
	
	public Ballplayer selectByName(String name) {
		Session sesh = HibernateUtil.getSession();
		
		List<Ballplayer> playerList = sesh.createQuery("from Ballplayer where name = '" + name + "'", Ballplayer.class).list();
		
		return playerList.get(0);
	}
	
	public Ballplayer selectById(int id) {
		Session sesh = HibernateUtil.getSession();
		
		List<Ballplayer> playerList = sesh.createQuery("from Ballplayer where playerId = '" + id + "'", Ballplayer.class).list();
		
		return playerList.get(0);
	}
	
	public List<Ballplayer> selectAll() {
		Session sesh = HibernateUtil.getSession();
		
		List<Ballplayer> playerList = sesh.createQuery("from Ballplayer", Ballplayer.class).list();
		
		return playerList;
	}
	
	public void updateAvg(Ballplayer b, double newAvg) {
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();
		
		b.setAvg(newAvg);
		sesh.update(b);
		tx.commit();
		sesh.close();
	}
	
	public void updateSlg(Ballplayer b, double newSlg) {
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();
		
		b.setSlg(newSlg);
		sesh.update(b);
		tx.commit();
		sesh.close();
	}
	
	public void updateOps(Ballplayer b, double newOps) {
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();
		
		b.setOps(newOps);
		sesh.update(b);
		tx.commit();
		sesh.close();
	}
	
	public void updateHr(Ballplayer b, int newHr) {
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();
		
		b.setHr(newHr);
		sesh.update(b);
		tx.commit();
		sesh.close();
	}
	
	public void delete(Ballplayer b) {
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();
		
		sesh.delete(b);
		tx.commit();
		sesh.close();
	}
}
