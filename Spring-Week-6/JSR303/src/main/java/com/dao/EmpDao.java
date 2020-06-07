package com.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.EmployeeVO;


@Transactional
@Repository("empRepo")
public class EmpDao {
	
	private SessionFactory sf;

	@Autowired
	public EmpDao(SessionFactory sf) {
		super();
		this.sf = sf;
	}
	
	
	public void insert(EmployeeVO emp) {
		
		sf.getCurrentSession().save(emp);
		
	}
	
	public void delete(EmployeeVO emp) {
		
		sf.getCurrentSession().delete(emp);
		
	}
	
	public void update(EmployeeVO emp) {
		
		sf.getCurrentSession().update(emp);
	}
	
	public EmployeeVO selectById(int id) {
		
		EmployeeVO emp = sf.getCurrentSession().get(EmployeeVO.class, id);
		
		return emp;
		
	}
	
	public List<EmployeeVO> selectAll() {
		
		List<EmployeeVO> empList;
		
		empList = sf.getCurrentSession().createQuery("from EmployeeVO", EmployeeVO.class).list();
		
		return empList;
		
	}
	
	

}
