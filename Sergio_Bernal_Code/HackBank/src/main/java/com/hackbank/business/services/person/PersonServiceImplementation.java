package com.hackbank.business.services.person;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.business.validations.Validation;
import com.hackbank.persistence.dao.person.PersonDAO;
import com.hackbank.persistence.dao.person.PersonDAOImplementation;
import com.hackbank.persistence.models.Person;

public class PersonServiceImplementation implements PersonService {
	
	private PersonDAO personDao = new PersonDAOImplementation();
	Validation vd = new Validation();

	public Person createPerson(Person person) throws BusinessException {

		Person iPerson = null;
		LocalDate currentDate = LocalDate.now();
		LocalDate localDob = person.getDob().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Period period = Period.between(localDob, currentDate);		
		
		if(!vd.isValidName(person.getName())) {
			throw new BusinessException("Person Name "+person.getName()+" is not valid.");
		}else if(!vd.isValidName(person.getLastName())) {
			throw new BusinessException("Person Last Name "+person.getLastName()+" is not valid.");
		}else if(!vd.isValidSSN(person.getSsn())) {
			throw new BusinessException("Person SSN is not valid.");
		}else if(!vd.isValidContact(person.getPhoneNumber())) {
			throw new BusinessException("Person Phone Number "+person.getPhoneNumber()+" is not valid.");
		}else if(period.getYears() < 18) {
			throw new BusinessException("The person can not sign up for an account is under-aged");
		}else if(!vd.isValidName(person.getCity())) {
			throw new BusinessException("Person City "+person.getCity()+" is not valid.");
		}else {
			if (!personDao.getPersonIdBySSN(person.getSsn()).equals("")) {
				iPerson = personDao.createPerson(person);
			}else {
				throw new BusinessException("Customer SSN already exist.");
			}
		}
		return iPerson;
		
	}

	public Person getPersonById(String id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPersonIdBySSN(String ssn) throws BusinessException {
		String personId = "";
		if(!vd.isValidSSN(ssn)) {
			throw new BusinessException("Person SSN is not valid.");
		}else {
			personId = personDao.getPersonIdBySSN(ssn);
		}
		return personId;
	}

}
