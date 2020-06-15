package com.example.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.dao.CarsRepo;
import com.example.model.Cars;

public class MainDriver {

	public static ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	public static CarsRepo carRepo = appContext.getBean("carRepo", CarsRepo.class);
//	public static CarsRepo carRepo = appContext.getBean("carRepo", com.example.dao.impl.CarsRepo.class);

	
	public static void main(String[] args) {
		
		carRepo.insertCar(new Cars(0,"Tesla","Model S",360));
		carRepo.insertCar(new Cars(0,"Tesla","Model E",460));
		carRepo.insertCar(new Cars(0,"Tesla","Model X",370));
		carRepo.insertCar(new Cars(0,"Tesla","Model Y",460));

		
		System.out.println("SELECT ALL:");
		System.out.println(carRepo.selectAllCars());
		
		System.out.println("SELECT BY ID");
		System.out.println(carRepo.selectCarById(3));

		

	}

}
