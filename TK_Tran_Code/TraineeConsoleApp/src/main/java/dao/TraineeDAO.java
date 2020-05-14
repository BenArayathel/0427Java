package dao;

import exception.BusinessException;
import model.Trainee;

import java.util.List;

/*
	Interface similar to TraineeService; but TraineeService handles business logic (input validation).
	This DAO interface provides abstract methods to communicate with the DB; concrete class (TraineeDAOImpl) must implement.
	Mainly used for communicating with DB.
 */
public interface TraineeDAO {

	public abstract Trainee createTrainee(Trainee trainee) throws BusinessException;

	public abstract Trainee getTraineeByID(String id) throws BusinessException;

	public abstract Trainee updateTraineeContact(String id, long newContact) throws BusinessException;

	public abstract void deleteTrainee(String id) throws BusinessException;

	public abstract List<Trainee> getTraineesByCity(String city) throws BusinessException;

	// This can be placed here instead of TraineeService because it doesn't need any validation.
	public abstract List<Trainee> getAllTrainees() throws BusinessException;
}
