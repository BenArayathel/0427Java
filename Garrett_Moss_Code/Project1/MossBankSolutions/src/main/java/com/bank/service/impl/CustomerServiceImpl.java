package com.bank.service.impl;

import org.apache.log4j.Logger;

import com.bank.dao.CustomerDAO;
import com.bank.dao.impl.CustomerDaoImpl;
import com.bank.exception.BankException;
import com.bank.main.MainDriver;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	
	final static Logger logger = Logger.getLogger(MainDriver.class);
	
	private CustomerDAO dao = new CustomerDaoImpl();

	public Customer createCustomerAccount(Customer createAccount) throws BankException {

		if (createAccount == null) {
			logger.info("Customer account was not created");
		}else {
			createAccount=dao.createCustomerAccount(createAccount);
		}
		return createAccount;
	}

	
	private boolean validAccountNumber(String accountNumber) {
		boolean b = false;
		if (accountNumber.matches("[0-9]{10}")) {
			b=true;
		}
		return b;
	}
	public Customer viewBalance(String accountNumber) throws BankException {

		Customer customer=null;
		if(validAccountNumber(accountNumber)==true) {
		customer=dao.viewBalance(accountNumber);
		}else {
			logger.info("Account number does not exist");
		}
		return customer;
	}
	
	public boolean validWithdrawAmount(String withdraw, String accountBalance) {
		boolean b = false;
		if (withdraw.matches("[0-9]{1,10}[.]{1}[0-9]{2}") && (Double.parseDouble(withdraw) < Double.parseDouble(accountBalance))) {
			b=true;
		} else if (withdraw.matches("[0-9]{1,10}[.]{1}[0-9]{2}") && (Double.parseDouble(withdraw) > Double.parseDouble(accountBalance))) {
			b=false;
		}
		return b;
	}

	public Customer withdraw(String accountNumber, String withdraw, String accountBalance) throws BankException {
		Customer customer=null;
		if ((validWithdrawAmount(withdraw, accountBalance) == true)  && (validAccountNumber(accountNumber)== true)) {
			customer=dao.withdraw(accountNumber, withdraw);
			logger.info("You have withdrawn: $"+withdraw);
		}else if ((validWithdrawAmount(withdraw, accountBalance) == false)  && (validAccountNumber(accountNumber)== true)){
			logger.info("Withdrawl ammount must be greater than 0 or less than account balance");
		} else if((validWithdrawAmount(withdraw, accountBalance) == true)  && (validAccountNumber(accountNumber)== false)) {
			logger.info(" Account number not valid");
		}
		return customer;
	}
	
	public boolean validDepositAmount(String deposit) {
		boolean b = false;
		if (deposit.matches("[0-9]{1,10}[.]{1}[0-1]{2}")) {
			b=true;
		}
		return b;
	}

	public Customer deposit(String deposit, String accountNumber) throws BankException {
		Customer customer=null;
		if ((validDepositAmount(deposit)==true) && (validAccountNumber(accountNumber)==true)) {
			customer=dao.deposit(deposit, accountNumber);
			logger.info("You have deposited: $"+deposit);
		} else if ((validDepositAmount(deposit)==false) && (validAccountNumber(accountNumber)==true)) {
			logger.info("Deposit ammount must be greater than 0");
		} else if ((validDepositAmount(deposit)==true) && (validAccountNumber(accountNumber)==false)) {
			logger.info("Invalid Account Number");
		}else if ((validDepositAmount(deposit)==false) && (validAccountNumber(accountNumber)==false)) {
			logger.info("Invalid Account Number && Deposit ammount must be greater than 0");
		}
		return customer;
	}


	public Customer moneyTransfer(String fromAccountNumber, String transferAmount, String toAccountNumber) throws BankException {
		Customer customer=null;
		if ((validDepositAmount(transferAmount)==true) && (validAccountNumber(fromAccountNumber)==true) && (validAccountNumber(toAccountNumber)==true)) {
			customer=dao.moneyTransfer(fromAccountNumber, transferAmount, toAccountNumber);
			logger.info("You have sucessfully transfered $"+transferAmount + " to :"+toAccountNumber);
		} else if ((validDepositAmount(transferAmount)==true) && (validAccountNumber(fromAccountNumber)==false) && (validAccountNumber(toAccountNumber)==true)){
			logger.info("Account Number invalid");
		} else if((validDepositAmount(transferAmount)==true) && (validAccountNumber(fromAccountNumber)==true) && (validAccountNumber(toAccountNumber)==false)) {
			logger.info("Account Number invalid");
		}else if((validDepositAmount(transferAmount)==false) && (validAccountNumber(fromAccountNumber)==true) && (validAccountNumber(toAccountNumber)==true)) {
			logger.info("Transfer Amount is invalid");
		}else if((validDepositAmount(transferAmount)==true) && (validAccountNumber(fromAccountNumber)==false) && (validAccountNumber(toAccountNumber)==false)) {
			logger.info("Both Account Numbers are invalid");
		}else if((validDepositAmount(transferAmount)==false) && (validAccountNumber(fromAccountNumber)==false) && (validAccountNumber(toAccountNumber)==true)) {
			logger.info("Transfer Amount is invalid and to Account Number is invalid");
		}else if((validDepositAmount(transferAmount)==false) && (validAccountNumber(fromAccountNumber)==true) && (validAccountNumber(toAccountNumber)==false)) {
			logger.info("Transfer Amount is invalid and from Account Number is invalid");
		}
			
		return customer;
	}

	public Customer acceptTransfer(String fromAccountNumber, String transferAmount, String toAccountNumber) throws BankException {
		Customer customer=null;
		if ((validDepositAmount(transferAmount)==true) && (validAccountNumber(fromAccountNumber)==true) && (validAccountNumber(toAccountNumber)==true)) {
			customer=dao.acceptTransfer(fromAccountNumber, transferAmount, toAccountNumber);
			logger.info("You have accepted a money Transfer of $"+ transferAmount);
		} else if ((validDepositAmount(transferAmount)==true) && (validAccountNumber(fromAccountNumber)==false) && (validAccountNumber(toAccountNumber)==true)){
			logger.info("Account Number invalid");
		} else if((validDepositAmount(transferAmount)==true) && (validAccountNumber(fromAccountNumber)==true) && (validAccountNumber(toAccountNumber)==false)) {
			logger.info("Account Number invalid");
		}else if((validDepositAmount(transferAmount)==false) && (validAccountNumber(fromAccountNumber)==true) && (validAccountNumber(toAccountNumber)==true)) {
			logger.info("Transfer Amount is invalid");
		}else if((validDepositAmount(transferAmount)==true) && (validAccountNumber(fromAccountNumber)==false) && (validAccountNumber(toAccountNumber)==false)) {
			logger.info("Both Account Numbers are invalid");
		}else if((validDepositAmount(transferAmount)==false) && (validAccountNumber(fromAccountNumber)==false) && (validAccountNumber(toAccountNumber)==true)) {
			logger.info("Transfer Amount is invalid and to Account Number is invalid");
		}else if((validDepositAmount(transferAmount)==false) && (validAccountNumber(fromAccountNumber)==true) && (validAccountNumber(toAccountNumber)==false)) {
			logger.info("Transfer Amount is invalid and from Account Number is invalid");
		}
		return customer;
	}

	@Override
	public String deleteCustomer(String accountNumber) throws BankException {

		if (accountNumber == null) {
			logger.info("Customer account does not exist");
		}else {
			accountNumber=dao.deleteCustomer(accountNumber);
		}
		return null;
	}


	@Override
	public Customer loginVerification(String username) throws BankException {
			Customer customer= null;
			if(username !=null) {
				customer=dao.loginVerification(username);
			}else {
				logger.info("Customer Credentials "+ username+ " are not valid");
			}
			return customer;
		}
	@Override
	public Customer updateCustomer(String newPassword, String accountNumber) throws BankException{
		Customer customer=null;
		if(newPassword != null && validAccountNumber(accountNumber)==true) {
		customer=dao.updateCustomer(newPassword, accountNumber);
		}else if(newPassword != null && (validAccountNumber(accountNumber)==false)) {
			logger.info("Invalid account number ");
		}
		return customer;
	}
	}






