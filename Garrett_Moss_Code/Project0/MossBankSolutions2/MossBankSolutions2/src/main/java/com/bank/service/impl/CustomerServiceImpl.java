package com.bank.service.impl;

import com.bank.dao.CustomerDAO;
import com.bank.dao.impl.CustomerDaoImpl;
import com.bank.exception.BankException;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDAO dao = new CustomerDaoImpl();

	public Customer createCustomerAccount(Customer createAccount) throws BankException {

		if (createAccount == null) {
			throw new BankException("Customer account was not created");
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
		if(validAccountNumber(accountNumber)) {
		customer=dao.viewBalance(accountNumber);
		}else {
			throw new BankException("Account number does not exist");
		}
		return customer;
	}
	
	private boolean validWithdrawAmount(String withdraw, String accountBalance) {
		boolean b = false;
		if (withdraw.matches("[0-9]{1,10}[.]{1}[0-9]{2}") && (Double.parseDouble(withdraw) < Double.parseDouble(accountBalance))) {
			b=true;
		}
		return b;
	}

	public Customer withdraw(String accountNumber, String withdraw, String accountBalance) throws BankException {
		Customer customer=null;
		if (validWithdrawAmount(withdraw, accountBalance)  && validAccountNumber(accountNumber)) {
			customer=dao.withdraw(accountNumber, withdraw);
		}else {
			throw new BankException("Withdrawl ammount must be greater than 0 or less than account balance");
		}
		return customer;
	}
	
	private boolean validDepositAmount(String deposit) {
		boolean b = false;
		if (deposit.matches("[0-9]{1,10}[.]{1}[0-1]{2}")) {
			b=true;
		}
		return b;
	}

	public Customer deposit(String deposit, String accountNumber) throws BankException {
		Customer customer=null;
		if (validDepositAmount(deposit) && validAccountNumber(accountNumber)) {
			customer=dao.deposit(deposit, accountNumber);
		} else {
			throw new BankException("Deposit ammount must be greater than 0");
		}
		return customer;
	}


	public Customer moneyTransfer(String fromAccountNumber, String transferAmount, String toAccountNumber) throws BankException {
		Customer customer=null;
		if (validDepositAmount(transferAmount) && validAccountNumber(fromAccountNumber) && validAccountNumber(toAccountNumber)) {
			customer=dao.moneyTransfer(fromAccountNumber, transferAmount, toAccountNumber);
		} else {
			throw new BankException("Either Account Numbers or Transfer Amount is invalid");
		}
		return customer;
	}

	public Customer acceptTransfer(String fromAccountNumber, String transferAmount, String toAccountNumber) throws BankException {
		Customer customer=null;
		if (validDepositAmount(transferAmount) && validAccountNumber(fromAccountNumber) && validAccountNumber(toAccountNumber)) {
			customer=dao.acceptTransfer(fromAccountNumber, transferAmount, toAccountNumber);
		} else{
			throw new BankException("You have chosen not to accept this transfer");
		}
		return customer;
	}

	@Override
	public String deleteCustomer(String accountNumber) throws BankException {

		if (accountNumber == null) {
			throw new BankException("Customer account was not deleted");
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
				throw new BankException("Customer Credentials "+ username+ " are not valid");
			}
			return customer;
		}

	}






