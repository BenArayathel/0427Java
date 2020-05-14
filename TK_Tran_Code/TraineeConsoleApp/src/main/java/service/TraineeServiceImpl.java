package service;

import dao.TraineeDAO;
import dao.TraineeDAOImpl;
import exception.BusinessException;
import model.Trainee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
	Concrete class that implements TraineeService's abstract methods.
	First level filtration (input validation) implemented here.
	If things are validly formatted locally THEN communicate with DB using TraineeDAO's abstract methods.
 */
public class TraineeServiceImpl implements TraineeService {

	// dao Object that will be communicating to DB.
	private TraineeDAO dao = new TraineeDAOImpl();

	// Custom methods
	private boolean isValidContact(long contact) { // Validates contact number format
		boolean b = false; // Good practice to start off false
		if ((contact + "").matches("[0-9]{10}")) { // (contact + "") is a shortcut to convert long to String
			b = true;
		}
		return b;
	}

	private boolean isValidID(String id) { // Validates ID format
		boolean b = false;
		if (id.matches("TR[A-Z]{2}[0-9]{12}")) {
			b = true;
		}
		return b;
	}

	private boolean isValidName(String s) { // Validates name format
		boolean b = false;
		if (s.matches("[a-zA-Z]{2,15}")) { // {2,15} is {min,max}
			b = true;
		}
		return b;
	}

	public Date isValidDate(String dob) throws BusinessException {
		Date d = null;
		if (dob.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
			sdf.setLenient(false);
			try {
				d = sdf.parse(dob);
			} catch (ParseException e) {
				throw new BusinessException("Entered date " + dob + " is invalid.");
			}
		} else {
			throw new BusinessException("Entered date " + dob + " should be in (MM.dd.yyyy) format only.");
		}
		return d;
	}


	@Override
	public Trainee createTrainee(Trainee trainee) throws BusinessException {
		if (trainee == null) {
			throw new BusinessException("Trainee Object was not created.");
		} else if (!isValidContact(trainee.getContact())) {
			throw new BusinessException("Entered trainee contact " + trainee.getContact() + " is invalid.");
		} else if (!isValidName(trainee.getName())) {
			throw new BusinessException("Entered name " + trainee.getName() + " is invalid.");
		} else {
			// Calling up TraineeDAOImpl
			trainee = dao.createTrainee(trainee);
		}
		return trainee;
	}

	@Override
	public Trainee getTraineeByID(String id) throws BusinessException {
		Trainee trainee = null;
		if (isValidID(id)) {
			trainee = dao.getTraineeByID(id);
		} else {
			throw new BusinessException("Entered ID " + id + " is invalid.");
		}
		return trainee;
	}

	@Override
	public Trainee updateTraineeContact(String id, long newContact) throws BusinessException {
		return null;
	}

	@Override
	public void deleteTrainee(String id) throws BusinessException {

	}

	@Override
	public List<Trainee> getTraineesByCity(String city) throws BusinessException {
		return null;
	}
}
