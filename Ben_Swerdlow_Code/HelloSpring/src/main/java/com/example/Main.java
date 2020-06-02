package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.service.PlanetService;
//import com.example.service.PlanetServiceImpl;

/*
 * What is Spring?
 * 		Spring is a module based, open source, Java Aspect Oriented Programming (AOP) framework. It provides support for
 * 		enterprise level applications, it provides infrastructure so you can focus your application's business logic.
 * 	
 * What is a module?
 * 		A grouping of libraries that work together to achieve some goal using re-usable abstracted code. In spring, a module
 * 		will take the form of a dependency (in our case, a maven dependency)
 * 
 * What are some modules in Spring?
 * 		Core, Beans, Context, ORM, AOP, WebMVC, Security,  Test, etc.
 * 
 * How does Spring achieve its goals?
 * 		Inversion of Control (IOC)
 * 		Aspect Oriented Programming (AOP)
 * 		Model View Controller Design (MVC)
 * 		Many abstracted APIs
 * 
 * What is IOC?
 * 		A framework that will manage object creation (we don't create object, IOC does), its life cycle, and its dependencies (which will be injected)
 * 
 * What is Application Context?
 * 		It's a type of bean factory that
 * 
 * What is a bean?
 * 		A bean is an object that you've told spring what we want it to manage for us
 * 
 * What is bean wiring?
 * 		Bean wiring is the process of telling spring to manage objects for you
 * 		AND also defining what dependencies those objects may have (can be done with annotations as well as xml)
 * 
 * What are the types of injections?
 * 		Setter
 * 		Constructor
 * 		Field (be careful with this one)
 */

public class Main {
	
	private static ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");
	
	/*
	 * Don't want tight coupling. We don't want to use the new keyword.
	 * 
	 * LOOK UP THE DISADVANTAGES OF TIGHTLY COUPLING!!!!! WHY DO WE USE SPRING TO AVOID THIS!!!!!
	 * Extend Hibernate assignment with Planets and re-create it with no annotations (xml only)
	 * 
	 * Extension: Alter wire.
	 * 
	 * We instead want the following:
	 */
	
//	private static PlanetService pServ = new PlanetServiceImpl();
	private static PlanetService pServ;

	public static void main(String[] args) {
		
		// This asks spring service to make the PlanetService object for us
		pServ = appContext.getBean("PlanServiceFIRST", PlanetService.class);
		// This seems overkill, but we can have multiple PlanetService beans that have different configurations and we just reference them very simply 
		// instead of setting them up the same way each time.
		
		System.out.println(pServ.getAllPlanets());

	}

}
