package com.animal.app.service;

import java.util.List;

import com.animal.app.model.Person;

public interface PersonService {
	
	public Person createPerson(Person p);
	public Person updatePerson(Person p);
	public Person getPersonById(int id);
	public void deletePersonById(int id);
	public List<Person> getAllPersons();
	public List<Person> getPersonsByFirstName(String firstName);
	public List<Person> getPersonsByLastName(String lastName);
	public List<Person> getPersonsByFirstNameAndLastName(String firstName, String lastName);
	public boolean isPerson(int ownerId);

}
