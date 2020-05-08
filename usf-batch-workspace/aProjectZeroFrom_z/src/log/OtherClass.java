package log;

import org.apache.log4j.Logger;



public class OtherClass {
	
	public static void aMethod() {
		System.out.println("This is SYSOUT: In another class!!");
		Log.loggy.info("This is LOG4J: I'm in another class!");
	}

}
