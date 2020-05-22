package com.bhank.webapp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bhank.webapp.dao.impl.CustomerDAOImpl;
import com.bhank.webapp.exception.BusinessException;
import com.bhank.webapp.main.Main;
import com.bhank.webapp.model.Customer;
import com.bhank.webapp.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDAOImpl dao = new CustomerDAOImpl();

	@Override
	public Customer createCustomer(Customer customer) throws BusinessException {
		if (customer == null) {
			Main.logger.error("Customer service failed to create customer becuase customer object was null");
			throw new BusinessException("Customer object was not created");
		} else if (!isValidName(customer.getName())) {
			Main.logger.error("Customer service failed to created customer due to invalid name \""+customer.getName()+"\"");
			throw new BusinessException("Customer name is invalid");
		} else if (!isValidPassword(customer.getPassword())) {
			Main.logger.error("Customer service failed to created customer due to invalid password \""+customer.getPassword()+"\"");
			throw new BusinessException("Customer password is invalid");
		} else {
			Main.logger.info("Customer service successfully created customer: "+customer);
			dao.createCustomer(customer);
		}
		return customer;
	}

	public static Date isValidDob(String dob) throws BusinessException {
		Date d = null;
		if (dob.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			sdf.setLenient(false);
			try {
				d = sdf.parse(dob);
			} catch (ParseException e) {
				Main.logger.info("Customer service denied invalid date \""+dob+"\"");
				throw new BusinessException("Entered date " + dob + " is invalid");
			}
		} else {
			Main.logger.info("Customer service denied invalid date format \""+dob+"\"");
			throw new BusinessException("Entered date " + dob + " should be in (dd.MM.yyyy) format only");
		}
		Main.logger.info("Customer service successfully validated dob \""+dob+"\"");
		return d;
	}

	public boolean isValidPassword(String password) {
		boolean b = false;
		if (password.matches("[a-zA-Z0-9]{4,12}")) {
			Main.logger.info("Customer service validated password \""+password+"\"");
			b = true;
		}
		return b;
	}

	private boolean isValidName(String name) {
		boolean b = false;
		if (name.matches("[a-zA-Z ]{1,20}")) {
			Main.logger.info("Customer service validated name \""+name+"\"");
			b = true;
		}
		return b;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws BusinessException {
		if (customer == null) {
			throw new BusinessException("Customer object was not created");
		} else if (!isValidName(customer.getName())) {
			throw new BusinessException("Customer name is invalid");
		} else if (!isValidPassword(customer.getPassword())) {
			throw new BusinessException("Customer password is invalid");
		}
		return customer;
	}

	@Override
	public List<Customer> selectAllCustomers() throws BusinessException {
		Main.logger.info("Customer service successfully selected all customers");
		return dao.selectAllCustomers();
	}

	@Override
	public Customer selectCustomerByNameAndPassword(String customerName, String customerPassword) throws BusinessException {
		Customer customer = null;
		if(customerName==null || customerPassword==null) {
			Main.logger.error("Customer service failed to selected customer by name and password because name or password was null");
			throw new BusinessException("Name or password is null");
		} else if (!isValidName(customerName)) {
			Main.logger.error("Customer service failed to selected customer by name and password due to invalid name \""+customerName+"\"");
			throw new BusinessException("Name is invalid");
		} else if (!isValidPassword(customerPassword)) {
			Main.logger.error("Customer service failed to selected customer by name and password due to invalid password \""+customerPassword+"\"");
			throw new BusinessException("password is invalid");
		} else {
			Main.logger.info("Customer service successfully selected customer by name \""+customerName+"\" and password \""+customerPassword+"\"");
			customer=dao.selectCustomerByNameAndPassword(customerName, customerPassword);
		}
		return customer;
	}

	@Override
	public Customer selectCustomerById(String id) throws BusinessException {
		Customer customer=null;
		if(id==null) {
			Main.logger.error("Customer service failed to selected customer by id because id was null");
			throw new BusinessException("id is null");
		} else if(!isValidId(id)) {
			Main.logger.error("Customer service failed to select customer by id due to invalid id \""+id+"\"");
			throw new BusinessException("Customer id is invalid");
		} else {
			Main.logger.info("Customer service successfully selected customer by id \""+id+"\"");
			customer=dao.selectCustomerById(id);
		}
		return customer;
	}
	
	private boolean isValidId(String id) {
		boolean b = false;
		if (id.matches("CU[a-zA-Z]{2}[0-9]{9}")) {
			Main.logger.info("Customer service successfully validated customer id \""+id+"\"");
			b = true;
		}
		return b;
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
