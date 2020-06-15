package com.friendshipBank.service;

import java.util.List;

import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.customer;


public interface customerService {
	
	public customer createCustomer(customer customer)throws BusinessException;
	public customer getCustomerById(String cId)throws BusinessException;
	public customer updateCustomerContact(String cId,long newContact) throws BusinessException;
	public void deleteCustomer(String cId) throws BusinessException;
	public List<customer> getCustomerByState(String state) throws BusinessException;
	public List<customer> getCustomerByCity(String city) throws BusinessException;

}
