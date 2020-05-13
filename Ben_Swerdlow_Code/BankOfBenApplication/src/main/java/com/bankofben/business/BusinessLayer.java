package com.bankofben.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

import com.bankofben.exceptions.BusinessException;
import com.bankofben.models.Account;
import com.bankofben.models.Customer;
import com.bankofben.models.Employee;
import com.bankofben.models.Payment;
import com.bankofben.models.Request;
import com.bankofben.models.Transfer;
import com.bankofben.models.User;
import com.bankofben.presentation.ValidationTools;
import com.bankofben.services.BankOfBenServices;

public class BusinessLayer {
	
	BankOfBenServices dbs = new BankOfBenServices();
	final static Logger loggy = Logger.getLogger(BusinessLayer.class);

	public User loginUser(String username, String password) throws BusinessException {
		return dbs.loginUser(username, password);
	}

	public boolean userExists(String username) throws BusinessException {
		// BusinessLayer passes database call to DatabaseLayer
		return dbs.usernameExists(username);
	}
	
	public boolean emailExists(String email) throws BusinessException {
		// BusinessLayer passes database call to DatabaseLayer
		return dbs.emailExists(email);
	}

	public boolean userExists(long ssn) throws BusinessException {
		return dbs.ssnExists(ssn);
	}

	public Customer applyForAccount_newUser(User user, double startingBalance) throws BusinessException {
		return dbs.applyForAccount_newUser(user, startingBalance);
	}
	
	public Account applyForAccount_newUser_returnAccount(User user, double startingBalance) throws BusinessException {
		return dbs.applyForAccount_newUser_returnAccount(user, startingBalance);
	}

	public void applyForAccount_newUser_returnNothing(User user, double startingBalance) throws BusinessException{
		dbs.applyForAccount_newUser(user, startingBalance);
	}
	
	public Customer applyForAccount(Customer customer, double startingBalance) throws BusinessException {
		return dbs.applyForAccount(customer, startingBalance);
	}
	
	public Account applyForAccount_returnAccount(Customer customer, double startingBalance) throws BusinessException {
		return dbs.applyForAccount_returnAccount(customer, startingBalance);
	}
	
	public void applyForAccount_returnNothing(Customer customer, double startingBalance) throws BusinessException {
		dbs.applyForAccount(customer, startingBalance);
	}
	
	public String viewBalances() throws BusinessException {
		return dbs.getBalances();
	}

	public String viewBalances(String username) throws BusinessException {
		return dbs.getBalances(username);
	}

	public String viewBalances(Customer customer) throws BusinessException {
		return dbs.getBalances(customer);
	}
	
	public long validateAccountNumber(String accountNumberString) throws BusinessException {
		long accountNumber;
		if (accountNumberString.matches("[0-9]{10}")) {
			try {
				accountNumber = Long.parseLong(accountNumberString);
			} catch (NumberFormatException e){
				BusinessException b =  new BusinessException("The entry "+accountNumberString+" is not a valid "
						+ "account number. Account numbers must be positive 10-digit numbers. "
						+ "Please try again.");
				loggy.error(b);
				throw b;
			}
		} else {
			BusinessException b = new BusinessException("The entry "+accountNumberString+" is not a valid "
					+ "account number. Account numbers must be positive 10-digit numbers. "
					+ "Please try again.");
			loggy.error(b);
			throw b;
		}
		return accountNumber;
	}
	
	public long validateAccountNumber(long accountNumber) throws BusinessException {
		return validateAccountNumber(Long.toString(accountNumber));
	}

	public long validateCustomerAccountNumber(String sourceAccountString, String sourceRoutingString, Customer customer)
			throws BusinessException {
		System.out.println("I'm in validateCustomerAccountNumber in the BL");
		long sourceAccountNumber = validateAccountNumber(sourceAccountString);
		long sourceRoutingNumber = validateRoutingNumber(sourceRoutingString);
		List<Account> customerAccounts = dbs.getAccountsForCustomer(customer);
		for (Account a : customerAccounts) {
			System.out.println(a);
		}
		System.out.println();
		Account sourceAccount = dbs.getAccount(sourceAccountNumber, sourceRoutingNumber);
		System.out.println(sourceAccount);
		if (customerAccounts.contains(sourceAccount)) {
			return sourceAccount.getAccountNumber();
		} else {
			BusinessException b = new BusinessException("The account number "+sourceAccountString+" and routing number "+sourceRoutingString
					+" do not correspond to an account owned by "+customer.getUsername());
			loggy.error(b);
			throw b;
		}
	}
	
	public long validateCustomerAccountNumber(long sourceAccountNumber, String sourceRoutingString, Customer customer)
			throws BusinessException {
		return validateCustomerAccountNumber(Long.toString(sourceAccountNumber), sourceRoutingString, customer);
	}
	
	public long validateCustomerAccountNumber(String sourceAccountString, long sourceRoutingNumber, Customer customer)
			throws BusinessException {
		return validateCustomerAccountNumber(sourceAccountString, Long.toString(sourceRoutingNumber), customer);
	}
	
	public long validateCustomerAccountNumber(long sourceAccountNumber, long sourceRoutingNumber, Customer customer)
			throws BusinessException {
		return validateCustomerAccountNumber(Long.toString(sourceAccountNumber), Long.toString(sourceRoutingNumber), customer);
	}
	
	public Account validatCustomereAccount(Account account, Customer customer) throws BusinessException {
		List<Account> customerAccounts = dbs.getAccountsForCustomer(customer);
		if (customerAccounts.contains(account)) {
			return account;
		} else {
			BusinessException b = new BusinessException("The account number "+account.getAccountNumber()+" and routing number "
					+Account.getRoutingNumber()+" do not correspond to an account owned by "+customer.getUsername());
			loggy.error(b);
			throw b;
		}
	}
	
	public Account validateCustomerAccount(long sourceAccountNumber, long sourceRoutingNumber, Customer customer)
			throws BusinessException {
		Account account = dbs.getAccount(sourceAccountNumber, sourceRoutingNumber);
		return validatCustomereAccount(account, customer);
	}
	
	public double validateMonetaryAmount(String amount) throws BusinessException {
		double amt;
		if (ValidationTools.isValidMonetaryAmount(amount)) {
			try {
				amt = Double.parseDouble(amount);
			} catch (NumberFormatException e) {
				BusinessException b = new BusinessException("The amount "+amount+" is not a valid monetary amount.");
				loggy.error(b);
				throw b;
			}
		} else {
			BusinessException b = new BusinessException("The amount "+amount+" is not a valid monetary amount.");
			loggy.error(b);
			throw b;
		}
		return amt;
	}
	
	public double validateMonetaryAmount(double amount) throws BusinessException {
		if (ValidationTools.isValidMonetaryAmount(amount)) {
			return amount;
		} else {
			BusinessException b = new BusinessException("The amount "+amount+" is not a valid monetary amount.");
			loggy.error(b);
			throw b;
		}
	}
	
	public long validateRoutingNumber(String routingNumberString) throws BusinessException {
		long routingNumber;
		if (routingNumberString.matches("[0-9]{9}")) {
			try {
				routingNumber = Long.parseLong(routingNumberString);
			} catch (NumberFormatException e){
				BusinessException b = new BusinessException("The entry "+routingNumberString+" is not a valid "
						+ "account number. Account numbers must be positive 10-digit numbers. "
						+ "Please try again.");
				loggy.error(b);
				throw b;
			}
			if (routingNumber!=Account.getRoutingNumber()) {
				BusinessException b = new BusinessException("Invalid routing number. Please try again.");
				loggy.error(b);
				throw b;
			}
		} else {
			BusinessException b = new BusinessException("The entry "+routingNumberString+" is not a valid "
					+ "account number. Account numbers must be positive 10-digit numbers. "
					+ "Please try again.");
			loggy.error(b);
			throw b;
		}
		return routingNumber;
	}
	
	public long isValidRoutingNumber(long routingNumber) throws BusinessException {
		return validateAccountNumber(Long.toString(routingNumber));
	}
	
	public HashMap<Customer, List<Account>> makeNewCustomerPendingAccountsMap() throws BusinessException {
//		List<Customer> customers = dbs.getCustomersByApplicationPendingStatus(true);
		List<Account> accounts = dbs.getAccountsWithPendingStatus(true);
		if (accounts.size()==0) {
			BusinessException b = new BusinessException("There are no pending applications for new customers at this time.");
			loggy.error(b);
			throw b;
		}
		HashMap<Customer, List<Account>> accountsMap = new HashMap<>();
		Customer customer = null;
		for (Account a : accounts) {
			customer = dbs.getCustomerById(a.getCustomerId());
			if (customer.isApplicationPending()) {
				if (!accountsMap.containsKey(customer)) {
					accountsMap.put(customer, new ArrayList<>());
				}
				accountsMap.get(customer).add(a);
			}
		}
		
		if (accountsMap.isEmpty()) {
			BusinessException b = new BusinessException("There are no pending applications for new customers at this time.");
			loggy.error(b);
			throw b;
		}
		
		return accountsMap;
	}
	
	public HashMap<Customer, List<Account>> makePendingAccountsMap() throws BusinessException {
		List<Account> accounts = dbs.getAccountsWithPendingStatus(true);
		if (accounts.size()==0) {
			BusinessException b = new BusinessException("There are no pending applications at this time.");
			loggy.error(b);
			throw b;
		}
		HashMap<Customer, List<Account>> accountsMap = new HashMap<>();
		Customer customer = null;
		for (Account a : accounts) {
			customer = dbs.getCustomerById(a.getCustomerId());
//			System.out.println(customer);
//			System.out.println(a);
			if (!accountsMap.containsKey(customer)) {
				accountsMap.put(customer, new ArrayList<>());
			}
			accountsMap.get(customer).add(a);
		}
		if (accountsMap.isEmpty()) {
			BusinessException b = new BusinessException("There are no pending applications for new customers at this time.");
			loggy.error(b);
			throw b;
		}
		return accountsMap;
	}
	
	public Customer getCustomerById(String customerId) throws BusinessException {
		return dbs.getCustomerById(customerId);
	}

//	public String viewPendingAccountApplications() throws BusinessException {
//		List<Account> accounts = dbs.getAccountsWithPendingStatus(true);
//		HashMap<String, List<Account>> accountsMap = new HashMap<>();
//		for (Account a : accounts) {
//			if (accountsMap.containsKey(a.getCustomerId())) {
//				accountsMap.put(a.getCustomerId(), new ArrayList<>());
//			}
//			accountsMap.get(a.getCustomerId()).add(a);
//		}
//		StringBuilder outputBuilder = new StringBuilder();
////		// We'll start by organizing the columns
////		outputBuilder.append("Last Name\t|\tFirst Name\t|\tDate of Birth\t|\t"
////				+ "SSN\t|\tEmail\t|\tPhone #\t|\tUsername\t|\tApplication Pending");
//		// Now we get the content
//		Customer c = null;
//		for (Entry<String, List<Account>> entry : accountsMap.entrySet()) {
//			c = dbs.getCustomerById(entry.getKey());
//			outputBuilder.append("Name: "+c.getLastName()+", "+c.getFirstName()+", "
//					+ "Username: "+c.getUsername()+", DOB: "+c.getDob()+", SSN: "+c.getSsn()
//					+", Email: "+c.getEmail()+", Phone #: "+c.getPhoneNumber());
//			if (c.isApplicationPending()) {
//				outputBuilder.append(", New Customer Application Pending\n");
//			} else {
//				outputBuilder.append(", Existing Customer\n");
//			}
//			for (Account a : entry.getValue()) {
//				outputBuilder.append("Pending Act. #: "+a.getAccountNumber()+", Starting Balance: "+a.getBalance());
//			}
//		}
//		
//		// And... we return the content
//		return outputBuilder.toString();
//	}
	
	public Account getAccount(long accountNumber, long routingNumber) throws BusinessException {
		if (routingNumber!=Account.getRoutingNumber()) {
			// Check routing number
			BusinessException b = new BusinessException("Given routing number does not match Bank of Ben's routing number. Please check "
					+ "that your information is correct. If it is, contact a Bank of Ben employee to remedy the issue.");
			loggy.error(b);
			throw b;
		} else {
			Account account = dbs.getAccount(accountNumber, routingNumber);
			return account;
		}
	}

	public Account makeDeposit(double deposit, Account account, User customerOrEmployee) throws BusinessException {
		if (account.isPending()) {
			BusinessException b = new BusinessException("Cannot operate on an account that is pending.");
			loggy.error(b);
			throw b;
		}
		if (ValidationTools.isValidMonetaryAmount(deposit)) {
			if (Double.valueOf(account.getBalance() + deposit)==Double.POSITIVE_INFINITY) {
				// Check that the maximum balance is not exceeded
				BusinessException b = new BusinessException("Deposits that would result in balances in excess of "+Double.MAX_VALUE+" are handled "
						+ "via another system. Contact a Bank of Ben employee for more details.");
				loggy.error(b);
				throw b;
			} else {
				if (customerOrEmployee instanceof Customer) {
//					account.setBalance(account.getBalance() + deposit, (Customer) customerOrEmployee);
					account = dbs.updateAccountBalance(account, deposit, account);
				} else if (customerOrEmployee instanceof Employee) {
//					account.setBalance(account.getBalance() + deposit, (Employee) customerOrEmployee);
					account = dbs.updateAccountBalance(account, deposit, account);
				} else {
					BusinessException b = new BusinessException("Invalid credentials to make deposit into account "+account.getAccountNumber()+". "
							+ "Please ensure your information is correct.");
					loggy.error(b);
					throw b;
				}
			}
		} else {
			BusinessException b = new BusinessException("Deposit amount must be a positive number that has only "
					+ "two digits after the decimal point.");
			loggy.error(b);
			throw b;
		}
		return account;
	}

	public Account makeWithdrawal(double withdrawal, Account account, User customerOrEmployee) throws BusinessException {
		if (account.isPending()) {
			BusinessException b = new BusinessException("Cannot operate on an account that is pending.");
			loggy.error(b);
			throw b;
		}
		if (ValidationTools.isValidMonetaryAmount(withdrawal)) {
			if (Double.valueOf(account.getBalance() - withdrawal) < 0) {
				// Check that the balance would not become negative
				BusinessException b = new BusinessException("Withdrawal amount "+withdrawal+" exceeds the amount available in account "
						+account.getAccountNumber()+". Please check that your information is correct. If it is, contact "
						+ "a Bank of Ben employee to remedy the issue.");
				loggy.error(b);
				throw b;
			} else {
				if (customerOrEmployee instanceof Customer) {
//					account.setBalance(account.getBalance() - withdrawal, (Customer) customerOrEmployee);
					account = dbs.updateAccountBalance(account, -withdrawal, account);
				} else if (customerOrEmployee instanceof Employee) {
//					account.setBalance(account.getBalance() - withdrawal, (Employee) customerOrEmployee);
					account = dbs.updateAccountBalance(account, -withdrawal, account);
				} else {
					BusinessException b =  new BusinessException("Invalid credentials to make withdrawal from account "+account.getAccountNumber()+". "
							+ "Please ensure your information is correct.");
					loggy.error(b);
					throw b;
				}
			}
		} else {
			BusinessException b = new BusinessException("Withdrawal amount must be a positive number that has only "
					+ "two digits after the decimal point.");
			loggy.error(b);
			throw b;
		}
		return account;
	}
	
	public void makePayment(Payment payment) throws BusinessException {
		Account payingAccount = dbs.getAccount(payment.getPayingAccountNumber(), Account.getRoutingNumber());
		if (payingAccount.isPending()) {
			BusinessException b = new BusinessException("Cannot operate on an account that is pending.");
			loggy.error(b);
			throw b;
		}
		Account receivingAccount = dbs.getAccount(payment.getReceivingAccountNumber(), Account.getRoutingNumber());
		if (receivingAccount.isPending()) {
			BusinessException b = new BusinessException("Cannot operate on an account that is pending.");
			loggy.error(b);
			throw b;
		}
		double payingAccountBalance = Math.floor((payingAccount.getBalance()-payment.getAmount())*100)/100;
		payingAccount.setBalance(payingAccountBalance, payment);
		double receivingAccountBalance = Math.floor((payingAccount.getBalance()-payment.getAmount())*100)/100;
		receivingAccount.setBalance(receivingAccountBalance, payment);
		payingAccount = dbs.updateAccountBalance(payingAccount, -payment.getAmount(), receivingAccount);
		receivingAccount = dbs.updateAccountBalance(receivingAccount, payment.getAmount(), payingAccount);
		dbs.resolvePendingPayment(payment);
	}
	
	public void makeRequest(Request request) throws BusinessException {
		Account requestorAccount = dbs.getAccount(request.getRequestorAccountNumber(), Account.getRoutingNumber());
		if (requestorAccount.isPending()) {
			BusinessException b = new BusinessException("Cannot operate on an account that is pending.");
			loggy.error(b);
			throw b;
		}
		Account soughtAccount = dbs.getAccount(request.getSoughtAccountNumber(), Account.getRoutingNumber());
		if (soughtAccount.isPending()) {
			BusinessException b = new BusinessException("Cannot operate on an account that is pending.");
			loggy.error(b);
			throw b;
		}
		double requestorAccountBalance = Math.floor((requestorAccount.getBalance()+request.getAmount())*100)/100;
		requestorAccount.setBalance(requestorAccountBalance, request);
		double soughtAccountBalance = Math.floor((soughtAccount.getBalance()-request.getAmount())*100)/100;
		soughtAccount.setBalance(soughtAccountBalance, request);
		requestorAccount = dbs.updateAccountBalance(requestorAccount, request.getAmount(), soughtAccount);
		soughtAccount = dbs.updateAccountBalance(soughtAccount, -request.getAmount(), requestorAccount);
		dbs.resolvePendingRequest(request);
	}

	public boolean employeeExists(int employeeId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Long> getAccountNumbersForCustomer(Customer customer) throws BusinessException {
		BankOfBenServices dbs = new BankOfBenServices();
		List<Account> accounts = dbs.getAccountsForCustomer(customer);
		List<Long> accountNumbers = new ArrayList<>();
		for (Account account : accounts) {
			accountNumbers.add(account.getAccountNumber());
		}
		return accountNumbers;
	}
	
	public List<Account> getAccountsForCustomer(Customer customer) throws BusinessException {
		BankOfBenServices dbs = new BankOfBenServices();
		return dbs.getAccountsForCustomer(customer);
	}

	public List<Transfer> getTransfers(Customer customer) throws BusinessException {
		return dbs.getTransfers(customer);
	}

	public void postPayment(Customer customer, Account paySourceAccount, Account payDestinationAccount, double amount) throws BusinessException {
		if (paySourceAccount.isPending() || payDestinationAccount.isPending()) {
			BusinessException b = new BusinessException("Cannot operate on an account that is pending.");
			loggy.error(b);
			throw b;
		}
		dbs.postPayement(customer, paySourceAccount, payDestinationAccount, amount);
	}
	
	public void postRequest(Customer customer, Account reqSourceAccount, Account reqDestinationAccount, double amount) throws BusinessException {
		if (reqSourceAccount.isPending() || reqDestinationAccount.isPending()) {
			BusinessException b = new BusinessException("Cannot operate on an account that is pending.");
			loggy.error(b);
			throw b;
		}
		dbs.postRequest(customer, reqSourceAccount, reqDestinationAccount, amount);
	}

	public Account acceptTransfer(Transfer transfer, Customer customer) throws BusinessException {
		
		Account a = null;
		
		BusinessLayer bl = new BusinessLayer();
		if (transfer.isPending()) {
			if (transfer instanceof Payment) {
				Payment p = (Payment) transfer;
				if (dbs.doesCustomerOwnAccountNumber(customer.getId(), p.getPayingAccountNumber()) && !dbs.doesCustomerOwnAccountNumber(customer.getId(), p.getReceivingAccountNumber())) {
					BusinessException b = new BusinessException("Only the party who would receive money can choose to accept payment. "
							+ "Until that party accepts the payment you are allowed to halt it, however.");
					loggy.error(b);
					throw b;
				}
				bl.makePayment(p);
				a = dbs.getAccount(p.getReceivingAccountNumber(), Account.getRoutingNumber());
			} else if (transfer instanceof Request) {
				Request r = (Request) transfer;
				if (dbs.doesCustomerOwnAccountNumber(customer.getId(), r.getRequestorAccountNumber()) && !dbs.doesCustomerOwnAccountNumber(customer.getId(), r.getSoughtAccountNumber())) {
					BusinessException b = new BusinessException("Only the party who would have to pay money can choose to accept the request.");
					loggy.error(b);
					throw b;
				}
				if (r.getAmount() > dbs.getAccount(r.getSoughtAccountNumber(), Account.getRoutingNumber()).getBalance()) {
					BusinessException b = new BusinessException("You have insufficient funds in account "+r.getSoughtAccountNumber()
						+" to accept this request. Please add more money and try again.");
					loggy.error(b);
					throw b;
				}
				bl.makeRequest(r);
				a = dbs.getAccount(r.getSoughtAccountNumber(), Account.getRoutingNumber());
			} else {
				BusinessException b = new BusinessException("Could not accept transfer "+transfer.getId()+" due to too little information given. "
						+ "Need to specify if the transfer is a payment or a request.");
				loggy.error(b);
				throw b;
			}
		} else {
			BusinessException b = new BusinessException("Cannot accept transfer "+transfer.getId()+". Transfer is not pending.");
			loggy.error(b);
			throw b;
		}
		
		return a;
	}

	public void haltTransfer(Transfer transfer, Customer customer) throws BusinessException {
		if (transfer.isPending()) {
			dbs.haltTransfer(transfer, customer);
		} else {
			BusinessException b = new BusinessException("No need to halt transfer "+transfer.getId()+". Transfer is not pending.");
			loggy.error(b);
			throw b;
		}
	}

	public String viewTransactions() throws BusinessException {
		return dbs.getTransactions();
	}

	public String viewTransactions(long accountNumber) throws BusinessException {
		return dbs.getTransactions(accountNumber);
	}
	
	public String viewTransactionsUpTo(int numberOfTransactions) throws BusinessException {
		return dbs.getTransactionsUpTo(numberOfTransactions);
	}

	public void approveNewCustomerAccountApplication(Account a) throws BusinessException {
		dbs.approveNewCustomerAccountApplication(a);
	}

	public void rejectNewCustomerAccountApplication(Account a) throws BusinessException {
		dbs.rejectNewCustomerAccountApplication(a);
	}

	public void approveExistingCustomerAccountApplication(Account a) throws BusinessException {
		dbs.approveExistingCustomerAccountApplication(a);
	}

	public void rejectExistingCustomerAccountApplication(Account a) throws BusinessException {
		dbs.rejectExistingCustomerAccountApplication(a);
	}
	
}
