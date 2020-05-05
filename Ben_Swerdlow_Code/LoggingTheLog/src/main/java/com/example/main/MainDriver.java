package com.example.main;

import org.apache.log4j.Logger;

public class MainDriver {

	final static Logger loggy = Logger.getLogger(MainDriver.class);
	
	public static void main(String[] args) {
		
		// All logging output
		loggy.setLevel(org.apache.log4j.Level.ALL);
		// Level Hierarchy: (ALL ->) INFO -> WARN -> ERROR -> FATAL
		// Any level will report itself and all levels below it (e.g. ERROR reports ERROR and FATAL, not WARN or INFO)
		
		loggy.info("This is an info update!");
		
		if (loggy.isInfoEnabled()) {
			loggy.warn("This is a warning!");
		}
		
		// E.g.
		// if employee rejects
		// log.info(ticketRejected());
		
		// can pass messages and the error that is thown (or throw your own)
		loggy.error("This is an error", new IndexOutOfBoundsException());
		loggy.fatal("This is a fatal");
		
		OtherClass.aMethod();
		
	}
}
