package com.models;

import org.apache.log4j.Logger;

public class User {
	private String id;
	private String name;
	private String email;
	private String phoneNumber;
	private String password;
	private String status;
	
	public User() {
		
	}
	
	public User(String id, String name, String email, String phoneNumber, String password, String status) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.status = status;
		
	}



	public String getId() {
		return id;
	}
	public void setId(String i) {
		this.id = i;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", password=" + password
				+ ", status=" + status + "]";
	}
	
	

}
