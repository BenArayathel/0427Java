package com.friendshipBank.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.friendshipBank.dao.customerDAO;
import com.friendshipBank.dao.impl.customerDAOImpl;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.customer;
import com.friendshipBank.service.customerService;


public class customerServiceImpl implements customerService{

	private customerDAO cusDAO = new customerDAOImpl();
	
	private boolean isValidContact(long contact) {
		boolean b = false;
		if ((contact + "").matches("[0-9]{10}")) {
			b = true;
		}
		return b;
	}

	private boolean isValidCustomerId(String id) {
		boolean b = false;
		if (id.matches("FB[A-Z]{2}[0-9]{9}")) {
			b = true;
		}
		return b;
	}

	private boolean isValidLastName(String lname) {
		boolean b = false;
		if (lname.matches("[a-zA-Z]{2,30}")) {
			b = true;
		}
		return b;
	}
	
	private boolean isValidFirstName(String fname) {
		boolean b = false;
		if (fname.matches("[a-zA-Z]{2,30}")) {
			b = true;
		}
		return b;
	}
	
	private boolean isValidCity(String city) {
		boolean b = false;
		if (city.matches("[a-zA-Z]{2,30}")) {
			b = true;
		}
		return b;
	}
	
	private boolean isValidState(String state) {
		boolean b = false;
		if (state.matches("[a-zA-Z]{2}")) {
			b = true;
		}
		return b;
	}
	
	private boolean isValidEmail(String email) {
		boolean b = false;
		if (email.matches("[a-zA-Z0-9]{2,30}@[a-zA-Z0-9]{2,30}.[a-zA-Z0-9]{2,30}")) {
			b = true;
		}
		return b;
	}
	
	public static Date isValidDate(String dob) throws BusinessException 
	{
		Date d=null;
		if(dob.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
			sdf.setLenient(false);
			try {
				d=sdf.parse(dob);
			} catch (ParseException e) {
				throw new BusinessException("The entered Date " + dob + " is invalid");
			}
		}else {
			throw new BusinessException("The entered Date " + dob + " should be in (dd-MM-yyyy) format only");
		}
		return d;
	}
	
	
	
	@Override
	public customer createCustomer(customer customer) throws BusinessException {
		if (customer == null) {
			throw new BusinessException("CUSTOMER Object was not created");
		} else if (!isValidContact(customer.getContactNumber())) {
			throw new BusinessException("The entered CUSTOMER Contact " + customer.getContactNumber() + " is invalid");
		} else if (!isValidLastName(customer.getLastName())) {
			throw new BusinessException("The entered CUSTOMER Lastname " + customer.getLastName() + " is invalid");
		} else if (!isValidFirstName(customer.getFirstName())) {
			throw new BusinessException("The entered CUSTOMER Firstname " + customer.getFirstName() + " is invalid");
		} else if (!isValidState(customer.getState())) {
			throw new BusinessException("The entered CUSTOMER State " + customer.getState() + " is invalid");
		} else if (!isValidEmail(customer.getEmailAddress())) {
			throw new BusinessException("The entered CUSTOMER Email Address " + customer.getEmailAddress() + " is invalid");
		} else {
			// calling up DAO
			customer = cusDAO.createCustomer(customer);
		}
		return customer;
	}

	@Override
	public customer getCustomerById(String cId) throws BusinessException {
		customer customer = null;
		if(isValidCustomerId(cId)) {
			customer = cusDAO.getCustomerById(cId);
		}else {
			throw new BusinessException("The entered CUSTOMER ID " + cId + " is invalid");
		}
		return customer;
	}

	@Override
	public customer updateCustomerContact(String cId, long newContact) throws BusinessException {
		customer customer = null;
		if(isValidCustomerId(cId)) {
			customer = cusDAO.updateCustomerContact(cId, newContact);
		}else {
			throw new BusinessException("The entered CUSTOMER ID " + cId + " is invalid");
		}
		return customer;
	}

	@Override
	public void deleteCustomer(String cId) throws BusinessException {
		if(isValidCustomerId(cId)) {
			cusDAO.deleteCustomer(cId);
		}else {
			throw new BusinessException("The entered CUSTOMER ID " + cId + " is invalid");
		}
		
	}

	@Override
	public List<customer> getCustomerByState(String state) throws BusinessException {
		List<customer> customerList = new ArrayList<>();
		if(isValidCity(state)) {
			customerList = (List<customer>) cusDAO.getCustomerByCity(state) ;
		}else {
			throw new BusinessException("The entered STATE " + state + " is invalid");
		}
		return customerList;
	}

	@Override
	public List<customer> getCustomerByCity(String city) throws BusinessException {
		List<customer> customerList = new ArrayList<>();
		if(isValidCity(city)) {
			customerList = (List<customer>) cusDAO.getCustomerByCity(city) ;
		}else {
			throw new BusinessException("The entered CITY " + city + " is invalid");
		}
		return customerList;
	}

}
