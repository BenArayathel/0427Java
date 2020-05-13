package com.example.main;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MainDriver {
	
	final static Logger loggy = Logger.getLogger(MainDriver.class);
	
	public static void main(String[] args) {
		
		//info -> warn -> error -> fatal
		loggy.setLevel(Level.INFO);
		
		loggy.info("THIS IS AN INFO UPDATE!!!!");
		loggy.warn("WARNING AGAIN!");
		
//		if(loggy.isInfoEnabled()) {
//			loggy.warn("THIS IS A WARNING!!!");
//		}
		
		//if employee rejects
		//log.info(ticket rejected);
		
		loggy.error("This is an error", new IndexOutOfBoundsException());
		loggy.fatal("This is fatal!");
		
		OtherClass.aMethod();
		
		
	}

}
