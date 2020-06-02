package com.example;

import org.springframework.stereotype.Component;

@Component("appProxy")
public class MyAppProxy {

	public MyAppProxy() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//examples of how CCCs can flood your methods 
	public void randomMethod() {
		//couple of lines of security 
		
		//couple of lines dedicated to exception handling 
		
		//few lines for caching 
		
		//few lines for logging
		
		//after a lot of lines of coding , we end up business logic 
		
		System.out.println("Inside Random Method");
		
	}
	
	public void anotherMethod(Integer a ) {
		// 1 method call for security 
		//1 method call for exception handling 
		// 1 method class for caching 
		//1 method for logging
		
		System.out.println("Insidde another method");
		
		//We still have 4 lines of code htat have NOTHING to do with business logic, using methods. 
	}
	
//	protected int doingJudo(char a, int b) {
//		
//		System.out.println("Doing Judo ");
//		
//		return 0;
//	}
	
	public void doingBJJ() {
		System.out.println("Doing a bit of jujitsu");
	}
	
	public String doingDiscGold(Integer c) {
		
		System.out.println("Doing disc golf");
		
		return "";
	}

}
