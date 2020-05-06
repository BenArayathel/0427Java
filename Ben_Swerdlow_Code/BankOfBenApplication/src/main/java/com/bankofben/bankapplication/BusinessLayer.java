package com.bankofben.bankapplication;

//import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
//import java.time.LocalDate;
//import java.time.format.DateTimeParseException;
//import java.util.Scanner;
//import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class BusinessLayer {
	
	private static BusinessLayer bob;
	
//	private TreeMap<String, Customer> usernameCustomerMap;
//	private TreeMap<Integer, Customer> idCustomerMap;
//	private TreeMap<Integer, Employee> idEmployeeMap;
//	private TreeMap<Integer, Account> idAccountMap;
//	private TreeMap<String, String> usernameEmailMap;
//	private TreeMap<String, Employee> usernameEmployeeMap;
//	private TreeMap<Account, Customer> accountCustomerMap;
	
	private BusinessLayer() {
		// No default properties yet
		// TODO: Adapt to persistent data
	}

	public static BusinessLayer getBank() {
		// TODO: Adapt to persistent data
		if (bob==null) {
			// Will change when we have persistent data
			bob = new BusinessLayer();
			LocalDate dob = LocalDate.parse("1992-01-01");
			User ben;
//			try {
//				ben = new Customer("Ben", "Eli", "Swerdlow", "Toby", dob, "111-11-1111",
//						"ben@gmail.com", "555-55-5555", "benswerdlow", "lego105!"); 
////				("Ben", "Eli", "Swerdlow", "Tobias", dob, "111-11-1111", 
////						"ben@gmail.com", "555-555-5555", "benswerdlow", "Lego105!");
//				bob.usernameCustomerMap.put("benswerdlow", ben);
//				bob.usernameEmailMap.put("benswerdlow", "ben@gmail.com");
//			} catch (BlankFieldException | InvalidDateOfBirthException | InvalidSsnException | InvalidEmailException
//					| InvalidPhoneNumberException | InvalidUsernameException | InvalidPasswordException
//					| InvalidPasswordChangeException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		return bob;
	}
	
//	public User registerCustomer(User user) throws ExistingEmailException, ExistingUserException, NullPointerException  {
//		if (usernameEmailMap.values().contains(user.getEmail())) {
//			throw new ExistingEmailException();
//		} else if (usernameCustomerMap.keySet().contains(user.getUsername())) {
//			throw new ExistingUserException();
//		}
////		try {
////			// Putting these in the same try block returning a NullPointerException
////			// so if one is invalid, the other map doesn't update
////			usernameCustomerMap.put(user.getUsername(), user);
////			usernameEmailMap.put(user.getUsername(), user.getEmail());
////		} catch (NullPointerException e) {
////			throw e;
////		}
//		return user;
//	}
	
//	public User loginUser(String username, String password) throws UserNotFoundException, InvalidLoginException, NullPointerException {
//		User user = null;
//		if (!(usernameCustomerMap.keySet().contains(username))) {
//			throw new UserNotFoundException();
//		} else if (usernameCustomerMap.get(username).passwordMatch(password)) {
//			user = usernameCustomerMap.get(username);
//			return user;
//		} else {
//			throw new InvalidLoginException();
//		}
//	}
	
	public User loginUser(String username, Scanner sc) throws BusinessException {
		String password = null;
		int loginAttempts = 0;
		User user = null;
		BusinessLayer bob = BusinessLayer.getBank();
		while (loginAttempts < 4) {
			password = UserInterface.requestPassword(sc);
			bob.loginUser(username, password);
			loginAttempts++;
			// TODO: Added lag to discourage brute force attempts; not critical, attempt later
		}
		if (loginAttempts >= 4) {
			throw new BusinessException("Limit of password attempts exceeded. Please try again later.");
		}
		return user;
	}

	public User loginUser(String username, String password) throws BusinessException {
		DatabaseLayer dbl = DatabaseLayer.getDatabaseLayer();
		User user = null;
		try {
			user = dbl.validateUser(username, password);
		} catch (BusinessException e) {
			throw e;
		}
		return user;
	}
	
	private String generateAccountNumber () {
		boolean uniqueAccountNumber = false;
		Long randomTenDigitNumber;
		DatabaseLayer dbl = DatabaseLayer.getDatabaseLayer();
		do {
			randomTenDigitNumber = ThreadLocalRandom.current().nextLong((long)1e9, (long)1e10);
			uniqueAccountNumber = dbl.isUniqueAccountNumber(randomTenDigitNumber);
			/*
			 *  TODO Fix dbl.isUniqueAccountNumber(long randomTenDigitNumber) implementation to actually
			 *  		check if account number already exists in the database.
			 */
		} while (!(uniqueAccountNumber));
		return randomTenDigitNumber.toString();
	}

	public boolean userExists(String username) {
		// BusinessLayer passes database call to DatabaseLayer
		return DatabaseLayer.getDatabaseLayer().userExists(username);
//		boolean userExists;
//		try {
//			userExists = this.usernameCustomerMap.containsKey(username);
//		} catch (NullPointerException e) {
//			userExists = false;
//		}
//		return userExists;
	}
	
	public boolean emailExists(String email) {
		// BusinessLayer passes database call to DatabaseLayer
		return DatabaseLayer.getDatabaseLayer().emailExists(email);
//		boolean emailExists;
//		try {
//			emailExists = this.usernameEmailMap.containsValue(email);
//		} catch (NullPointerException e) {
//			emailExists = false;
//		}
//		return emailExists;
//	}

//	public TreeMap<String, String> getUsernameEmailMap() {
//		return usernameEmailMap;
//	}
//
//	public void setUsernameEmailMap(TreeMap<String, String> usernameEmailMap) {
//		// Make this require employee id and approval
//		// and update usernameEmailMap
//		this.usernameEmailMap = usernameEmailMap;
	}

	public void applyForAccount(User user) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
