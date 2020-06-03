package com.trainee.service.impl.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.trainee.service.TraineeService;
import com.trainee.service.impl.TraineeServiceImpl;

public class TraineeServiceImplTest {

	private static TraineeService service;
	@BeforeClass
	public static void createTraineeServiceObject() {
		service=new TraineeServiceImpl();
	}
	
	//all your test cases goes here
	
	
	@AfterClass
	public static void destroyTrainee() {
		service=null;
	}
}
