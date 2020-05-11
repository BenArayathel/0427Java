package com.hackbank.persistence.dao.person;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.models.Person;

public interface PersonDAO {

	public abstract Person createPerson(Person person) throws BusinessException;
	public abstract Person getPersonById(String id) throws BusinessException;
	public abstract boolean getPersonBySSN(int ssn) throws BusinessException;
	
}
