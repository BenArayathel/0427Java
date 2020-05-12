package com.company.dao;

import java.util.List;

import com.company.model.Registration;

public interface RegistrationDao {

	List<Registration> getAllRegistrations();
	
	Registration getRegistration(long registrationId);
	
	Registration addRegistration(Registration registration);
	
	void updateRegistration(Registration registration);

	void deleteRegistration(long registraionId);
	
	// Update by Customer ID
	void updateRegistrationByCustomerId(Registration registration);
	
	// Delete by Customer ID
	void deleteRegistrationByCustomerId(int customerId);

	// Get customer ID by login name and password
	int getCustomerIdByLoginName(String loginName, String password);
	
	// For validating if Customer already has a login name (previously registered).
	Registration getRegistrationByCustomerId(int customerId);
	
	// For checking if loginName is already used.
	boolean isLoginNameNotUsed(String loginName); 

}
