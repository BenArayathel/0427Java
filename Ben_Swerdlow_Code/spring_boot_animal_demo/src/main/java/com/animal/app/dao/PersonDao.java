package com.animal.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animal.app.model.Person;

public interface PersonDao extends JpaRepository<Person, Integer> {
	
	public List<Person> findByFirstName(String firstName);
	public List<Person> findByFirstNameStartingWith(String partialFirstName);
	
	public List<Person> findByLastName(String lastName);
	public List<Person> findByLastNameStartingWith(String partialLastName);
	
	public List<Person> findByFirstNameAndLastName(String firstName, String lastName);

}
