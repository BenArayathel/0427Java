package com.bhank.service.impl.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bhank.service.impl.EmployeeServiceImpl;

public class EmployeeServiceImplTest {
	
	private static EmployeeServiceImpl service;
	
	@BeforeClass
	public static void createEmployeeServiceImplObject() {
		service=new EmployeeServiceImpl();
	}
	
	@Test
	public void createEmployeeTest() {
		
	}
	
	@AfterClass
	public static void destroyEmployeeServiceImplObject() {
		service=null;
	}
}
