import org.apache.log4j.Logger;

public class OtherClass {

	final static Logger loggy = Logger.getLogger(OtherClass.class);

	public static void aMethod() {
		System.out.println("Printing info log from another class: ");
		loggy.info("I'm a log from another class!");
	}
}
