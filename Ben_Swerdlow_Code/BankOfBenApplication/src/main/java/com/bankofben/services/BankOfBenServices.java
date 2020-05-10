package com.bankofben.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bankofben.dao.BankOfBenDAO;
import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Customer;
import com.bankofben.models.Employee;
import com.bankofben.models.Payment;
import com.bankofben.models.Request;
import com.bankofben.models.Transaction;
import com.bankofben.models.Transfer;
import com.bankofben.models.User;

public class BankOfBenServices {
	
	private BankOfBenDAO dao = new BankOfBenDAO();

	public User loginUser(String username, String password) throws BusinessException {
		User user = null;
		List<BusinessException> bizExceptions = new ArrayList<>();
		try {
			user = dao.loginCustomer(username, password);
		} catch (BusinessException e) {
			bizExceptions.add(e);
		}
		
		try {
			user = dao.loginEmployee(username, password);
		} catch (BusinessException e) {
			bizExceptions.add(e);
		}
		
		if (user==null) {
			if (bizExceptions.size()==1) {
				throw bizExceptions.get(0);
			} else {
				throw new BusinessException("User does not exist with these credentials. "
						+ "Please register these credentials as a new user.");
			}
		}
		
		return user;
	}
	
	public Customer loginCustomer(String username, String password) throws BusinessException {
		return dao.loginCustomer(username, password);
	}
	
	public Employee loginEmployee(String username, String password) throws BusinessException {
		return dao.loginEmployee(username, password);
	}
	
	public boolean userExists(User user) throws BusinessException {
		boolean exists = false;
		if (user instanceof Customer) {
			exists = customerExists((Customer) user);
		} else if (user instanceof Employee) {
			exists = employeeExists((Employee) user);
		} else
			exists = false;
		return exists;
	}
	
	public boolean usernameExists(String username) throws BusinessException {
		return (dao.customerUsernameExists(username) || dao.customerUsernameExists(username));
	}
	
	public boolean emailExists(String email) throws BusinessException {
		return (dao.customerEmailExists(email) || dao.employeeEmailExists(email));
	}

	public boolean ssnExists(long ssn) throws BusinessException {
		return (dao.customerSsnExists(ssn) || dao.employeeSsnExists(ssn));
	}

	private boolean customerExists(Customer customer) throws BusinessException {
		return customerExists(customer.getUsername());
	}

	public boolean customerExists(String customerUsername) throws BusinessException {
		return dao.customerUsernameExists(customerUsername);
	}

	private boolean employeeExists(Employee employee) throws BusinessException {
		return employeeExists(employee.getUsername());
	}
	
	public boolean employeeExists(String employeeUsername) throws BusinessException {
		return dao.employeeUsernameExists(employeeUsername);
	}

	public Customer applyForAccount(User user) throws BusinessException {
		return dao.createCustomer(user);
	}
	
	public void approveCustomerAccount(String customerId, Double startingBalance, Employee employee) throws BusinessException {
		Account account = new Account(0L, startingBalance, customerId);
		account = dao.createAccount(account);
		dao.updateCustomerApplicationPending_returnNothing(false, customerId);
	}
	
	public void rejectCustomerAccount(String customerId, Employee employee) throws BusinessException {
		dao.deleteCustomer(customerId);
	}
	
	public void approveCustomerAccount(String customerId, Employee employee) throws BusinessException {
		approveCustomerAccount(customerId, 0.0, employee);
	}

	public String getBalances() throws BusinessException {
		List<Account> accounts = dao.getAllAccounts();
		StringBuilder outputBuilder = new StringBuilder();
		// We'll start by organizing the columns
		outputBuilder.append("Account Number\t|\tBalance\t|\tCustomer ID\n");
		// Now we get the content
		for (Account a : accounts) {
			outputBuilder.append(a.getAccountNumber()+"\t|\t"+a.getBalance()+"\t|\t"+a.getCustomerId()+"\n");
		}
		// And... we return the content
		return outputBuilder.toString();
	}

	public String getBalances(Customer customer) throws BusinessException {
		List<Account> accounts = dao.getAccountsForCustomerId(customer.getId());
		StringBuilder outputBuilder = new StringBuilder();
		// We'll start by organizing the columns
		outputBuilder.append("Account Number\t|\tBalance\n");
		// Now we get the content
		for (Account a : accounts) {
			outputBuilder.append(a.getAccountNumber()+"\t|\t"+a.getBalance()+"\n");
		}
		// And... we return the content
		return outputBuilder.toString();
	}

	public String getBalances(String username) throws BusinessException {
		Customer customer = dao.getCustomerByUsername(username);
		return getBalances(customer);
	}

	public Account getAccount(long accountNumber, long routingNumber) throws BusinessException {
		// TODO Auto-generated method stub
		if (routingNumber != Account.getRoutingNumber()) {
			throw new BusinessException("Recipient account and intended recipient account do not match. Please check "
					+ "that your information is correct. If it is, contact a Bank of Ben employee to remedy the issue.");
		}
		return dao.getAccountByAccountNumber(accountNumber);
	}
	
	public List<Account> getAccountsForCustomer(Customer customer) throws BusinessException {
		return dao.getAccountsForCustomerId(customer.getId());
	}

	public List<Customer> getCustomersByApplicationPendingStatus(boolean applicationPending) throws BusinessException {
		return dao.getCustomersByApplicationPendingStatus(applicationPending);
	}

	public Customer getCustomerById(String customerId) throws BusinessException {
		return dao.getCustomerById(customerId);
	}

	public List<Transfer> getTransfers(Customer customer) throws BusinessException {
		return dao.getTransfersInvolvingCustomer(customer);
	}
	
	public List<Transfer> getTransfersPending(Customer customer) throws BusinessException {
		return dao.getTransfersInvolvingCustomerWithPendingStatus(customer, true);
	}
	
	public List<Payment> getPayments(Customer customer) throws BusinessException {
		return dao.getAllPaymentsInvolvingCustomer(customer);
	}
	
	public List<Payment> getPaymentsPending(Customer customer) throws BusinessException {
		return dao.getAllPaymentsInvolvingCustomerWithPendingStatus(customer, true);
	}
	
	public List<Request> getRequests(Customer customer) throws BusinessException {
		return dao.getAllRequestsInvolvingCustomer(customer);
	}
	
	public List<Request> getRequestsPending(Customer customer) throws BusinessException {
		return dao.getAllRequestsInvolvingCustomerWithPendingStatus(customer, true);
	}

	public void postPayement(Customer customer, Account paySourceAccount, Account payDestinationAccount, double amount) throws BusinessException {
		dao.createPayment(new Payment(customer.getId(), true, paySourceAccount.getAccountNumber(), payDestinationAccount.getAccountNumber(), amount));
	}

	public void postRequest(Customer customer, Account reqSourceAccount, Account reqDestinationAccount, double amount) throws BusinessException {
		dao.createRequest(new Request(customer.getId(), true, reqSourceAccount.getAccountNumber(), reqDestinationAccount.getAccountNumber(), amount));
	}

	public void resolvePendingPayment(Payment payment) throws BusinessException {
		if (payment.isPending()) {
			dao.updatePaymentPendingStatus_returnNothing(false, payment.getId());
		} else {
			throw new BusinessException("No need to resolve payment "+payment.getId()+". Payment is not pending.");
		}
	}

	public void resolvePendingRequest(Request request) throws BusinessException {
		if (request.isPending()) {
			dao.updatePaymentPendingStatus_returnNothing(false, request.getId());
		} else {
			throw new BusinessException("No need to resolve request "+request.getId()+". Request is not pending.");
		}
	}

	public void rejectTransfer(Transfer transfer, Customer customer) throws BusinessException {
		if (transfer.isPending()) {
			dao.deleteTransfer(transfer.getId());
			// TODO log that customer rejected transfer!!!!! Also, all the other logging of transactions :)
		} else {
			throw new BusinessException("No need to reject transfer "+transfer.getId()+". Transfer is not pending.");
		}
	}

	public String getTransactions() throws BusinessException {
		List<Transaction> transactions = dao.getAllTransactions();
		Collections.sort(transactions);
		StringBuilder outputBuilder = new StringBuilder();
		// We'll start by organizing the columns
		outputBuilder.append("Transaction ID\t|\tTimestamp\t|\tAccount Number\t|\tInitial Balance\t|\tAmount\t|\tFinal Balance\n");
		// Now we get the content
		for (Transaction t : transactions) {
			outputBuilder.append(t.getTransactionId()+"\t|\t"+t.getTimestamp()+"\t|\t"+t.getAccountNumber()+"\t|\t"+t.getInitialBalance()+"\t|\t"+t.getAmount()+"\t|\t"+t.getFinalBalance()+"\n");
		}
		// And... we return the content
		return outputBuilder.toString();
	}

	public String getTransactions(long accountNumber) throws BusinessException {
		List<Transaction> transactions = dao.getAllTransactionsWithAccountNumber(accountNumber);
		Collections.sort(transactions);
		StringBuilder outputBuilder = new StringBuilder();
		// We'll start by organizing the columns
		outputBuilder.append("Transaction ID\t|\tTimestamp\t|\tAccount Number\t|\tInitial Balance\t|\tAmount\t|\tFinal Balance\n");
		// Now we get the content
		for (Transaction t : transactions) {
			outputBuilder.append(t.getTransactionId()+"\t|\t"+t.getTimestamp()+"\t|\t"+t.getAccountNumber()+"\t|\t"+t.getInitialBalance()+"\t|\t"+t.getAmount()+"\t|\t"+t.getFinalBalance()+"\n");
		}
		// And... we return the content
		return outputBuilder.toString();
	}
	
	public String getTransactions(int numberOfTransactions) throws BusinessException {
		List<Transaction> transactions = dao.getAllTransactions();
		Collections.sort(transactions);
		StringBuilder outputBuilder = new StringBuilder();
		// We'll start by organizing the columns
		outputBuilder.append("Transaction ID\t|\tTimestamp\t|\tAccount Number\t|\tInitial Balance\t|\tAmount\t|\tFinal Balance\n");
		// Now we get the content
		for (int i=0; i<numberOfTransactions; i++) {
			Transaction t = transactions.get(i);
			outputBuilder.append(t.getTransactionId()+"\t|\t"+t.getTimestamp()+"\t|\t"+t.getAccountNumber()+"\t|\t"+t.getInitialBalance()+"\t|\t"+t.getAmount()+"\t|\t"+t.getFinalBalance()+"\n");
		}
		// And... we return the content
		return outputBuilder.toString();
	}

}
