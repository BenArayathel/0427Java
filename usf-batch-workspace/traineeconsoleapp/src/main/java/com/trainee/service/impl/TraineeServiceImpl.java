package com.trainee.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.trainee.dao.TraineeDAO;
import com.trainee.dao.impl.TraineeDaoImpl;
import com.trainee.exception.BusinessException;
import com.trainee.model.Trainee;
import com.trainee.service.TraineeService;

public class TraineeServiceImpl implements TraineeService {
	private TraineeDAO dao = new TraineeDaoImpl();

	@Override
	public Trainee createTrainee(Trainee trainee) throws BusinessException {
		if (trainee == null) {
			throw new BusinessException("Trainee Object was not created");
		} else if (!isValidContact(trainee.getContact())) {
			throw new BusinessException("Entered Trainee Contact " + trainee.getContact() + " is invalid");
		} else if (!isValidName(trainee.getName())) {
			throw new BusinessException("Entere Name " + trainee.getName() + " is invalid");
		} else {
			// calling up DAO
			trainee=dao.createTrainee(trainee);
		}
		return trainee;
	}

	private boolean isValidContact(long contact) {
		boolean b = false;
		if ((contact + "").matches("[0-9]{10}")) {
			b = true;
		}
		return b;
	}

	private boolean isValidId(String id) {
		boolean b = false;
		if (id.matches("TR[A-Z]{2}[0-9]{12}")) {
			b = true;
		}
		return b;
	}

	private boolean isValidName(String name) {
		boolean b = false;
		if (name.matches("[a-zA-Z]{2,15}")) {
			b = true;
		}
		return b;
	}

	@Override
	public Trainee getTraineeById(String id) throws BusinessException {
		Trainee trainee=null;
		if(isValidId(id)) {
			trainee=dao.getTraineeById(id);
		}else {
			throw new BusinessException("Entered Id "+id+" is invalid");
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
	public static Date isValidDate(String dob) throws BusinessException {
		Date d=null;
		if(dob.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
			SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
			sdf.setLenient(false);
			try {
				d=sdf.parse(dob);
			} catch (ParseException e) {
				throw new BusinessException("Entered date "+dob+" is invalid");
			}
		}else {
			throw new BusinessException("Entered date "+dob+" should be in (dd.MM.yyyy) format only");
		}
		return d;
	}
}
