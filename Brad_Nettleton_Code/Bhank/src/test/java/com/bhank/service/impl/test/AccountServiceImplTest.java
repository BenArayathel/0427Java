package com.bhank.service.impl.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bhank.service.impl.AccountServiceImpl;

public class AccountServiceImplTest {

	private static AccountServiceImpl service;
	
	@BeforeClass
	public static void createAccountServiceImplObject() {
		service=new AccountServiceImpl();
	}
	
	@Test
	public void createAccountTest() {
		
	}
	
	@AfterClass
	public static void destroyAccountServiceImplObject() {
		service=null;
	}
}
