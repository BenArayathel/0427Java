package com.bankofben.business;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

	public Customer applyForAccount(User user) throws BusinessException {
		return dbs.applyForAccount(user);
	}

	public void applyForAccount_returnNothing(User user) throws BusinessException{
		dbs.applyForAccount(user);
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
				throw new BusinessException("The entry "+accountNumberString+" is not a valid "
						+ "account number. Account numbers must be positive 10-digit numbers. "
						+ "Please try again.");
			}
		} else {
			throw new BusinessException("The entry "+accountNumberString+" is not a valid "
					+ "account number. Account numbers must be positive 10-digit numbers. "
					+ "Please try again.");
		}
		return accountNumber;
	}
	
	public long validateAccountNumber(long accountNumber) throws BusinessException {
		return validateAccountNumber(Long.toString(accountNumber));
	}

	public long validateAccountNumber(String sourceAccountString, String sourceRoutingString, Customer customer)
			throws BusinessException {
		long sourceAccountNumber = validateAccountNumber(sourceAccountString);
		long sourceRoutingNumber = validateRoutingNumber(sourceRoutingString);
		List<Account> customerAccounts = dbs.getAccountsForCustomer(customer);
		Account sourceAccount = dbs.getAccount(sourceAccountNumber, sourceRoutingNumber);
		if (customerAccounts.contains(sourceAccount)) {
			return sourceAccount.getAccountNumber();
		} else {
			throw new BusinessException("The account number "+sourceAccountString+" and routing number "+sourceRoutingString
					+" do not correspond to an account owned by "+customer.getUsername());
		}
	}
	
	public long validateAccountNumber(long sourceAccountNumber, String sourceRoutingString, Customer customer)
			throws BusinessException {
		return validateAccountNumber(Long.toString(sourceAccountNumber), sourceRoutingString, customer);
	}
	
	public long validateAccountNumber(String sourceAccountString, long sourceRoutingNumber, Customer customer)
			throws BusinessException {
		return validateAccountNumber(sourceAccountString, Long.toString(sourceRoutingNumber), customer);
	}
	
	public long validateAccountNumber(long sourceAccountNumber, long sourceRoutingNumber, Customer customer)
			throws BusinessException {
		return validateAccountNumber(Long.toString(sourceAccountNumber), Long.toString(sourceRoutingNumber), customer);
	}
	
	public Account validateAccount(Account account, Customer customer) throws BusinessException {
		List<Account> customerAccounts = dbs.getAccountsForCustomer(customer);
		if (customerAccounts.contains(account)) {
			return account;
		} else {
			throw new BusinessException("The account number "+account.getAccountNumber()+" and routing number "
					+Account.getRoutingNumber()+" do not correspond to an account owned by "+customer.getUsername());
		}
	}
	
	public Account validateAccount(long sourceAccountNumber, long sourceRoutingNumber, Customer customer)
			throws BusinessException {
		Account account = dbs.getAccount(sourceAccountNumber, sourceRoutingNumber);
		return validateAccount(account, customer);
	}
	
	public double validateMonetaryAmount(String amount) throws BusinessException {
		long amt;
		if (ValidationTools.isValidMonetaryAmount(amount)) {
			try {
				amt = Long.parseLong(amount);
			} catch (NumberFormatException e) {
				throw new BusinessException("The amount "+amount+" is not a valid monetary amount.");
			}
		} else {
			throw new BusinessException("The amount "+amount+" is not a valid monetary amount.");
		}
		return amt;
	}
	
	public double validateMonetaryAmount(double amount) throws BusinessException {
		if (ValidationTools.isValidMonetaryAmount(amount)) {
			return amount;
		} else {
			throw new BusinessException("The amount "+amount+" is not a valid monetary amount.");
		}
	}
	
	public long validateRoutingNumber(String routingNumberString) throws BusinessException {
		long routingNumber;
		if (routingNumberString.matches("[0-9]{9}")) {
			try {
				routingNumber = Long.parseLong(routingNumberString);
			} catch (NumberFormatException e){
				throw new BusinessException("The entry "+routingNumberString+" is not a valid "
						+ "account number. Account numbers must be positive 10-digit numbers. "
						+ "Please try again.");
			}
			if (routingNumber!=Account.getRoutingNumber()) {
				throw new BusinessException("Invalid routing number. Please try again.");
			}
		} else {
			throw new BusinessException("The entry "+routingNumberString+" is not a valid "
					+ "account number. Account numbers must be positive 10-digit numbers. "
					+ "Please try again.");
		}
		return routingNumber;
	}
	
	public long isValidroutingNumber(long routingNumber) throws BusinessException {
		return validateAccountNumber(Long.toString(routingNumber));
	}

	public String viewPendingApplications() throws BusinessException {
		List<Customer> customers = dbs.getCustomersByApplicationPendingStatus(true);
		StringBuilder outputBuilder = new StringBuilder();
		// We'll start by organizing the columns
		outputBuilder.append("Last Name\t|\tFirst Name\t|\tMiddle Name\t|\tMom's Maiden Name\t|\tDate of Birth\t|\t"
				+ "SSN\t|\tEmail\t|\tPhone #\t|\tUsername\t|\tApplication Pending\n");
		// Now we get the content
		for (Customer c : customers) {
			outputBuilder.append(c.getLastName()+"\t|\t"+c.getFirstName()+"\t|\t"+c.getMiddleName()+"\t|\t"
					+c.getMomsMaidenName()+"\t|\t"+c.getDob()+"\t|\t"+c.getSsn()+"\t|\t"+c.getEmail()+"\t|\t"
					+c.getPhoneNumber()+"\t|\t"+c.getUsername()+"\t|\t"+c.isApplicationPending());
		}
		// And... we return the content
		return outputBuilder.toString();
	}
	
	public Account getAccount(long accountNumber, long routingNumber)
			throws BusinessException {
		if (routingNumber!=Account.getRoutingNumber()) {
			// Check routing number
			throw new BusinessException("Given routing number does not match Bank of Ben's routing number. Please check "
					+ "that your information is correct. If it is, contact a Bank of Ben employee to remedy the issue.");
		} else {
			BankOfBenServices dbs = new BankOfBenServices();
			Account account = dbs.getAccount(accountNumber, routingNumber);
			return account;
		}
	}

	public void makeDeposit(double deposit, Account account, User customerOrEmployee) throws BusinessException {
		if (ValidationTools.isValidMonetaryAmount(deposit)) {
			if (Double.valueOf(account.getBalance() + deposit)==Double.POSITIVE_INFINITY) {
				// Check that the maximum balance is not exceeded
				throw new BusinessException("Deposits that would result in balances in excess of "+Double.MAX_VALUE+" are handled "
						+ "via another system. Contact a Bank of Ben employee for more details.");
			} else {
				if (customerOrEmployee instanceof Customer) {
					account.setBalance(account.getBalance() + deposit, (Customer) customerOrEmployee);
				} else if (customerOrEmployee instanceof Employee) {
					account.setBalance(account.getBalance() + deposit, (Employee) customerOrEmployee);
				} else {
					throw new BusinessException("Invalid credentials to make deposit into account "+account.getAccountNumber()+". "
							+ "Please ensure your information is correct.");
				}
			}
		} else {
			throw new BusinessException("Deposit amount must be a positive number that has only "
					+ "two digits after the decimal point.");
		}
	}

	public void makeWithdrawal(double withdrawal, Account account, User customerOrEmployee) throws BusinessException {
		if (ValidationTools.isValidMonetaryAmount(withdrawal)) {
			if (Double.valueOf(account.getBalance() - withdrawal) < 0) {
				// Check that the balance would not become negative
				throw new BusinessException("Withdrawal amount "+withdrawal+" exceeds the amount available in account"
						+account.getAccountNumber()+". Please check that your information is correct. If it is, contact "
						+ "a Bank of Ben employee to remedy the issue.");
			} else {
				if (customerOrEmployee instanceof Customer) {
					account.setBalance(account.getBalance() - withdrawal, (Customer) customerOrEmployee);
				} else if (customerOrEmployee instanceof Employee) {
					account.setBalance(account.getBalance() - withdrawal, (Employee) customerOrEmployee);
				} else {
					throw new BusinessException("Invalid credentials to make withdrawal from account "+account.getAccountNumber()+". "
							+ "Please ensure your information is correct.");
				}
			}
		} else {
			throw new BusinessException("Withdrawal amount must be a positive number that has only "
					+ "two digits after the decimal point.");
		}
	}
	
	public void makePayment(Payment payment) throws BusinessException {
		Account payingAccount = dbs.getAccount(payment.getPayingAccountNumber(), Account.getRoutingNumber());
		Account receivingAccount = dbs.getAccount(payment.getReceivingAccountNumber(), Account.getRoutingNumber());
		payingAccount.setBalance(payingAccount.getBalance()-payment.getAmount(), payment);
		receivingAccount.setBalance(receivingAccount.getBalance()+payment.getAmount(), payment);
		dbs.resolvePendingPayment(payment);
	}
	
	public void makeRequest(Request request) throws BusinessException {
		Account requestorAccount = dbs.getAccount(request.getRequestorAccountNumber(), Account.getRoutingNumber());
		Account soughtAccount = dbs.getAccount(request.getSoughtAccountNumber(), Account.getRoutingNumber());
		requestorAccount.setBalance(requestorAccount.getBalance()+request.getAmount(), request);
		soughtAccount.setBalance(soughtAccount.getBalance()-request.getAmount(), request);
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
		dbs.postPayement(customer, paySourceAccount, payDestinationAccount, amount);
	}
	
	public void postRequest(Customer customer, Account reqSourceAccount, Account reqDestinationAccount, double amount) throws BusinessException {
		dbs.postRequest(customer, reqSourceAccount, reqDestinationAccount, amount);
	}

	public void acceptTransfer(Transfer transfer, Customer customer) throws BusinessException {
		
		BusinessLayer bl = new BusinessLayer();
		if (transfer.isPending()) {
			if (transfer instanceof Payment) {
				Payment p = (Payment) transfer;
				bl.makePayment(p);
			} else if (transfer instanceof Request) {
				Request r = (Request) transfer;
				bl.makeRequest(r);
			} else {
				throw new BusinessException("Could not accept transfer "+transfer.getId()+" due to too little information given. "
						+ "Need to specify if the transfer is a payment or a request.");
			}
		} else {
			throw new BusinessException("Cannot accept transfer "+transfer.getId()+". Transfer is not pending.");
		}
	}

	public void rejectTransfer(Transfer transfer, Customer customer) throws BusinessException {
		if (transfer.isPending()) {
			dbs.rejectTransfer(transfer, customer);
		} else {
			throw new BusinessException("No need to reject transfer "+transfer.getId()+". Transfer is not pending.");
		}
	}

	public String viewTransactions() throws BusinessException {
		return dbs.getTransactions();
	}

	public String viewTransactions(long accountNumber) throws BusinessException {
		return dbs.getTransactions(accountNumber);
	}
	
	public String viewTransactions(int numberOfTransactions) throws BusinessException {
		return dbs.getTransactions(numberOfTransactions);
	}
	
}
