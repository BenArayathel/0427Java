package com.application.bank;

import com.application.bank.dao.impl.UserDaoImpl;
import com.application.bank.exception.BusinessException;
import com.application.bank.models.User;

import org.apache.log4j.Logger;

public class MainDriver {
	final static Logger loggy = Logger.getLogger(User.class);

	public static void main(String[] args) {
		UserDaoImpl uDI = new UserDaoImpl();
		User u2 = new User();
		
//		User u2 = new User("9", "Han Solo", "han@email.com", "8129988", "falcon", "customer");
//		try {
//			uDI.insertUser(u2);
//		} catch (BusinessException e) {
//			loggy.warn("Couldn't create a new user. Please try again.");
//			e.printStackTrace();
//		}
		
		
		try {
			User u5 = uDI.selectUserByEmail("max@email.com");
			User u6 = uDI.selectUserByColumnName("name", "Max DePriest");
			loggy.info("The name of this user is " + u6.getEmail());
		} catch (BusinessException e) {
			//loggy.warn("Couldn't select user. Please try again.");
			e.printStackTrace();
		}
		
		
		

	}

}




