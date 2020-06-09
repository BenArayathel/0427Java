package com.animal.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table
public class Person {

	@Id
	@GeneratedValue//(strategy=GenerationType.AUTO)
	@Column(name="person_id")
	private int id;
	private String firstName;
	private String lastName;
	@OneToMany
//	@JoinColumn
	@JsonBackReference
	private List<Animal> pets = new ArrayList<>();
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Animal> getPets() {
		return pets;
	}

	public void setPets(List<Animal> pets) {
		this.pets = pets;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", pets=" + pets + "]";
	}

}
