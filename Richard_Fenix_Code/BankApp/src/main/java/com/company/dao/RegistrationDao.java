package com.company.dao;

import java.util.List;

import com.company.model.Registration;

public interface RegistrationDao {

	List<Registration> getAllRegistrations();
	
	Registration getRegistration(int id);
	
	Registration addRegistration(Registration registration);
	
	void updateRegistration(Registration registration);

	void deleteRegistration(int id);
	
	// Update by Customer ID
	void updateRegistrationByCustomerId(Registration registration);
	
	// Delete by Customer ID
	void deleteRegistrationByCustomerId(int customerId);

	// Get customer ID by login name and password
	int getCustomerIdByLoginName(String loginName, String password);
}
