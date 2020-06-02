package com.pzero.v1.business.services.person;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.business.validations.Validation;
import com.pzero.v1.persistence.dao.person.PersonDAO;
import com.pzero.v1.persistence.dao.person.PersonDAOImplementation;
import com.pzero.v1.persistence.models.Person;

public class PersonServiceImplementation implements PersonService {
	
	private PersonDAO personDao = new PersonDAOImplementation();
	Validation vd = new Validation();

	public Person createPerson(Person person) throws BusinessException {

		Person iPerson = null;
		LocalDate currentDate = LocalDate.now();
		LocalDate localDob = person.getDob().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Period period = Period.between(localDob, currentDate);		
		
		if(!vd.isValidName(person.getName())) {
			throw new BusinessException("\nCustomer's Name "+person.getName()+" is not valid.");
		}else if(!vd.isValidName(person.getLastName())) {
			throw new BusinessException("\nCustomer's Last Name "+person.getLastName()+" is not valid.");
		}else if(!vd.isValidSSN(person.getSsn())) {
			throw new BusinessException("\nCustomer's SSN is not valid.");
		}else if(!vd.isValidContact(person.getPhoneNumber())) {
			throw new BusinessException("\nCustomer's Phone Number "+person.getPhoneNumber()+" is not valid.");
		}else if(period.getYears() < 18) {
			throw new BusinessException("\nThe customer can not sign up for an account is under-aged");
		}else if(!vd.isValidCity(person.getCity())) {
			throw new BusinessException("\nCustomer's City "+person.getCity()+" is not valid.");
		}else {
			if (personDao.getPersonIdBySSN(person.getSsn()) == null) {
				iPerson = personDao.createPerson(person);
			}else {
				throw new BusinessException("Customer's SSN already exist.");
			}
		}
		return iPerson;
		
	}

	public Person getPersonById(String id) throws BusinessException {
		return personDao.getPersonById(id);
	}

	@Override
	public Person getPersonIdBySSN(String ssn) throws BusinessException {
		Person person = null;
		if(!vd.isValidSSN(ssn)) {
			throw new BusinessException("\nCustomer's SSN is not valid.\n");
		}else {
			person = personDao.getPersonIdBySSN(ssn);
		}
		return person;
	}

}
