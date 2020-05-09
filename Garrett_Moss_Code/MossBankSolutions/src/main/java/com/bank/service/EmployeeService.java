package com.bank.service;

import com.bank.exception.BankException;
import com.bank.model.Employee;

public interface EmployeeService {
	public Employee creatEmployee(Employee employee) throws BankException;
	public Employee approveAccount(Employee approve) throws BankException;
	public Employee rejectAccount(Employee reject) throws BankException;
	public Employee viewCustomer(String username) throws BankException;
	public Employee viewCustomerAccount(int accountNumber) throws BankException;
	public Employee updatEmployee(String newAddress) throws BankException;
	public Employee deleteEmployee(String username) throws BankException;
	//Still need a view log of all transactions
}
