package com;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// Repository is a synonym for DAO. This annotation is a specialized,
// specific type of @Component. it is used for these types of classes which play
// the role, or 'stereotype' of a repo
@Repository("paintingDAO") // also, does this name need to be here to be used in main?
public class PaintingDAO {
	
	// create CRUD operations
	// these are where 90% of the new spring changes go
	
	
	// but first, create the SessionFactory that we'll use in each method,
	// which is connected by an @Autowire to the beans.xml file. It knows 
	// to look here because of component scan telling it which packages to search
	
	// i need more understanding on how these lines work
	private SessionFactory sf;
	
	// this is a constructor for the session factory that we just made?...singleton?
	@Autowired
	public void PaintingDaO(SessionFactory sf) {
		this.sf = sf;
	}
	
	// CREATE
	@Transactional
	public void insert(Painting painting) {
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		
		// look into save vs persist
		s.save(painting);
		
		// persists to database
		t.commit();
		
		// if you don't close it, lots of random threads floating around
		s.close();
	}
	
	// READ
	@Transactional
	public List<Painting> selectAll() {
		Session s = sf.openSession();
//		Transaction t = s.beginTransaction(); // don't need to use this because we
		//aren't making changes, just querying
		
		// HQL style query, referencing the class, not the table name
		List<Painting> paintingList = s.createQuery("from Painting", Painting.class).list();
		// trying SQL instead...
//		List<Painting> paintingList = s.createNativeQuery("SELECT * FROM painting_table", Painting.class).list();
		
		// always shut the door behind you
		s.close();
		
		// send back the data we collected
		return paintingList;
		
		
	}
	
	// don't use this method, use service layer or something, but for DAO/repo
	// just use the entire object. best practices thing.
//	// Delete
//	@Transactional
//	public void deletePainting(String paintingTitle) {
//		Session s = sf.openSession();
//		Transaction t = s.beginTransaction();
//		
//		System.out.println(paintingTitle + " is the title i'm entering for the HQL query");
//		s.createQuery("delete from Painting where painting_title = :paintingTitle");
//		
//		// persists to database
//		t.commit();
//		
//		// if you don't close it, lots of random threads floating around
//		s.close();
//	}
	
	@Transactional
	public void delete(Painting painting) {
				
		sf.getCurrentSession().delete(painting); //Using contextual sessions
		
	}
	
	
	// Update
	@Transactional
	public void editPainting(Painting painting) {
		sf.getCurrentSession().saveOrUpdate(painting); // again, using contextual sessions
	}
	
	
	
	/*
	 * 	
	 */

}
