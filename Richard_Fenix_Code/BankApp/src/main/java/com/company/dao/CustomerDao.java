package com.company.dao;

import java.util.List;

import com.company.model.Customer;

public interface CustomerDao {
	
	Customer addCustomer(Customer customer);
	
	Customer getCustomer(int id);
	
	List<Customer> getAllCustomers();
	
	void updateCustomer(Customer customer);
	
	void deleteCustomer(int id);

}
