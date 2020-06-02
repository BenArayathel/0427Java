package com.pzero.v1.persistence.models;

public class User extends Person{

	private String Id;
	private String email;
	private String password;
	private String personId;
	private String userType;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String id, String email, String password, String personId, String userType) {
		super();
		Id = id;
		this.email = email;
		this.password = password;
		this.personId = personId;
		this.userType = userType;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [Id=" + Id + ", email=" + email + ", password=" + password + ", Customer Id=" + personId + ", userType="
				+ userType + "]";
	}
	
}
