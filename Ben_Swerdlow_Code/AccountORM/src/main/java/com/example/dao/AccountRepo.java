package com.example.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.SimpleAccount;

@Repository("accountRepo")
public class AccountRepo {
	
	private SessionFactory sesFact;

	// This autowired constructor will automatically inject the SessionFactory
	@Autowired
	public AccountRepo(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}

	@Transactional
	public void insert(SimpleAccount a) {
		sesFact.getCurrentSession().saveOrUpdate(a);
//		Session ses = sesFact.openSession();
//		
//		Transaction tx = ses.getTransaction();
//		
//		ses.saveOrUpdate(a);
//		
//		tx.commit();
//		
//		ses.close();
	}
	
	@Transactional
	public List<SimpleAccount> selectAll() {
		
		Session ses = sesFact.openSession();
//		Transaction tx = ses.getTransaction();
		List<SimpleAccount> aList = ses.createQuery("from SimpleAccount", SimpleAccount.class).list();
//		tx.commit();
		ses.close();
		
		return aList;
	}
	
	@Transactional
	public SimpleAccount selectByAccountNumber(long accountNumber) {
		Session ses = sesFact.openSession();
		
		// Old criteria syntax
//		List<Account> aList = ses.createCriteria(Account.class).list();
		
		// New (longer, but more explicit) criteria syntax
		// Create a criteria builder
		CriteriaBuilder cBuilder = ses.getCriteriaBuilder();
		// Create a query object
		CriteriaQuery<SimpleAccount> critQuery = cBuilder.createQuery(SimpleAccount.class);
		// Get the query root (defines the range variable in a FROM clause)
		Root<SimpleAccount> root = critQuery.from(SimpleAccount.class);
		// Set the query root
		critQuery.select(root);
		// Prepare query for execution
		Query<SimpleAccount> query = ses.createQuery(critQuery);
		// Execute query!
		List<SimpleAccount> aList = query.getResultList();
		
		return aList.get(0);
	}
	
	@Transactional
	public void update(SimpleAccount a) {
		sesFact.getCurrentSession().update(a);
	}
	
	@Transactional
	public void delete(SimpleAccount a) {
		sesFact.getCurrentSession().delete(a);
	}

}
