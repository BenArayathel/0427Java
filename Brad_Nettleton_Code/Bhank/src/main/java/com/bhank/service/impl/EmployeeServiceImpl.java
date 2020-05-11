package com.bhank.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bhank.dao.impl.EmployeeDAOImpl;
import com.bhank.exception.BusinessException;
import com.bhank.model.Employee;
import com.bhank.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
	EmployeeDAOImpl dao=new EmployeeDAOImpl();

	@Override
	public Employee createEmployee(Employee e) throws BusinessException {
		if (e == null) {
			throw new BusinessException("Customer object was not created");
		} else if (!isValidName(e.getName())) {
			throw new BusinessException("Customer name is invalid");
		} else if (!isValidPassword(e.getPassword())) {
			throw new BusinessException("Customer password is invalid");
		} else {
			dao.createEmployee(e);
		}
		return e;
	}
	
	public static Date isValidDob(String dob) throws BusinessException {
		Date d = null;
		if (dob.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			sdf.setLenient(false);
			try {
				d = sdf.parse(dob);
			} catch (ParseException e) {
				throw new BusinessException("Entered date " + dob + " is invalid");
			}
		} else {
			throw new BusinessException("Entered date " + dob + " should be in (dd.MM.yyyy) format only");
		}
		return d;
	}

	private boolean isValidPassword(String password) {
		boolean b = false;
		if (password.matches("[a-zA-Z0-9]{4,12}")) {
			b = true;
		}
		return b;
	}

	private boolean isValidName(String name) {
		boolean b = false;
		if (name.matches("[a-zA-Z]{1,20}")) {
			b = true;
		}
		return b;
	}

	@Override
	public Employee updateEmployee(Employee e) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> selectAllEmployees() throws BusinessException {
		return dao.selectAllEmployees();
	}

	@Override
	public Employee selectEmployeeByNameAndPassword(String name, String password) throws BusinessException {
		Employee employee = null;
		if(name==null ||password==null) {
			throw new BusinessException("Name or password is null");
		} else if (!isValidName(name)) {
			throw new BusinessException("Name is invalid");
		} else if (!isValidPassword(password)) {
			throw new BusinessException("password is invalid");
		} else {
			employee=dao.selectEmployeeByNameAndPassword(name, password);
		}
		return employee;
	}

	@Override
	public Employee selectEmployeeById(String id) throws BusinessException {
		Employee employee=null;
		if(id==null) {
			throw new BusinessException("id is null");
		} else {
			employee=dao.selectEmployeeById(id);
		}
		return employee;
	}

	@Override
	public void deleteEmployee(Employee e) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	

}
