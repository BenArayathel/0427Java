package com.bhank.webapp.model;

import java.util.Date;

public class User {

	private String id;
	private String name;
	private Date dob;
	private String password;
	
	public User() {
		super();
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User Name=" + name + ", id=" + id + ", password=" + password
				+ " dob="+ dob.toString() + "]";
	}
	
	
}