package com.animal.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.animal.app.model.Person;
import com.animal.app.service.PersonService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class PersonController {
	
	private PersonService service;
	
	public PersonController() {
		super();
	}
	
	@Autowired
	public PersonController(PersonService service) {
		super();
		this.service = service;
	}

	@PostMapping("/person")
	public Person createPerson(@RequestBody Person p) {
		// TODO Auto-generated method stub
		return service.createPerson(p);
	}

	@PutMapping("/person")
	public Person updatePerson(@RequestBody Person p) {
		return service.updatePerson(p);
	}
	
	@GetMapping("/person/{id}")
	public Person getPersonById(@PathVariable("id") int id) {
		return service.getPersonById(id);
	}

	@DeleteMapping("/Person/{id}")
	public void deletePersonById(@PathVariable int id) {
		service.deletePersonById(id);
	}

	@GetMapping("/persons")
	public List<Person> getAllPersons() {
		return service.getAllPersons();
	}

	@GetMapping("/person/firstName/{firstName}")
	public List<Person> getPersonsByFirstName(@PathVariable("firstName") String firstName) {
		return service.getPersonsByFirstName(firstName);
	}

	@GetMapping("/person/lastname/{lastName}")
	public List<Person> getPersonsByLastName(@PathVariable("firstName") String lastName) {
		return service.getPersonsByFirstName(lastName);
	}

	@GetMapping("/person/firstName/{firstName}/lastName/{lastName}")
	public List<Person> getPersonsByFirstNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
		return service.getPersonsByFirstNameAndLastName(firstName, lastName);
	}

}
