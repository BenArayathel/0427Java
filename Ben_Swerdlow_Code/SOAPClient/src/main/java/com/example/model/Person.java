package com.example.model;

public class Person {
	
	private String firstName;
	private String lastName;
	private int age;
	private String solgan;
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(String firstName, String lastName, int age, String slogan) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.solgan = slogan;
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

	public int getDob() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSlogan() {
		return solgan;
	}

	public void setSlogan(String slogan) {
		this.solgan = slogan;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", slogan="
				+ solgan + "]";
	}

}
