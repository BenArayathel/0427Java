package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.dao.AccountRepo;
import com.example.model.SimpleAccount;

public class Main {
	
	public static ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	public static AccountRepo accRepo = appContext.getBean("accountRepo", AccountRepo.class);

	public static void main(String[] args) {
		accRepo.insert(new SimpleAccount(0, 0.0));
		accRepo.insert(new SimpleAccount(0, 0.01));
		accRepo.insert(new SimpleAccount(0, 10));
		accRepo.insert(new SimpleAccount(0, 999.99));
		accRepo.insert(new SimpleAccount(0, 50.50));
		
		System.out.println(accRepo.selectAll());
	}

}
