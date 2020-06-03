package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.service.PlanetService;
import com.example.service.PlanetServiceImpl;

/*
 * What is Spring?
 * 		Spring is a module based, open source, Java AOP framework. It provides support for enterprise level applications, 
 * 		it provides infrastructrue so you can focus your application's business logic. 
 * 		(Also, AOP is "Aspect Oriented Programming")
 * 
 * What is a module?
 * 		A gouping of libraries that work together to achieve some oal using reusable abstracted code. In spring, a module 
 * 		will take the form a dependency (in our case a maven dependency)
 * 
 * What are some moduels in spring?
 * 		core, beans, context, orm, aop, webmvc, secutiry, test, etc. 
 * 
 * How does Spring achieve its goals?
 * 		Inversion of Control (IoC)
 * 		Aspect Oriented Programming (AoP)
 * 		Model View Controller Design (MVC)
 * 		A ton of Abstracted APIs 
 * 
 * What is IoC Container?
 * 		A framework that will manage object creation, its lifecycle and also inject dependencies to the class. 
 * 
 * What is ApplicaitonContext?
 * 		
 */

public class MainDriver {
	
	private static ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");
	
	/*
	 * no more tightly coupling? 
	 */
	
//	private static PlanetService pServ = new PlanetServiceImpl();
	
	private static PlanetService pServ;
	
	public static void main(String[] args) {
		
//		pServ = new PlanetServiceImpl(pDao);
		
		pServ = appContext.getBean("PlanService", PlanetService.class);
//		pServ = appContext.getBean("PlanServiceFIRST", PlanetService.class);
//		pServ = appContext.getBean("PlanServiceSECOND", PlanetService.class);
//		pServ = appContext.getBean("PlanServiceSECOND", PlanetService.class);
		
		System.out.println(pServ.getAllPlanets());
		System.out.println(PlanetServiceImpl.howManyServicesDidIMake);
	}

}
