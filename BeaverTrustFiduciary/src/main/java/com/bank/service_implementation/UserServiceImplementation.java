package com.bank.service_implementation;

import com.bank.dao_implementation.UserDAOImplementation;
import com.bank.dao_interface.UserDAOInterface;
import com.bank.models.User;
import com.bank.service_interface.UserServiceInterface;
import com.bank.tools.BankException;


public class UserServiceImplementation implements UserServiceInterface {
	//note for the future: the reason this couldn't be private is because I was
	//trying to declare it down in the method!!!
	private UserDAOInterface udi = new UserDAOImplementation();

	//consider what this does exactly and take a note...it passes the value from layer to layer right?
	@Override
	public User createUser(User user) throws BankException {
		//create instance of interface to access methods

		user = udi.createUser(user);
		
		return null;
	}
	
//	@Override
//	public Trainee createTrainee(Trainee trainee) throws BusinessException {
//		if (trainee == null) {
//			throw new BusinessException("Trainee Object was not created");
//		} else if (!isValidContact(trainee.getContact())) {
//			throw new BusinessException("Entered Trainee Contact " + trainee.getContact() + " is invalid");
//		} else if (!isValidName(trainee.getName())) {
//			throw new BusinessException("Entere Name " + trainee.getName() + " is invalid");
//		} else {
//			// calling up DAO
//			trainee=dao.createTrainee(trainee);
//		}
//		return trainee;
//	}
//
//	private boolean isValidContact(long contact) {
//		boolean b = false;
//		if ((contact + "").matches("[0-9]{10}")) {
//			b = true;
//		}
//		return b;
//	}
//
//	private boolean isValidId(String id) {
//		boolean b = false;
//		if (id.matches("TR[A-Z]{2}[0-9]{12}")) {
//			b = true;
//		}
//		return b;
//	}
//
//	private boolean isValidName(String name) {
//		boolean b = false;
//		if (name.matches("[a-zA-Z]{2,15}")) {
//			b = true;
//		}
//		return b;
//	}

}
