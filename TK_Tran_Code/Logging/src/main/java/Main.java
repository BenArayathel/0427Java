//// BAD IMPORTS
//import java.util.logging.Level;
//import java.util.logging.Logger;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Main {

	// Get a logger instance
	final static Logger loggy = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		// Set logger's level.
		// ALL > DEBUG > INFO -> WARN -> ERROR -> FATAL > OFF > TRACE
		loggy.setLevel(Level.INFO);
		loggy.info("This is an info log.");
		loggy.warn("This is a warn log."); // Implicitly enabled bc WARN >= INFO
		loggy.debug("This is a debug log."); // Disabled bc DEBUG < INFO
		loggy.fatal("This is a fatal.");
		loggy.info("This is an info log that throws an exception.", new IndexOutOfBoundsException());

		if (loggy.isInfoEnabled()) {
			loggy.warn("This is a warn log within an if statement.");
		}

		// P0 example:
		// If employee rejects an account, log.INFO(ticket rejected);
	}
}