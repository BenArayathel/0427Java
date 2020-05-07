package com.trainee.services;

import java.util.List;

import com.trainee.exception.BusinessException;
import com.trainee.model.Trainee;

public interface TraineeService {
	
	/*
	 * Validation checks should happen here, not in the presentation layer
	 */

	public Trainee createTrainee(Trainee trainee) throws BusinessException;
	public Trainee getTraineeById(String id) throws BusinessException;
	public Trainee updateTraineeContact(String id, long newContact) throws BusinessException;
	public void deleteTrainee(String id) throws BusinessException;
	public List<Trainee> getTraineesByCity(String city) throws BusinessException;
	
}
