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
@Table(name = "USER_LOGIN")
public class UserLogin {
	
	
	@Id
	@Column(name = "USER_LOGINID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userLID;
	
	@Column(name = "USER_ID", unique = true, nullable = false)
	private int userID;
	
	@Column(name = "USER_NAME", unique = true, nullable = false)
	private String userName;
	
	@Column(name = "USER_PASSWORD", unique = false, nullable = false)
	private String password;
	
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "USER_FK", referencedColumnName = "USER_ID")
//	private user myuser;


	public UserLogin() {
		// TODO Auto-generated constructor stub
	}


	public int getUserLID() {
		return userLID;
	}


	public void setUserLID(int userLID) {
		this.userLID = userLID;
	}


	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "UserLogin [userLID=" + userLID + ", userID=" + userID + ", userName=" + userName + ", password="
				+ password + "]";
	}


	
	

}
