package com.bank.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.bank.dao.EmployeeDAO;
import com.bank.dao.impl.EmployeeDaoImpl;
import com.bank.exception.BankException;
import com.bank.main.MainDriver;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.model.Transactions;
import com.bank.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
	
	final static Logger logger = Logger.getLogger(MainDriver.class);
	
	private EmployeeDAO dao = new EmployeeDaoImpl();
	public Customer approveAccount(String approve, String accountNumber) throws BankException {
		
		Customer customer=null;
		if(approve != null && validAccountNumber(accountNumber)==true) {
		customer=dao.approveAccount(approve, accountNumber);
		}else if(approve != null && (validAccountNumber(accountNumber)==false)) {
			logger.info("Invalid account number ");
		}
		return customer;
	}


	public Customer rejectAccount(String reject, String accountNumber) throws BankException {
		Customer customer=null;
		if(reject != null && validAccountNumber(accountNumber)==true) {
		customer=dao.rejectAccount(reject, accountNumber);
		}else if(reject != null && (validAccountNumber(accountNumber)==false)) {
			logger.info("Invalid account number ");
		}
		return customer;
	}
	
	
	private boolean validAccountNumber(String accountNumber) {
		boolean b = false;
		if (accountNumber.matches("[0-9]{10}")) {
			b=true;
		}
		return b;
	}
	public Customer viewCustomerAccount(String accountNumber) throws BankException {
		Customer customer=null;
		if(validAccountNumber(accountNumber)==true) {
		customer=dao.viewCustomerAccount(accountNumber);
		}else {
			logger.info("Ivalid account number");
		}
		return customer;
	}

	@Override
	public Employee createEmployee(Employee employee) throws BankException {
		if (employee == null) {
			logger.info("Employee account was not created");
		}else {
			employee=dao.createEmployee(employee);
		}
		return employee;
	}

	@Override
	public Employee updateEmployee(String newPassword, String accountNumber) throws BankException {
		Employee employee=null;
		if(newPassword != null && validAccountNumber(accountNumber)==true) {
		employee=dao.updateEmployee(newPassword, accountNumber);
		}else if(newPassword != null && (validAccountNumber(accountNumber)==false)) {
			logger.info("Invalid account number ");
		}
		return employee;
	}

	@Override
	public String deleteEmployee(String employeeId) throws BankException {
		
		if (employeeId == null) {
			logger.info("Employee account does not exist");
		}else {
			employeeId=dao.deleteEmployee(employeeId);
		}
		return null;
	}


	@Override
	public Employee loginVerification(String username) throws BankException {
		Employee employee = null;
		if(username !=null) {
			employee=dao.loginVerification(username);
		}else {
			logger.info("Employee Credentials "+ username+ " are not valid");
		}
		return employee;
	}

	public boolean validTransactionChoice(String choice) {
		boolean b = false;
		if (choice.matches("Yes")) {
			b=true;
		} else if(choice.matches("yes")) {
			b=true;
		}else if(choice.matches("No")) {
			b=false;
		}else if(choice.matches("no")) {
			b=false;
		}
		return b;
	}

	@Override
	public List<Transactions> viewTransactionLogs(String choice) throws BankException{	
	List<Transactions> accountTransactions=null;
		if (validTransactionChoice(choice)==true) {
			accountTransactions=dao.viewTransactionLogs(choice);
		}else if (validTransactionChoice(choice)==false){
			logger.info("You have chosen not to view Transaction Logs");
		}else {
			logger.info("Not a Valid Input");
		}
		return accountTransactions;
	}
	
}
