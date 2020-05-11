package com.bhank.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bhank.dao.impl.CustomerDAOImpl;
import com.bhank.exception.BusinessException;
import com.bhank.model.Customer;
import com.bhank.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDAOImpl dao = new CustomerDAOImpl();

	@Override
	public Customer createCustomer(Customer c) throws BusinessException {
		if (c == null) {
			throw new BusinessException("Customer object was not created");
		} else if (!isValidName(c.getName())) {
			throw new BusinessException("Customer name is invalid");
		} else if (!isValidPassword(c.getPassword())) {
			throw new BusinessException("Customer password is invalid");
		} else {
			dao.createCustomer(c);
		}
		return c;
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
		if (name.matches("[a-zA-Z ]{1,20}")) {
			b = true;
		}
		return b;
	}

	@Override
	public Customer updateCustomer(Customer c) throws BusinessException {
		if (c == null) {
			throw new BusinessException("Customer object was not created");
		} else if (!isValidName(c.getName())) {
			throw new BusinessException("Customer name is invalid");
		} else if (!isValidPassword(c.getPassword())) {
			throw new BusinessException("Customer password is invalid");
		}
		return c;
	}

	@Override
	public List<Customer> selectAllCustomers() throws BusinessException {
		return dao.selectAllCustomers();
	}

	@Override
	public Customer selectCustomerByNameAndPassword(String name, String password) throws BusinessException {
		Customer c = null;
		if(name==null ||password==null) {
			throw new BusinessException("Name or password is null");
		} else if (!isValidName(name)) {
			throw new BusinessException("Name is invalid");
		} else if (!isValidPassword(password)) {
			throw new BusinessException("password is invalid");
		} else {
			c=dao.selectCustomerByNameAndPassword(name, password);
		}
		return c;
	}

	@Override
	public Customer selectCustomerById(String id) throws BusinessException {
		Customer c=null;
		if(id==null) {
			throw new BusinessException("id is null");
		} else {
			c=dao.selectCustomerById(id);
		}
		return c;
	}

	@Override
	public void deleteCustomer(Customer c) throws BusinessException {
		if (c == null) {
			throw new BusinessException("Customer object was not created");
		} else if (!isValidName(c.getName())) {
			throw new BusinessException("Customer name is invalid");
		} else if (!isValidPassword(c.getPassword())) {
			throw new BusinessException("Customer password is invalid");
		} else {
			dao.deleteCustomer(c);
		}
	}

}
