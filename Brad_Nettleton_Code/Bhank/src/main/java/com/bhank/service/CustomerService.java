package com.bhank.service;

import java.util.List;

import com.bhank.exception.BusinessException;
import com.bhank.model.Account;
import com.bhank.model.Customer;

public interface CustomerService  {

	// create
	Customer createCustomer(Customer c) throws BusinessException;

	// update
	Customer updateCustomer(Customer e) throws BusinessException;

	// read
	List<Customer> selectAllCustomers() throws BusinessException;

	Customer selectCustomerByNameAndPassword(String name, String Password) throws BusinessException;

	Customer selectCustomerById(String id) throws BusinessException;

	// delete
	void deleteCustomer(Customer e) throws BusinessException;
}
