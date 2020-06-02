package com.pzero.v1.business.services.person;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.persistence.models.Person;

public interface PersonService {
	
	public abstract Person createPerson(Person person) throws BusinessException;
	public abstract Person getPersonById(String id) throws BusinessException;
	public abstract Person getPersonIdBySSN(String ssn) throws BusinessException;

}
