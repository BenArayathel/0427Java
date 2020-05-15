package service;

import org.apache.log4j.Logger;

/*
	Service (Business) Layer
	Provides and implements methods for service (business) level validation.
 */
public class Service {

	static final Logger log = Logger.getLogger(Service.class);

	// GENERIC METHODS
	public void displayWelcome() {
		log.info("Welcome to Cash R' Us, where your money is ours!");
	}

	public void displayGoodbye() {
		log.info("Thanks for being a loyal Cash R' Us customer!");
	}

	public void displayLogOut() {
		log.info("Logging out, routing back to main portal.");
	}

	public void displayMainPortal() {
		log.info("\n---[ Main Portal ]---");
		log.info("Select an Option");
		log.info("1. Register");
		log.info("2. Login");
		log.info("3. Exit");
	}

	public void displayLoginPortal() {
		log.info("\n---[ User Portal ]---");
		log.info("Select an Option");
		log.info("1 = View Account");
		log.info("2 = Deposit");
		log.info("3 = Withdraw");
		log.info("4 = Transfer Money");
		log.info("---[ Employee Portal ]---");
		log.info("5 = View all Accounts (Employees ONLY)");
		log.info("6 = View all Transactions (Employees ONLY)");
		log.info("7 = Log Out");
	}

	// FIRST-LEVEL VALIDATION
	public boolean isValidUsername(String username) {
		boolean b = false;
		if (username.matches("[a-zA-Z0-9]{3,15}")) {
			b = true;
		} else {
			log.warn("Invalid, username must be between [3-15] alphanumeric characters.");
		}
		return b;
	}

	public boolean isValidPassword(String password) {
		boolean b = false;
		if (password.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}")) {
			b = true;
		} else {
			log.warn("Invalid, password must be a minimum of 8 characters including 1 uppercase, 1 lowercase, 1 digit, and 1 special character [!@#$%^&*_].");
		}
		return b;
	}

	public boolean isValidAmount(String amount) {
		boolean b = false;
		if (amount.matches("^\\d{1,5}")) {
			b = true;
		} else {
			log.warn("Invalid, amount must be whole numbers between 1-5 digits long.");
		}
		return b;
	}
}
