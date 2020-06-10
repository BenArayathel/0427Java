package com.test.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_TABLE")
public class user {
	
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userID;
	
	@Column(name = "FIRST_NAME", unique = false, nullable = false)
	private String firstName;
	
	@Column(name = "LAST_NAME", unique = false, nullable = false)
	private String lastName;
	
	@Column(name = "STREET_ADDRESS", unique = false, nullable = false)
	private String address;
	
	@Column(name = "CONTACT_NUM", unique = false, nullable = false)
	private long contact;
	
	@Column(name = "EMAIL_ADDRESS", unique = false, nullable = false)
	private String email;
	
	
	
//	@OneToOne(mappedBy = "myuser")
//	private UserLogin Userlogin;
	
	public user() {
		// TODO Auto-generated constructor stub
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@Override
	public String toString() {
		return "user [userID=" + userID + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", contact=" + contact + ", email=" + email + "]";
	}
	
	

}
