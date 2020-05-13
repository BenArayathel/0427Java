package com.bank.service;

import com.bank.exception.BankException;
import com.bank.model.Customer;
import com.bank.model.Employee;

public interface EmployeeService {
	public Employee createEmployee(Employee employee) throws BankException;
	public Customer approveAccount(String approve, String accountNumber) throws BankException;
	public Customer rejectAccount(String reject, String accountNumber) throws BankException;
	public Customer viewCustomerAccount(String accountNumber) throws BankException;
	public Employee updateEmployee(String newPassword, String accountNumber) throws BankException;
	public String deleteEmployee(String employeeId) throws BankException;
	public Employee viewTransactionLogs(String choice) throws BankException;
	public Employee loginVerification(String username) throws BankException;
}
