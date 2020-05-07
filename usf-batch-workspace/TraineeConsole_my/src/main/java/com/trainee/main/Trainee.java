package com.trainee.main;

import java.sql.Date;

public class Trainee {

	private String id;
	private String name;
	private long contact;
	private String email;
	private Date dob;
	private String city;

	public Trainee(String id, String name, long contact, String email, Date dob, String city) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.dob = dob;
		this.city = city;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
