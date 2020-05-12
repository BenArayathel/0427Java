package com.hackbank.business.services.person;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.models.Person;

public interface PersonService {
	
	public abstract Person createPerson(Person person) throws BusinessException;
	public abstract Person getPersonById(String id) throws BusinessException;
	public abstract String getPersonIdBySSN(String ssn) throws BusinessException;

}
