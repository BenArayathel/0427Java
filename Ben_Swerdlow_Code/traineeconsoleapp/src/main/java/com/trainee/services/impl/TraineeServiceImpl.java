package com.trainee.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.trainee.dao.TraineeDAO;
import com.trainee.dao.impl.TraineeDAOImpl;
import com.trainee.exception.BusinessException;
import com.trainee.model.Trainee;
import com.trainee.services.TraineeService;

public class TraineeServiceImpl implements TraineeService {
	
	private TraineeDAO dao = new TraineeDAOImpl();

	@Override
	public Trainee createTrainee(Trainee trainee) throws BusinessException {
		if (trainee==null) {
			throw new BusinessException("Trainee object was not passed.");
		} else if (!isValidContact(trainee.getContact())) {
			throw new BusinessException("Entered Trainee contact "+trainee.getContact()+" is invalid. Please try again.");
//		} else if (!isValidId(trainee.getId())) {
//			// isValidId will check if the id is null throw a business exception if so
//			// this is usually good, but we need it to be null to create a new trainee.
//			throw new BusinessException("Entered Id "+trainee.getId()+" is invalid. Please try again.");
		} else if (!isValidAlphaString(trainee.getName())) {
			throw new BusinessException("Entered name"+trainee.getName()+" is invalid. Please try again.");
//		} else if (the other stuff) {
		} else {
			//call the DAO to put valid trainee in the database
			trainee = dao.createTrainee(trainee);
		}
		return trainee;
	}
	
	private boolean isValidContact(long contact) {
		boolean isValid = false;
		if ((contact+"").matches("[0-9]{10}")) {
			isValid = true;
		}
		return isValid;
	}
	
	private boolean isValidId(String id) throws BusinessException {
		boolean isValid = false;
		if (id==null) {
			throw new BusinessException("There is no entry for trainee's Id. A trainee Id must be supplied");
		} else if (id.matches("TR[A-Z]{2}[0-9]{12}")) {
			isValid = true;
		}
		return isValid;
	}
	
	private boolean isValidAlphaString (String alphaString) {
		boolean isValid = false;
		if (alphaString.matches("[A-Za-z]{2,15}")) {
			isValid = true;
		}
		return isValid;
	}
	
	public static boolean isValidDate(String dob) {
		boolean isValid = false;
		if (dob.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
			isValid = true;
		}
		return isValid;
	}
	
	public static Date makeValidDate(String dob) throws BusinessException {
		Date d = null;
		if(isValidDate(dob)) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			sdf.setLenient(false);
			try {
				d = sdf.parse(dob);
			} catch (ParseException e) {
				throw new BusinessException("Entered date "+dob+" is invalid.");
			}
		} else {
			throw new BusinessException("Entered date "+dob+" is invalid.");
		}
		return d;
	}

	@Override
	public Trainee getTraineeById(String id) throws BusinessException {
		Trainee trainee = null;
		if (isValidId(id)) {
			trainee = dao.getTraineeById(id);
		} else {
			throw new BusinessException("Id "+id+" is not a valid id.");
		}
		return trainee;
	}

	@Override
	public Trainee updateTraineeContact(String id, long newContact) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTrainee(String id) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Trainee> getTraineesByCity(String city) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
