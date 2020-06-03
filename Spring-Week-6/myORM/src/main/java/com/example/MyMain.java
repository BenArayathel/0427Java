package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.Dao;
import com.model.Car;

public class MyMain {
	
	public static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	public static Dao carDao = ac.getBean("carRepoDao", Dao.class);

	public static void main(String[] args) {
		
		Car carM = new Car(1, "Mini");
		carDao.createCar(carM);
		
		Car carToyota = new Car(2, "Toyota");
		carDao.createCar(carToyota);
		
		Car car = new Car(3, "Chevy");
		carDao.createCar(car);
		
		System.out.println(carDao.selectAll());
		
		carDao.deleteCar(car);
		
		System.out.println(carDao.selectAll());
		
		carToyota.setName("ToyotaLandCruiser");
		
		carDao.updateCar(carToyota);
		
		System.out.println(carDao.selectAll());
		
		System.out.println("myFav: " + carM.toString());
	}

}
