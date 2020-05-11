package com.bank.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.bank.dao.EmployeeDAO;
import com.bank.dao.impl.EmployeeDaoImpl;
import com.bank.exception.BankException;
import com.bank.main.MainDriver;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.service.EmployeeService;

import oracle.net.aso.e;

public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDAO dao = new EmployeeDaoImpl();
	public Customer approveAccount(String approve, String accountNumber) throws BankException {
		
		Customer customer=null;
		if(approve != null) {
		customer=dao.approveAccount(approve, accountNumber);
		}else {
			throw new BankException("Account has been approved already ");
		}
		return customer;
	}


	public Customer rejectAccount(String reject, String accountNumber) throws BankException {
		Customer customer=null;
		if(reject != null) {
		customer=dao.approveAccount(reject, accountNumber);
		}else {
			throw new BankException("Account has been rejected already ");
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
		if(validAccountNumber(accountNumber)) {
		customer=dao.viewCustomerAccount(accountNumber);
		}else {
			throw new BankException("Account number does not exist");
		}
		return customer;
	}

	@Override
	public Employee createEmployee(Employee employee) throws BankException {
		if (employee == null) {
			throw new BankException("Employee account was not created");
		}else {
			employee=dao.createEmployee(employee);
		}
		return employee;
	}

	@Override
	public Employee updateEmployee(String newAddress) throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee deleteEmployee(String username) throws BankException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Employee loginVerification(String username, String password) throws BankException {
		Employee employee = null;
		if(username !=null && password !=null) {
			employee=dao.loginVerification(username, password);
		}else {
			throw new BankException("Employee Credentials "+ username+ " "+password+ " are not valid");
		}
		return employee;
	}


	@Override
	public Employee viewTransactionLogs(String answer) throws BankException {
//		if (answer == ) {
			final Logger log = Logger.getLogger(EmployeeServiceImpl.class);
			try {
				FileInputStream readLog= new FileInputStream("G-mossDocumentsJava_Codemy_git_repos0427JavaGarrett_Moss_CodeProject0MossBankSolutions2log4j-application.log");
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(readLog));
				String readLine;
				while ((readLine = bufferedReader.readLine()) != null) {
				
				log.info(readLine);
				}
				readLog.close();
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
//		}else {
//				throw new BankException("You have chosen not to view Transaction Logs");
//			}
		return null;
	}

	
}
