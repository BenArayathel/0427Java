package com.hackbank.persistence.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {

	private String Id;
	private int ssn;
	private String name;
	private String lastName;
	private Date dob;
	private long phoneNumber;
	private String city;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(String id, int ssn, String name, String lastName, Date dob, long phoneNumber, String city) {
		super();
		Id = id;
		this.ssn = ssn;
		this.name = name;
		this.lastName = lastName;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.city = city;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCityd(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Person [Id=" + Id + ", ssn=" + ssn + ", name=" + name + ", lastName=" + lastName + ", dob=" + dob
				+ ", phone number=" + phoneNumber + ", city=" + city + "]";
	}
	
	public String preview() {
		return "Name: " + name + 
				"\nLast Name: " + lastName +
				"\nSSN: " + ssn +
				"\nDate of birth: " + new SimpleDateFormat("MMM-dd-yyyy").format(dob) + 
				"\nPhone Number: " + phoneNumber + 
				"\nCity: " + city+"\n";
	}
	
}
