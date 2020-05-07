package com.trainee.dao;

import java.util.List;

import com.trainee.exception.BusinessException;
import com.trainee.model.Trainee;

public interface TraineeDAO {
	
	/*
	 * This layer does all the actual getting of data. Methods should match the services
	 */

	public Trainee createTrainee(Trainee trainee) throws BusinessException;
	public Trainee getTraineeById(String id) throws BusinessException;
	public Trainee updateTraineeContact(String id, long newContact) throws BusinessException;
	public void deleteTrainee(String id) throws BusinessException;
	public List<Trainee> getTraineesByCity(String city) throws BusinessException;
	public List<Trainee> getAllTrainees() throws BusinessException;
	
	

}
