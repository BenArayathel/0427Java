package com.example.main;

import org.apache.log4j.Logger;

public class OtherClass {
	
//	final static Logger loggy = Logger.getLogger(OtherClass.class);
	
	public static void aMethod() {
		System.out.println("In another class!!");
		MainDriver.loggy.info("I'm in another class!");
	}

}
