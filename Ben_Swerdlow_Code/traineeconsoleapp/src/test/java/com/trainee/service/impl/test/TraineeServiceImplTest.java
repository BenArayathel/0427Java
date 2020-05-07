package com.trainee.service.impl.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.trainee.services.TraineeService;
import com.trainee.services.impl.TraineeServiceImpl;

public class TraineeServiceImplTest {
	
	private static TraineeService service;
	
	@BeforeClass
	public static void createTraineeServiceObject() {
		service = new TraineeServiceImpl();
	}
	
	@Test
	public static void createTraineeTest() {
//		testing the thing.
	}
	
	@AfterClass
	public static void destroyTrainee() {
		service = null;
		// Do this so it'll be destroyed by garbage collection.
	}
}
