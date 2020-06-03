package com.example;

import org.springframework.stereotype.Component;

@Component("appProxy")
public class MyAppProxy {
	/*
	 * A visualization of an entire application
	 */

	public MyAppProxy() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// examples of cross-cutting concerns (CCCs) that can flood your methods
	public void randomMethod() {
		// couple of lines of security
		
		// couple of lines of exception handling
		
		// few lines for caching
		
		// few lines for logging
		
		// after a lot of lines of code, we have our actual business logic
		System.out.println("Inside Random Method");
	}
	
	public void anotherMethod() {
		// 1 method call for security
		// 1 method call for exception handling
		// 1 method call for caching
		// 1 method call for logging
		
		System.out.println("Inside Another Method");
		
		// We still have 4 lines of code that are boiler plate and have nothing to do with unique business logic even if we make them functions
	}
	
//	protected int doingJudo(Character a, Integer b) {
//		System.out.println("Doing Judo");
////		System.out.println(b);
//		return 0;
//	}
	
	public void doingBJJ() {
		System.out.println("Doing a bit of jujitsu");
	}
	
	public String doingDiscGolf(Integer c) {
		System.out.println("Doing disc golf");
		return "";
	}

}
