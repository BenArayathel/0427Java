package com.bankofben.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Customer;
import com.bankofben.models.Employee;
import com.bankofben.models.Payment;
import com.bankofben.models.Request;
import com.bankofben.models.Transaction;
import com.bankofben.models.Transfer;
import com.bankofben.models.User;

public interface BankOfBenDAOInterface {
	
	/*
	 * This layer does all the actual getting of data. Methods should match the services
	 * Just have CRUD in here
	 */

	
	// CREATE
	
	// Create each type of persistent data
	public Customer createCustomer(User user) throws BusinessException;
	public Employee createEmployee(User user, String designation, Employee supervisor) throws BusinessException;
	public Account createAccount(Account account) throws BusinessException;
	public Payment createPayment(Payment payment) throws BusinessException;
	public Request createRequest(Request request) throws BusinessException;
	public Transfer createTransfer(Transfer transfer) throws BusinessException;
	public Transaction createTransaction(Transaction transaction) throws BusinessException;
	
	
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
	public Employee getEmployeeById(String employeeId) throws BusinessException;
	public Employee getEmployeeByUsername(String username) throws BusinessException;
	public Employee getEmployeeByEmail(String email) throws BusinessException;
	// Get all employees
	public List<Employee> getAllEmployees() throws BusinessException;
	public List<Employee> getAllEmployeesOrderedBy(String columnName) throws BusinessException;
	public List<Employee> getAllEmployeesOrderedBy(int columnIndex) throws BusinessException;
	// Get all employees with column value
	public List<Employee> getEmployeesByColumn(String columnName, String columnValue) throws BusinessException;
	public List<Employee> getEmployeesByColumn(int columnIndex, String columnValue) throws BusinessException;
	
	// Get account by primary or unique key
	public Account getAccountByAccountNumber(Long accountNumber) throws BusinessException;
	//Get all accounts
	public List<Account> getAllAccounts() throws BusinessException;
	// Get all accounts with column value (i.e. username)
	public List<Account> getAllAccountsOrderedBy(String columnName) throws BusinessException;
	public List<Account> getAllAccountsOrderedBy(int columnIndex) throws BusinessException;
	// Get all accounts with customerId
	public List<Account> getAccountsForCustomerId(String customerId) throws BusinessException;
	// Get all accounts by pending status
	public List<Account> getAccountsWithPendingStatus(boolean isPending) throws BusinessException;
	// Low priority: Get all accounts by balance comparison (i.e. all accounts with balance < 1000)
	public List<Account> getAccountsByBalanceComparison(String balanceComparison) throws BusinessException;
	
	// Get payment by primary key
	public Payment getPaymentById(String paymentId) throws BusinessException;
	// Get all payments
	public List<Payment> getAllPayments() throws BusinessException;
	// Get all payments with column value (i.e. Initiator's ID)
	public List<Payment> getAllPaymentsByColumn(String columnName, String columnValue) throws BusinessException;
	public List<Payment> getAllPaymentsByColumn(int columnIndex, String columnValue) throws BusinessException;
	// Get all payments with Initiator's ID
	public List<Payment> getAllPaymentsWithInitId(String initId) throws BusinessException;
	// Get all payments with pending status
	public List<Payment> getAllPaymentsWithPendingStatus(boolean isPending) throws BusinessException;
	
	// Get request by primary key
	public Request getRequestById(String requestId) throws BusinessException;
	// Get all requests
	public List<Request> getAllRequests() throws BusinessException;
	// Get all requests with column value (i.e. Initiator's ID)
	public List<Request> getAllRequestsByColumn(String columnName, String columnValue) throws BusinessException;
	public List<Request> getAllRequestsByColumn(int columnIndex, String columnValue) throws BusinessException;
	// Get all requests with Initiator's ID
	public List<Request> getAllRequestsWithInitId(String initId) throws BusinessException;
	// Get all requests with pending status
	public List<Request> getAllRequestsWithPendingStatus(boolean isPending) throws BusinessException;
	
	// Get all transfers (payments and requests)
	public List<Transfer> getAllTransfers() throws BusinessException;
	// Get all transfers with Initiator's ID
	public List<Transfer> getAllTransfersWithInitId(String initId) throws BusinessException;
	// Get all transfers with pending status
	public List<Transfer> getAllTransfersWithPendingStatus(boolean isPending) throws BusinessException;
	
	// Get all transactions
	public List<Transaction> getAllTransactions() throws BusinessException;
	// Get all transactions with Account Number
	public List<Transaction> getAllTransactionsWithAccountNumber(long accountNumber) throws BusinessException;
	// Get all transactions after date
	public List<Transaction> getAllTransactionsAfterDate(LocalDate date) throws BusinessException;
	public List<Transaction> getAllTransactionsAfterDate(Date date) throws BusinessException;
	
	
	// UPDATE
	
	// Update customer information
	public Customer updateCustomerFirstLame(String firstName, String customerId) throws BusinessException;
	public Customer updateCustomerMiddleName(String middleName, String customerId) throws BusinessException;
	public Customer updateCustomerLastName(String lastName, String customerId) throws BusinessException;
	public Customer updateCustomerMomsMaidenName(String momsMaidenName, String customerId) throws BusinessException;
	public Customer updateCustomerDateOfBirth(Date dob, String customerId) throws BusinessException;
	public Customer updateCustomerSocialSecurityNumber(long ssn, String customerId) throws BusinessException;
	public Customer updateCustomerSocialSecurityNumber(String ssn, String customerId) throws BusinessException;
	public Customer updateCustomerPhoneNumber(long phoneNumber, String customerId) throws BusinessException;
	public Customer updateCustomerPhoneNumber(String phoneNumber, String customerId) throws BusinessException;
	public Customer updateCustomerUsername(String username, String customerId) throws BusinessException;
	public Customer updateCustomerPassword(String password, String customerId) throws BusinessException;
	public Customer updateCustomerApplicationPending(boolean applicationPending, String customerId) throws BusinessException;
	// Update employee information
	public Employee updateEmployeeFirstLame(String firstName, String employeeId) throws BusinessException;
	public Employee updateEmployeeMiddleName(String middleName, String employeeId) throws BusinessException;
	public Employee updateEmployeeLastName(String lastName, String employeeId) throws BusinessException;
	public Employee updateEmployeeMomsMaidenName(String momsMaidenName, String employeeId) throws BusinessException;
	public Employee updateEmployeeDateOfBirth(Date dob, String employeeId) throws BusinessException;
	public Employee updateEmployeeSocialSecurityNumber(long ssn, String employeeId) throws BusinessException;
	public Employee updateEmployeeSocialSecurityNumber(String ssn, String employeeId) throws BusinessException;
	public Employee updateEmployeePhoneNumber(long phoneNumber, String employeeId) throws BusinessException;
	public Employee updateEmployeePhoneNumber(String phoneNumber, String employeeId) throws BusinessException;
	public Employee updateEmployeeUsername(String username, String employeeId) throws BusinessException;
	public Employee updateEmployeePassword(String password, String employeeId) throws BusinessException;
	public Employee updateEmployeeDesignation(String designation, String employeeId) throws BusinessException;
	public Employee updateEmployeeSupervisor(String supervisorId, String employeeId) throws BusinessException;
	// Update account information
	public Account updateAccountBalance(double balance, long accountNumber) throws BusinessException;
	public Account updateAccountCustomerId(int customerId, long accountNumber) throws BusinessException;
	public Account updateAccountPendingStatus(boolean isPending, long accountNumber) throws BusinessException;
	// Update payment information
	public Payment updatePaymentPendingStatus(boolean isPending, String paymentId) throws BusinessException;
	// Update request information
	public Request updateRequestPendingStatus(boolean isPending, String requestId) throws BusinessException;
	//Update transfer information
	public Transfer updateTransferPendingStatus(boolean isPending, String transferId) throws BusinessException;
	
	
	// DELETE
	
	// Delete each type of persistent data
	public void deleteCustomer(String customerId) throws BusinessException;
	public void deleteEmployee(String employeeId) throws BusinessException;
	public void deleteAccount(long accountNumber) throws BusinessException;
	public void deletePayment(String paymentId) throws BusinessException;
	public void deleteRequest(String requestId) throws BusinessException;
	public void deleteTransfer(String transferId) throws BusinessException;

}
