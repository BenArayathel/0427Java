package com.protestorganizer.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.protestorganizer.dao.ProtestRepo;

public class ProtestRepoTest {
	
	public static ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private ProtestRepo protRepo = appContext.getBean(ProtestRepo.class);
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("Testing ProtestRepo");
	}
	
	@Before
	public void before() {
		System.out.println("Starting test");
	}
	
	@Test
	public void testProtestCreationDeletion() {
		
	}
	
	@After
	public void after() {
		System.out.println("Completed test");
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("Finished testing ProtestRepo");
	}

}
