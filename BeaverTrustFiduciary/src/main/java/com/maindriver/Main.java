package com.maindriver;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.welcomeview.WelcomeView;

public class Main {
	
	final static Logger myLog = Logger.getLogger(Main.class);

	public static void main(String[] args) {
//		central hub for behavior
//		should have very few commands, just start the ball rolling
		
		if (myLog.isInfoEnabled()) {
			myLog.info("Hey, it workith!");			
		}
				
		myLog.warn("HEY!");
		
	     
	     //my logger only works if my call to this object/method comes after the logging stuff!
		 WelcomeView.welcome();


	}
	


}
