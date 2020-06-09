package com.animal.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.animal.app.dao.PersonDao;
import com.animal.app.model.Person;
import com.animal.app.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	private PersonDao pdao;

	public PersonServiceImpl() {
		super();
	}
	
	@Autowired
	public PersonServiceImpl(PersonDao pdao) {
		super();
		this.pdao = pdao;
	}

	@Override
	public Person createPerson(Person p) {
		return pdao.save(p);
	}

	@Override
	public Person updatePerson(Person p) {
		return pdao.save(p);
	}

	@Override
	public Person getPersonById(int id) {
		return pdao.findById(id).get();
	}

	@Override
	public void deletePersonById(int id) {
		Person p = getPersonById(id);
		pdao.delete(p);
	}
	
	public void deletePerson(Person p) {
		pdao.delete(p);
	}

	@Override
	public List<Person> getAllPersons() {
		return pdao.findAll();
	}

	@Override
	public List<Person> getPersonsByFirstName(String firstName) {
		return pdao.findByFirstName(firstName);
	}

	@Override
	public List<Person> getPersonsByLastName(String lastName) {
		return pdao.findByLastName(lastName);
	}

	@Override
	public List<Person> getPersonsByFirstNameAndLastName(String firstName, String lastName) {
		return pdao.findByFirstNameAndLastName(firstName, lastName);
	}

	@Override
	public boolean isPerson(int id) {
		return pdao.existsById(id);
	}

	public PersonDao getPdao() {
		return pdao;
	}

	public void setPdao(PersonDao pdao) {
		this.pdao = pdao;
	}

}
