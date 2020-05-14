package service;

import exception.BusinessException;
import model.Trainee;

import java.util.List;

/*
	Service Layer; between presentation and persistence layer (DB); enables first-level filtration.
	TraineeService interface provides abstract methods to be implemented by concrete class (TraineeServiceImpl).
	Mainly used just for validation purposes.
 */
public interface TraineeService {

	public abstract Trainee createTrainee(Trainee trainee) throws BusinessException;

	public abstract Trainee getTraineeByID(String id) throws BusinessException;

	public abstract Trainee updateTraineeContact(String id, long newContact) throws BusinessException;

	public abstract void deleteTrainee(String id) throws BusinessException;

	public abstract List<Trainee> getTraineesByCity(String city) throws BusinessException;
}
