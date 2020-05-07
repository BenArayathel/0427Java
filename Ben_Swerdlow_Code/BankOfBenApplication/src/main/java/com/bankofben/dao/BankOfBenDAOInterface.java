package com.bankofben.dao;

import java.util.Date;
import java.util.List;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Customer;
import com.bankofben.models.Employee;

public interface BankOfBenDAOInterface {
	
	/*
	 * This layer does all the actual getting of data. Methods should match the services
	 * Just have CRUD in here
	 */

	
	// CREATE
	
	// Create each type of persistent data
	public Customer createCustomer(Customer customer) throws BusinessException;
	public Employee createEmployee(Employee employee) throws BusinessException;
	public Account createAccount(Account account) throws BusinessException;
	
	
	// READ
	
	// Get customer by primary or unique key
	public Customer getCustomerById(String customerId) throws BusinessException;
	public Customer getCustomerByUsername(String username) throws BusinessException;
	public Customer getCustomerByEmail(String email) throws BusinessException;
	// Get all customers
	public List<Customer> getAllCustomers() throws BusinessException;
	public List<Customer> getAllCustomersOrderedBy(String columnName) throws BusinessException;
	public List<Customer> getAllCustomersOrderedBy(int columnIndex) throws BusinessException;
	// Get all customers with column value
	public List<Customer> getCustomersByColumn(String columnName, String columnValue) throws BusinessException;
	public List<Customer> getCustomersByColumn(int columnIndex, String columnValue) throws BusinessException;
	
	// Get employee by primary or unique key
	public Customer getEmployeeById(String customerId) throws BusinessException;
	public Customer getEmployeeByUsername(String username) throws BusinessException;
	public Customer getEmployeeByEmail(String email) throws BusinessException;
	// Get all employees
	public List<Customer> getAllEmployees() throws BusinessException;
	public List<Customer> getAllEmployeesOrderedBy(String columnName) throws BusinessException;
	public List<Customer> getAllEmployeesOrderedBy(int columnIndex) throws BusinessException;
	// Get all employees with column value
	public List<Customer> getEmployeesByColumn(String columnName, String columnValue) throws BusinessException;
	public List<Customer> getEmployeesByColumn(int columnIndex, String columnValue) throws BusinessException;
	
	// Get account by primary or unique key
	public Account getAccountByAccountNumber(Long accountNumber) throws BusinessException;
	//Get all accounts
	public List<Account> getAllAccounts() throws BusinessException;
	public List<Account> getAllAccountsOrderedBy(String columnName) throws BusinessException;
	public List<Account> getAllAccountsOrderedBy(int columnIndex) throws BusinessException;
	// Get all accounts with column value (i.e. username)
	public List<Account> getAccountsForCustomerId(String customerId) throws BusinessException;
	// Low priority: Get all accounts by balance comparison (i.e. all accounts with balance < 1000)
	public List<Account> getAccountsByBalanceComparison(String balanceComparison) throws BusinessException;
	
	
	// UPDATE
	
	// Update customer information
	public Customer updateCustomerFirstLame(String firstName) throws BusinessException;
	public Customer updateCustomerMiddleName(String middleName) throws BusinessException;
	public Customer updateCustomerLastName(String lastName) throws BusinessException;
	public Customer updateCustomerMomsMaidenName(String momsMaidenName) throws BusinessException;
	public Customer updateCustomerDateOfBirth(Date dob) throws BusinessException;
	public Customer updateCustomerSocialSecurityNumber(long ssn) throws BusinessException;
	public Customer updateCustomerSocialSecurityNumber(String ssn) throws BusinessException;
	public Customer updateCustomerPhoneNumber(long phoneNumber) throws BusinessException;
	public Customer updateCustomerPhoneNumber(String phoneNumber) throws BusinessException;
	public Customer updateCustomerUsername(String username) throws BusinessException;
	public Customer updateCustomerPassword(String password) throws BusinessException;
	// Update employee information
	public Employee updateEmployeeFirstLame(String firstName) throws BusinessException;
	public Employee updateEmployeeMiddleName(String middleName) throws BusinessException;
	public Employee updateEmployeeLastName(String lastName) throws BusinessException;
	public Employee updateEmployeeMomsMaidenName(String momsMaidenName) throws BusinessException;
	public Employee updateEmployeeDateOfBirth(Date dob) throws BusinessException;
	public Employee updateEmployeeSocialSecurityNumber(long ssn) throws BusinessException;
	public Employee updateEmployeeSocialSecurityNumber(String ssn) throws BusinessException;
	public Employee updateEmployeePhoneNumber(long phoneNumber) throws BusinessException;
	public Employee updateEmployeePhoneNumber(String phoneNumber) throws BusinessException;
	public Employee updateEmployeeUsername(String username) throws BusinessException;
	public Employee updateEmployeePassword(String password) throws BusinessException;
	public Employee updateEmployeeDesignation(String designation) throws BusinessException;
	// Update account information
	public Account updateAccountBalance(double balance) throws BusinessException;
	public Account updateAccountCustomerId(int customerId) throws BusinessException;
	
	
	// DELETE
	
	// Delete each type of persistent data
	public void deleteCustomer(String customerId) throws BusinessException;
	public void deleteEmployee(String employeeId) throws BusinessException;
	public void deleteAccount(String accountId) throws BusinessException;

}
