package com.bank.dao;

import com.bank.exception.BankException;
import com.bank.model.Customer;
import com.bank.model.Employee;

public interface EmployeeDAO {

	public Employee createEmployee(Employee employee) throws BankException;
	public Customer approveAccount(String approve, String accountNumber) throws BankException;
	public Customer rejectAccount(String reject, String accountNumber) throws BankException;
	public Customer viewCustomerAccount(String accountNumber) throws BankException;
	public Employee updateEmployee(String newAddress) throws BankException;
	public Employee deleteEmployee(String username) throws BankException;
	//Still need a view log of all transactions
	public Employee loginVerification(String username) throws BankException;
}
