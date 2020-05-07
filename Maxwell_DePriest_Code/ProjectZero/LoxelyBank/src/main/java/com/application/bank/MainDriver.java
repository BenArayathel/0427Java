package com.application.bank;

import com.application.bank.dao.impl.UserDaoImpl;
import com.application.bank.models.User;

import org.apache.log4j.Logger;

public class MainDriver {
	final static Logger loggy = Logger.getLogger(User.class);

	public static void main(String[] args) {
		UserDaoImpl uDI = new UserDaoImpl();
		
		User u = new User(7, "Ellen Ripley", "ripley@email.com", "8764958763", "newt", "employee");
		loggy.info("Created new user");
		uDI.insertUser(u);
		loggy.info("Called insertUser method");
		
		

	}

}




