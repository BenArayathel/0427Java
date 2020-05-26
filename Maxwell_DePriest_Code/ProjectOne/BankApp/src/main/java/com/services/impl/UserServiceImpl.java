package com.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.exceptions.BusinessException;
import com.models.User;
import com.secrets.SecretStuff;
import com.models.Account;
import com.services.UserService;
import com.dao.impl.AccountDaoImpl;
import com.dao.impl.UserDaoImpl;

public class UserServiceImpl implements UserService {
	final static Logger loggy = Logger.getLogger(User.class);
	AccountDaoImpl aDI = new AccountDaoImpl();
	UserDaoImpl uDI = new UserDaoImpl();

	public UserServiceImpl() {
		
	}
	
	@Override
	public boolean createNewUser(User u) throws BusinessException {
		boolean accountValidated = true;
		User newUser = u;
		newUser.setStatus("customer");
		if (!isValidName(newUser.getName())) {
			loggy.info("Error. Please use only letters in the name field. Please try again.");
			accountValidated = false;
		}
		else if (!isValidPhoneNumber(newUser.getPhoneNumber())) {
			loggy.info("Error. Please input your phone number with 10 digits. Ex. 1234567890");
			accountValidated = false;
		}
		if(accountValidated) {
			try {
				uDI.insertUser(newUser);
				loggy.debug("new customer account created with email " + newUser.getEmail());
				loggy.info("We'll review your application and get back to you shortly");
				return true;
			} catch (BusinessException e) {
				loggy.info(e.getMessage());
				loggy.info("Please try again");
				return false;
			}
			
		}
		loggy.info("Account not validated");
		return false;
		
		
	}// end of createNewUser
	
	@Override
	public boolean userLogin(String email, String password) throws BusinessException {
		User u = uDI.selectUserByEmail(email);
		String ePassword = passwordEncryption(password);
		if (u != null) {
			try {
				if (u.getPassword().equals(ePassword)) {
					loggy.debug("Passwords match");
					return true;
				}
			} catch (NullPointerException e) {
				//loggy.info("Either your email or password is incorrect. Please try again.");
				loggy.error("NullPointerException thrown during userLogin");
				//return false;
			}
			
		}
		loggy.error("Email or password could not be found matched in database");
		//loggy.info("Either your email or password is incorrect. Please try again.");
		return false;
	} // end of userLogin
	
	@Override
	public User setCurrentUser(String email) throws BusinessException {
		return uDI.selectUserByEmail(email);
	}
	
	@Override 
	public Account setCurrentAccount(String email) throws BusinessException {
		return aDI.selectAccountByEmail(email);
	}
	
	@Override
	public void createNewAccount(String email, String startingCheckingAmount) throws BusinessException {
		String newSavingsNum = Integer.toString(generateAccountNumber());
		String newCheckingNum = Integer.toString(generateAccountNumber());
		aDI.insertAccount(new Account("", newSavingsNum, newCheckingNum, "0.00", "0.00", "false", email));
	} // End of signUpForAccount
	
	public void viewAllAccounts(User u) {
		List<Account> allAccounts = new ArrayList<>();
		if(isEmployee(u)) {
			try {
				allAccounts = aDI.selectAllAccounts();
			} catch (BusinessException e) {
				loggy.error(e.getMessage());
				e.printStackTrace();
			}
			for (Account a : allAccounts) {
				String printOut = "Email- " + a.getEmail() + ", Checking Account Number- " + a.getCheckingAccountNumber() +
						", Savings Account Number " + a.getSavingsAccountNumber() + ", Checking Balance " + a.getCheckingBalance() +
						", Savings Balance " + a.getSavingsBalance()  + ", Account Active- " + a.getActive();
				loggy.info(printOut);
			}
		}
		else {
			loggy.info("You must be an employee to continue");
		}
	}
	
	public User findUser(String email) {
		try {
			return uDI.selectUserByEmail(email);
		} catch (BusinessException e) {
			loggy.error("Error looking for user with email "+ email);
			e.printStackTrace();
			return null;
		}
		
	}

	
	public void activatePendingAccounts(User u) {
		Scanner sc = new Scanner(System.in);
		List<Account> notActiveAccounts= new ArrayList<>();
		if(isEmployee(u)) {
			try {
				notActiveAccounts = aDI.selectAllAccountsByColumnName("active", "false");
			} catch (BusinessException e) {
				loggy.info(e.getMessage());
				loggy.error("Could not select all accounts by column name");
			}
			
			for (Account a : notActiveAccounts) {
				String printOut = "Email- " + a.getEmail() + ", Checking Account Number- " + a.getCheckingAccountNumber() +
						", Savings Account Number " + a.getSavingsAccountNumber() + ", Account Active- " + a.getActive();
				loggy.info(printOut);
			}
			
			loggy.info("Would you like to activate all pending accounts? Y or N");
			String answer = sc.next();
			if (answer.equalsIgnoreCase("y")) {
				for (Account pAccount : notActiveAccounts) {
					try {
						aDI.updateAccount(pAccount.getEmail(), "active", "true");
					} catch (BusinessException e) {
						loggy.error("Error while activating non-active accounts");
						e.printStackTrace();
					}
				}
				loggy.debug("Activating all pending accounts");
			}
			else {
				loggy.debug("Employee opted to not activate all pending accounts");
			}
			
		}
		else {
			loggy.info("You must be an employee to continue");
		}
	}// End of activatePendingAccounts

	@Override
	public String checkCheckingBalance(String email) throws BusinessException {
		return aDI.selectAccountByEmail(email).getCheckingBalance();
	}

	@Override
	public String checkSavingsBalance(String email) throws BusinessException {
		return aDI.selectAccountByEmail(email).getSavingsBalance();
	}

	@Override
	public void removeUserProfile(String email) throws BusinessException {
		
		uDI.deleteUser(email);
		aDI.deleteAccount(email);
		
	}
	
	public static int generateAccountNumber() {
		Random rand = new Random();
		int randomAccountNumber = rand.nextInt(100000);
		
		return randomAccountNumber;
	}
	
	public boolean isValidName(String testName) {
		if (testName.matches("[a-zA-Z ]{2,20}")) {
			loggy.debug("isValidName passed");
			return true;
		}
		loggy.error("isValidName failed");
		return false;
	}
	
	public boolean isValidPhoneNumber(String testNumber) {
		if (testNumber.matches("[0-9]{10}")) {
			loggy.debug("isValidPhoneNumber passed");
			return true;
		}
		loggy.error("isValidPhoneNumber failed");
		return false;
	}
	
	public boolean isValidDeposit(String testMoney) {
		if(testMoney.matches("[0-9.]{1,7}") && Double.parseDouble(testMoney) > 0 && Double.parseDouble(testMoney) <= 1000.0) {
			loggy.debug("isValidDeposit passed");
			return true;
		}
		else {
			loggy.info("Error. Please try again. Amount cannot be larger than $1000");
			loggy.error("isValidDeposit failed");
			return false;
		}
	}
	public boolean isEmployee(User u) {
		return u.getStatus().equals("employee") ? true : false;
	}

	@Override
	public void depositMoney(String whichAccount, String amount, String email) throws BusinessException {
		Account userAccount = aDI.selectAccountByEmail(email);
		double currentBalance = 0.0;
		if (amount.matches("[0-9.]{1,7}")) {
			if (Double.parseDouble(amount) >= 0) {
				try {
					if (whichAccount.equalsIgnoreCase("checkingBalance")) {
						currentBalance = Double.parseDouble(userAccount.getCheckingBalance());
					}
					else {
						currentBalance = Double.parseDouble(userAccount.getSavingsBalance());
					}
					if(amount.matches("[0-9.]{1,7}"))  {
						currentBalance += Double.parseDouble(amount);
						aDI.updateAccount(email, whichAccount, (currentBalance + ""));
						
					}
				}catch(NullPointerException e) {
					loggy.error("depositMoney threw NullPointException e- " + e);
					e.printStackTrace();
				}// end try block
			}
			else {
				loggy.info("Numbers cannot be negative");
				loggy.error("Negative number entered - deposit");
			}// end no negatives if check
		}
		else {
			loggy.info("Number must contain only numerical digits and non-negative");
			loggy.error("Non-numerical characters were used - deposit");
		}// end no letters if check
		
	} // End of depositMoney()

	@Override
	public void withdrawMoney(String whichAccount, String amount, String email) throws BusinessException {
		double currentBalance = 0.00;
		Account userAccount = aDI.selectAccountByEmail(email);
		
		if (amount.matches("[0-9.]{1,7}")) {
			if (Double.parseDouble(amount) >= 0) {
				try {
					currentBalance = whichAccount.equalsIgnoreCase("checkingBalance") ? Double.parseDouble(userAccount.getCheckingBalance()) : Double.parseDouble(userAccount.getSavingsBalance());
					if(currentBalance - Double.parseDouble(amount) > 0)  {
						currentBalance -= Double.parseDouble(amount);
						loggy.debug(amount + " was taken from account with email" +  email);
						aDI.updateAccount(email, whichAccount, (currentBalance + ""));
						
					}
					else {
						loggy.info("Error. Insufficient funds. Your current balance is $" + currentBalance);
						loggy.error("Current balance was smaller than requested amount during withdrawl");
					}
				} catch(NullPointerException e){
					loggy.error("Caught NullPointerException at withdrawMoney()");
					loggy.info("Error. Please try again");
				}
			}
			else {
				loggy.info("Numbers cannot be negative");
				loggy.error("Negative number entered - withdrawl");
			}// end no negatives if check
		}
		else {
			loggy.info("Number must contain only numerical digits and non-negative");
			loggy.error("Non-numerical characters were used - withdrawl");
		}// end no letters if check
		
	}// end of withdrawMoney()
	
	public boolean checkForAccount(User u) {
		try {
			Account a = aDI.selectAccountByEmail(u.getEmail()); 
			if(a.getId() != null && a.getActive().equals("true") ) {
				return true;
			}
		} catch (BusinessException e) {
			loggy.info("Couldn't find account with that email.");
		}
		return false;
	}
	
	@Override
	public boolean transferFunds(String uEmail, String fromWhichAccount, String receivingAccountNumber, String amt) throws BusinessException {
		try {
			//Account sendingAccount = aDI.selectAccountByEmail(uEmail);
			Account receivingAccount = aDI.selectAccountByColumnName("checkingNumber", receivingAccountNumber);
			withdrawMoney(fromWhichAccount, amt, uEmail);
			depositMoney("checkingBalance", amt, receivingAccount.getEmail());
			return true;
		} catch (BusinessException e) {
			loggy.error("Exception thrown during transfer funds");
			e.printStackTrace();
			return false;
			
		}
		
		
	}
	
	public void printStatement(User u) {
		try {
			loggy.info("Checking Account Balance- $" + checkCheckingBalance(u.getEmail()));
			loggy.info("Savings Account Balance- $" + checkSavingsBalance(u.getEmail()));
		} catch (BusinessException e) {
			loggy.error("Error during print statement");
			e.printStackTrace();
		}
		
	}
	
	
	public static String passwordEncryption(String pw) {
	loggy.debug("Encrypting password");
	try {
		StringBuilder newPassword = new StringBuilder();
		String original = "ZYXWVUTSRQPONMLKJIHGFEDCBAabcdefghijklmnopqrstuvwxyz0987654321";  
		String alternate = "1234567890zyxwvutsrqponmlkjihgfedcbaABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
		String[] arr = alternate.split("");
		String[] wordArray = pw.toLowerCase().split("");
		int tempIndex;
		for(int k = 0; k < (wordArray.length); k++) {
			String tempLetter = wordArray[k];
			tempIndex = original.indexOf(tempLetter); 
			newPassword.append(arr[tempIndex]);
		}
		return newPassword.toString();
	}
	catch (IndexOutOfBoundsException e) {
		loggy.error("IndexOOB exception");
		return "fail";
	
		}
	}

}// End of class
