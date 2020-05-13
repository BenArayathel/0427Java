package com.hackbank.business.services.user;

import com.hackbank.persistence.models.User;

public class SingletonUser {
	
private static final SingletonUser S_USER = new SingletonUser();
	
	public User iUser;
	
	private SingletonUser() {
		// TODO Auto-generated constructor stub
	}
	
	public static SingletonUser getUser() {
		return S_USER;
	}

}
