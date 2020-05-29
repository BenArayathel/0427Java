package com.bankofben.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

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
	final static Logger loggy = Logger.getLogger(BankOfBenServices.class);
	private BusinessException b = null;

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
				loggy.error(bizExceptions.get(0));
				throw bizExceptions.get(0);
			} else {
				b = new BusinessException("User does not exist with these credentials. "
						+ "Please check your information and try again, or register these credentials as a new user.");
				loggy.error(b);
				throw b;
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
		return (dao.customerUsernameExists(username) || dao.employeeUsernameExists(username));
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

	public Customer applyForAccount_newUser(User user, double startingBalance) throws BusinessException {
		Customer c = dao.createCustomer(user);
		Account a = new Account(1111111111L, startingBalance, c.getId(), true);
		a = dao.createAccount(a);
		return c;
	}

	public Account applyForAccount_newUser_returnAccount(User user, double startingBalance) throws BusinessException {
		Customer c = dao.createCustomer(user);
		Account a = new Account(0, startingBalance, c.getId(), true);
		a = dao.createAccount(a);
		return a;
	}

	public Customer applyForAccount(Customer customer, double startingBalance) throws BusinessException {
		Account a = new Account(1111111111L, startingBalance, customer.getId(), true);
		a = dao.createAccount(a);
		return customer;
	}

	public Account applyForAccount_returnAccount(Customer customer, double startingBalance) throws BusinessException {
		Account a = new Account(1111111111L, startingBalance, customer.getId(), true);
		a = dao.createAccount(a);
		return a;
	}
	
	public void approveCustomerAccount(String customerId, long accountNumber, Double startingBalance, Employee employee) throws BusinessException {
		dao.updateCustomerApplicationPending_returnNothing(false, customerId);
		dao.updateAccountPendingStatus_returnNothing(false, accountNumber);
	}
	
	public void rejectCustomerAccount(String customerId, long accountNumber, Employee employee) throws BusinessException {
		dao.deleteCustomer(customerId);
		dao.deleteAccount(accountNumber);
	}
	
	public void approveCustomerAccount(String customerId, long accountNumber, Employee employee) throws BusinessException {
		approveCustomerAccount(customerId, accountNumber, 0.0, employee);
	}

	public String getBalances() throws BusinessException {
		List<Account> accounts = dao.getAllAccounts();
		StringBuilder outputBuilder = new StringBuilder();
		// We'll start by organizing the columns
		outputBuilder.append("Account Number\t|\tBalance\t|\t\tCustomer ID\t|\tStatus\t|\n");
		// Now we get the content
		for (Account a : accounts) {
			outputBuilder.append(a.getAccountNumber()+"\t|\t"+a.getBalance()+"\t|\t"+a.getCustomerId()+"\t|\t");
			if (a.isPending()) {
				outputBuilder.append("Pending\t|\n");
			} else {
				outputBuilder.append("Active\t|\n");
			}
		}
		// And... we return the content
		return outputBuilder.toString();
	}

	public String getBalances(Customer customer) throws BusinessException {
		List<Account> accounts = dao.getAccountsForCustomerId(customer.getId());
		StringBuilder outputBuilder = new StringBuilder();
		// We'll start by organizing the columns
		outputBuilder.append("Account Number\t|\tBalance\t|\tStatus\t|\n");
		// Now we get the content
		for (Account a : accounts) {
			outputBuilder.append(a.getAccountNumber()+"\t|\t"+a.getBalance()+"\t|\t");
			if (a.isPending()) {
				outputBuilder.append("Pending\t|\n");
			} else {
				outputBuilder.append("Active\t|\n");
			}
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
			b = new BusinessException("Recipient account and intended recipient account do not match. Please check "
					+ "that your information is correct. If it is, contact a Bank of Ben employee to remedy the issue.");
			loggy.error(b);
			throw b;
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
	
	public Payment getPaymentById(String paymentId) throws BusinessException {
		return dao.getPaymentById(paymentId);
	}
	
	public Request getRequestById(String requestId) throws BusinessException {
		return dao.getRequestById(requestId);
	}
	
	public List<Payment> getPayments(Customer customer) throws BusinessException {
		return dao.getAllPaymentsInvolvingCustomer(customer);
	}
	
	public List<Payment> getPaymentsPending(Customer customer) throws BusinessException {
		return dao.getAllPaymentsInvolvingCustomerWithPendingStatus(customer, true);
	}

	public List<Payment> getPaymentsPendingToCustomer(Customer customer) throws BusinessException {
		List<Account> accounts = getAccountsForCustomer(customer);
		List<Payment> toPayments = new ArrayList<>();
		for (Account account : accounts) {
			List<Payment> paymentsToAccount = dao.getAllPaymentsWithReceivingAccountNumberAndPendingStatus(account.getAccountNumber(), true);
			toPayments = Stream.concat(toPayments.stream(), paymentsToAccount.stream())
                    .collect(Collectors.toList());
		}
		return toPayments;
	}

	public List<Payment> getPaymentsPendingToCustomer(Customer customer, boolean selfPayments) throws BusinessException {
		List<Account> accounts = getAccountsForCustomer(customer);
		List<Long> accountNumbers = new ArrayList<>();
		for (Account account : accounts) {
			accountNumbers.add(account.getAccountNumber());
		}
		List<Payment> toPayments = new ArrayList<>();
		System.out.println("Getting to payments");
		for (long accountNumber : accountNumbers) {
			List<Payment> paymentsToAccount = dao.getAllPaymentsWithReceivingAccountNumberAndPendingStatus(accountNumber, true);
			for (Payment payment : paymentsToAccount) {
				if ((accountNumbers.contains(payment.getPayingAccountNumber())
						&& accountNumbers.contains(payment.getReceivingAccountNumber()) == selfPayments)) {
					toPayments.add(payment);
				}
			}
		}
		System.out.println("Got to payments");
		return toPayments;
	}
	
	public List<Payment> getPaymentsPendingFromCustomer(Customer customer) throws BusinessException {
		List<Account> accounts = getAccountsForCustomer(customer);
		List<Payment> fromPayments = new ArrayList<>();
		for (Account account : accounts) {
			List<Payment> paymentsToAccount = dao.getAllPaymentsWithPayingAccountNumberAndPendingStatus(account.getAccountNumber(), true);
			fromPayments = Stream.concat(fromPayments.stream(), paymentsToAccount.stream())
                    .collect(Collectors.toList());
		}
		return fromPayments;
	}

	public List<Payment> getPaymentsPendingFromCustomer(Customer customer, boolean selfPayments) throws BusinessException {
		List<Account> accounts = getAccountsForCustomer(customer);
		List<Long> accountNumbers = new ArrayList<>();
		for (Account account : accounts) {
			accountNumbers.add(account.getAccountNumber());
		}
		List<Payment> fromPayments = new ArrayList<>();
		System.out.println("Getting from payments");
		for (long accountNumber : accountNumbers) {
			List<Payment> paymentsFromAccount = dao.getAllPaymentsWithPayingAccountNumberAndPendingStatus(accountNumber, true);
			for (Payment payment : paymentsFromAccount) {
				if ((accountNumbers.contains(payment.getPayingAccountNumber())
						&& accountNumbers.contains(payment.getReceivingAccountNumber()) == selfPayments)) {
					fromPayments.add(payment);
				}
			}
		}
		System.out.println("Got from payments");
		return fromPayments;
	}
	
	public List<Request> getRequests(Customer customer) throws BusinessException {
		return dao.getAllRequestsInvolvingCustomer(customer);
	}
	
	public List<Request> getRequestsPending(Customer customer) throws BusinessException {
		return dao.getAllRequestsInvolvingCustomerWithPendingStatus(customer, true);
	}

	public List<Request> getRequestsPendingToCustomer(Customer customer) throws BusinessException {
		List<Account> accounts = getAccountsForCustomer(customer);
		List<Request> toRequests = new ArrayList<>();
		for (Account account : accounts) {
			List<Request> requestsToAccount = dao.getAllRequestsWithSoughtAccountNumber(account.getAccountNumber());
			toRequests = Stream.concat(toRequests.stream(), requestsToAccount.stream())
                    .collect(Collectors.toList());
		}
		return toRequests;
	}

	public List<Request> getRequestsPendingToCustomer(Customer customer, boolean selfPayments) throws BusinessException {
		List<Account> accounts = getAccountsForCustomer(customer);
		List<Long> accountNumbers = new ArrayList<>();
		for (Account account : accounts) {
			accountNumbers.add(account.getAccountNumber());
		}
		List<Request> toRequests = new ArrayList<>();
		System.out.println("Getting to requests");
		for (long accountNumber : accountNumbers) {
			List<Request> requestsToAccount = dao.getAllRequestsWithSoughtAccountNumberAndPendingStatus(accountNumber, true);
			for (Request request : requestsToAccount) {
				if ((accountNumbers.contains(request.getRequestorAccountNumber())
						&& accountNumbers.contains(request.getSoughtAccountNumber()) == selfPayments)) {
					toRequests.add(request);
				}
			}
		}
		System.out.println("Got to requests");
		return toRequests;
	}
	
	public List<Request> getRequestsPendingFromCustomer(Customer customer) throws BusinessException {
		List<Account> accounts = getAccountsForCustomer(customer);
		List<Request> fromRequests = new ArrayList<>();
		for (Account account : accounts) {
			List<Request> requestsToAccount = dao.getAllRequestsWithRequestorAccountNumber(account.getAccountNumber());
			fromRequests = Stream.concat(fromRequests.stream(), requestsToAccount.stream())
                    .collect(Collectors.toList());
		}
		return fromRequests;
	}

	public List<Request> getRequestsPendingFromCustomer(Customer customer, boolean selfPayments) throws BusinessException {
		List<Account> accounts = getAccountsForCustomer(customer);
		List<Long> accountNumbers = new ArrayList<>();
		for (Account account : accounts) {
			accountNumbers.add(account.getAccountNumber());
		}
		List<Request> fromRequests = new ArrayList<>();
		System.out.println("Getting from requests");
		for (long accountNumber : accountNumbers) {
			List<Request> requestsToAccount = dao.getAllRequestsWithRequestorAccountNumberAndPendingStatus(accountNumber, true);
			for (Request request : requestsToAccount) {
				if ((accountNumbers.contains(request.getRequestorAccountNumber())
						&& accountNumbers.contains(request.getSoughtAccountNumber()) == selfPayments)) {
					fromRequests.add(request);
				}
			}
		}
		System.out.println("Got from requests");
		return fromRequests;
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
			b = new BusinessException("No need to resolve payment "+payment.getId()+". Payment is not pending.");
			loggy.error(b);
			throw b;
		}
	}
	
	public void makePayment(Payment payment) throws BusinessException {
		Account payingAccount = getAccount(payment.getPayingAccountNumber(), Account.getRoutingNumber());
		if (payingAccount.isPending()) {
			BusinessException b = new BusinessException("Cannot operate on an account that is pending.");
			loggy.error(b);
			throw b;
		}
		Account receivingAccount = getAccount(payment.getReceivingAccountNumber(), Account.getRoutingNumber());
		if (receivingAccount.isPending()) {
			BusinessException b = new BusinessException("Cannot operate on an account that is pending.");
			loggy.error(b);
			throw b;
		}
		
		double amount = payment.getAmount();
		double payingAccountOrigBal = payingAccount.getBalance();
		double receivingAccountOrigBal = receivingAccount.getBalance();
		double payingAccountNewBal = ((double) Math.round((payingAccount.getBalance()-amount)*100))/100.0;
		double receivingAccountNewBal = ((double) Math.round((receivingAccount.getBalance()+amount)*100))/100.0;
		// Check to ensure this will work
		// if can't work, won't be able to execute the next four lines
		payingAccount.setBalance(payingAccountNewBal, payment);
		receivingAccount.setBalance(receivingAccountNewBal, payment);
		Transaction transaction = new Transaction(payingAccount.getAccountNumber(), payingAccountOrigBal, -amount, receivingAccount.getAccountNumber());
		Transaction otherTransaction = new Transaction(receivingAccount.getAccountNumber(), receivingAccountOrigBal, amount, payingAccount.getAccountNumber());
		// Update the db accordingly
		dao.updateAccountBalance(payingAccountNewBal, payingAccount.getAccountNumber());
		dao.updateAccountBalance(receivingAccountNewBal, receivingAccount.getAccountNumber());
		dao.createTransaction(transaction);
		dao.createTransaction(otherTransaction);
		resolvePendingPayment(payment);
	}
	
	public void makeRequest(Request request) throws BusinessException {
		Account requestorAccount = getAccount(request.getRequestorAccountNumber(), Account.getRoutingNumber());
		if (requestorAccount.isPending()) {
			BusinessException b = new BusinessException("Cannot operate on an account that is pending.");
			loggy.error(b);
			throw b;
		}
		Account soughtAccount = getAccount(request.getSoughtAccountNumber(), Account.getRoutingNumber());
		if (soughtAccount.isPending()) {
			BusinessException b = new BusinessException("Cannot operate on an account that is pending.");
			loggy.error(b);
			throw b;
		}
		double amount = request.getAmount();
		double requestorAccountOrigBal = requestorAccount.getBalance();
		double soughtAccountOrigBal = soughtAccount.getBalance();
		double requestorAccountNewBal = ((double) Math.round((requestorAccount.getBalance()+amount)*100))/100.0;
		double soughtAccountNewBal = ((double) Math.round((soughtAccount.getBalance()-amount)*100))/100.0;
		// Check to ensure this will work
		// if can't work, won't be able to execute the next four lines
		requestorAccount.setBalance(requestorAccountNewBal, request);
		soughtAccount.setBalance(soughtAccountNewBal, request);
		Transaction transaction = new Transaction(requestorAccount.getAccountNumber(), requestorAccountOrigBal, amount, soughtAccount.getAccountNumber());
		Transaction otherTransaction = new Transaction(soughtAccount.getAccountNumber(), soughtAccountOrigBal, -amount, requestorAccount.getAccountNumber());
		// Update the db accordingly
		dao.updateAccountBalance(requestorAccountNewBal, requestorAccount.getAccountNumber());
		dao.updateAccountBalance(soughtAccountNewBal, soughtAccount.getAccountNumber());
		dao.createTransaction(transaction);
		dao.createTransaction(otherTransaction);
		resolvePendingRequest(request);
	}

	public void resolvePendingRequest(Request request) throws BusinessException {
		if (request.isPending()) {
			dao.updatePaymentPendingStatus_returnNothing(false, request.getId());
		} else {
			b = new BusinessException("No need to resolve request "+request.getId()+". Request is not pending.");
			loggy.error(b);
			throw b;
		}
	}

	public void haltTransfer(Transfer transfer) throws BusinessException {
		if (transfer.isPending()) {
			dao.deleteTransfer(transfer.getId());
		} else {
			b = new BusinessException("No need to halt transfer "+transfer.getId()+". Transfer is not pending.");
			loggy.error(b);
			throw b;
		}
	}

	public String getTransactions() throws BusinessException {
		List<Transaction> transactions = dao.getAllTransactions();
		Collections.sort(transactions);
		StringBuilder outputBuilder = new StringBuilder();
		// We'll start by organizing the columns
		outputBuilder.append("Transaction ID\t|\t\tTimestamp\t\t|\tAccount Number\t|\tInitial Balance\t|\tAmount\t|\tFinal Balance\t|\tOther Account Number\t|\n");
		// Now we get the content
		for (Transaction t : transactions) {
			outputBuilder.append(t.getTransactionId()+"\t|\t"+t.getTimestamp()+"\t|\t"+t.getAccountNumber()+"\t|\t"+t.getInitialBalance()+"\t|\t"+t.getAmount()+"\t|\t"+t.getFinalBalance()+"\t|\t"+t.getOtherAccountNumber()+"\t|\n");
		}
		// And... we return the content
		return outputBuilder.toString();
	}

	public String getTransactions(long accountNumber) throws BusinessException {
		List<Transaction> transactions = dao.getAllTransactionsWithAccountNumber(accountNumber);
		Collections.sort(transactions);
		StringBuilder outputBuilder = new StringBuilder();
		// We'll start by organizing the columns
		outputBuilder.append("Transaction ID\t|\tTimestamp\t|\tAccount Number\t|\tInitial Balance\t|\tAmount\t|\tFinal Balance\t|\tOther Account Number\t|\n");
		// Now we get the content
		for (Transaction t : transactions) {
			outputBuilder.append(t.getTransactionId()+"\t|\t"+t.getTimestamp()+"\t|\t"+t.getAccountNumber()+"\t|\t"+t.getInitialBalance()+"\t|\t"+t.getAmount()+"\t|\t"+t.getFinalBalance()+"\t|\t"+t.getOtherAccountNumber()+"\t|\n");
		}
		// And... we return the content
		return outputBuilder.toString();
	}
	
	public String getTransactionsUpTo(int numberOfTransactions) throws BusinessException {
		List<Transaction> transactions = dao.getAllTransactionsUpTo(numberOfTransactions);
		Collections.sort(transactions);
		StringBuilder outputBuilder = new StringBuilder();
		// We'll start by organizing the columns
		outputBuilder.append("Transaction ID\t|\tTimestamp\t|\tAccount Number\t|\tInitial Balance\t|\tAmount\t|\tFinal Balance\t|\tOther Account Number\t|\n");
		// Now we get the content
		Transaction t = null;
		for (int i=0; i<Math.min(numberOfTransactions, transactions.size()); i++) {
			t = transactions.get(i);
			outputBuilder.append(t.getTransactionId()+"\t|\t"+t.getTimestamp()+"\t|\t"+t.getAccountNumber()+"\t|\t"+t.getInitialBalance()+"\t|\t"+t.getAmount()+"\t|\t"+t.getFinalBalance()+"\t|\t"+t.getOtherAccountNumber()+"\t|\n");
		}
		// And... we return the content
		return outputBuilder.toString();
	}
	
	public List<Account> getAccountsWithPendingStatus(boolean isPending) throws BusinessException{
		return dao.getAccountsWithPendingStatus(isPending);
	}

	public void approveNewCustomerAccountApplication(Account a) throws BusinessException {
		// To approve the new customer and their account, all we have to do is update the customer's \"Application Pending\" entry to false
		//		and to update the account's \"Pending\" entry to false;
		dao.updateCustomerApplicationPending_returnNothing(false, a.getCustomerId());
		dao.updateAccountPendingStatus_returnNothing(false, a.getAccountNumber());
	}

	public void rejectNewCustomerAccountApplication(Account a) throws BusinessException {
		// TODO Auto-generated method stub
		// To reject the customer account application, delete customer entry and account entry
		// Both will be done on delete customer cascade
		dao.fakeCascadeDeleteCustomerID_Account(a.getCustomerId());
	}

	public void approveExistingCustomerAccountApplication(Account a) throws BusinessException {
		// To approve an existing customer, just change account's \"Pending\" entry to false;
		dao.updateAccountPendingStatus_returnNothing(false, a.getAccountNumber());
	}

	public void rejectExistingCustomerAccountApplication(Account a) throws BusinessException {
		// TODO Auto-generated method stub
		// This delete account will not cascade and keep the customer data
		dao.deleteExistingCustomerAccount(a.getAccountNumber());
	}

	public Account updateAccountBalance(Account account, double amount, Account otherAccount) throws BusinessException {
		System.out.println(((double) Math.round((account.getBalance()+amount)*100))/100.0);
		Account a = dao.updateAccountBalance(((double) Math.round((account.getBalance()+amount)*100))/100.0, account.getAccountNumber());
		Transaction transaction = new Transaction(account.getAccountNumber(), account.getBalance(), amount, otherAccount.getAccountNumber());
		dao.createTransaction(transaction);
		return a;
	}
	
	public boolean doesCustomerOwnAccountNumber(String customerId, long accountNumber) throws BusinessException {
		return dao.doesCustomerOwnAccountNumber(customerId, accountNumber);
	}

}