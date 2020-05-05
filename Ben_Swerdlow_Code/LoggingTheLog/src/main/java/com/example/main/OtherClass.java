package com.example.main;

public class OtherClass {
	
//	final static Logger loggy = Logger.getLogger(OtherClass.class);
	
	public static void aMethod() {
		System.out.println("In another class");
		MainDriver.loggy.info("I am in another class!");
	}

}
