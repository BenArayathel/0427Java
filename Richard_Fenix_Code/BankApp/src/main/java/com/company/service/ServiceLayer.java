package com.company.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.company.controller.BankServiceController;
import com.company.dao.AccountDaoJdbcImpl;
import com.company.dao.AccountTypeDaoJdbcImpl;
import com.company.dao.CustomerDaoJdbcImpl;
import com.company.dao.RegistrationDaoJdbcImpl;
import com.company.dao.TransactionDaoJdbcImpl;
import com.company.model.Account;
import com.company.model.AccountType;
import com.company.model.Customer;
import com.company.model.Registration;
import com.company.model.Transaction;
import com.company.view.BankApp;
import com.company.viewModel.AccountViewModel;

public class ServiceLayer {
	
	private final RegistrationDaoJdbcImpl registrationDao = new RegistrationDaoJdbcImpl();
	private final CustomerDaoJdbcImpl customerDao = new CustomerDaoJdbcImpl();
	private final AccountDaoJdbcImpl accountDao = new AccountDaoJdbcImpl();
	private final TransactionDaoJdbcImpl transactionDao = new TransactionDaoJdbcImpl();
	private final AccountTypeDaoJdbcImpl accountTypeDao = new AccountTypeDaoJdbcImpl();

	
	public Customer validateLogin(String loginName, String password) {
				
        // Validate login Name.
        if (loginName.trim().length() == 0 || password.trim().length() == 0) {
            BankApp.loggy.info("Login Name and Password must not be blank.");
            return null;
        };
        
        // Get customerId from registration table
        int customerId = registrationDao.getCustomerIdByLoginName(loginName, password);
        
        // if no customerId found, customerID returned will be 0 and return false;      
        if (customerId == 0) {
        	BankApp.loggy.info("Customer not found. Please try again or enroll.");
        	return null;
        };
        
        // find customer detail from customer table.
        BankApp.loggy.info("Customer ID found... proceeding to application screen.");
        
        Customer customer = customerDao.getCustomer(customerId);
        
		return customer;
	}
	
	
	public void createCustomerAccount(String firstName, String lastName, String birthday, String usState, String accountType, BigDecimal bigDecimalBalance) {
	
		// Create and insert Customer
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		
		// convert java.util.date to sql.Date.
        SimpleDateFormat format = new SimpleDateFormat( "MM/dd/yyyy" );  // United States style of format.
        java.util.Date javaDate;
        java.sql.Date sqlDate;
        try {
			javaDate = format.parse( birthday );
	        // Date type below is java.sql.Date
	        sqlDate = new java.sql.Date(javaDate.getTime());
	        customer.setBirthday(sqlDate);
		} catch (ParseException e) {
			BankApp.loggy.error("Problem encountered in parsing date.");
			e.printStackTrace();
		}  // Notice the ".util." of package name.

		customer.setState(usState);
		
		customer = customerDao.addCustomer(customer);
		
		//Create and insert Account 
		Account account = new Account();
		account.setCustomerId(customer.getCustomerId());
		account.setAccountType(accountType);
		account.setBalance(bigDecimalBalance);
		account.setApproved(false);
		
		account = accountDao.addAccount(account);
		
		/* Insert initial transaction replaced by PL/SQL Trigger: 
		 * TRIGGER account_after_insert

		// create and insert transactions in transaction table.
		Transaction transaction = new Transaction();
		transaction.setAccountId(account.getAccountId());
		transaction.setTransactionType("INIT");
		transaction.setAmount(bigDecimalBalance);
        transaction.setTransTime(Timestamp.valueOf(LocalDateTime.now())); 
        
        transaction = transactionDao.addTransaction(transaction);
        */
		
	}
	
	public Boolean createAdditionalAccount(Integer customerId, String accountType, BigDecimal bigDecimalBalance) {
		
		Customer customer = new Customer();
		customer = customerDao.getCustomer(customerId);
		if (customer == null) {
			return false;
		}
		
		//Create and insert Account 
		Account account = new Account();
		account.setCustomerId(customer.getCustomerId());
		account.setAccountType(accountType);
		account.setBalance(bigDecimalBalance);
		account.setApproved(false);
		
		account = accountDao.addAccount(account);
		
		/* Initial transaction inserted by PL/SQL Trigger: 
		 * TRIGGER account_after_insert
		
		// create and insert transactions in transaction table.
		Transaction transaction = new Transaction();
		transaction.setAccountId(account.getAccountId());
		transaction.setTransactionType("INIT");
		transaction.setAmount(bigDecimalBalance);
        transaction.setTransTime(Timestamp.valueOf(LocalDateTime.now())); 
        
        transaction = transactionDao.addTransaction(transaction);
        
        */

		return true;
	};

	
	public AccountViewModel getCustomerAccountDetail(String accountId) {

		AccountViewModel avm = new AccountViewModel();
		
		Account account = new Account();
		account = accountDao.getAccount(accountId);
		
    	if (account == null) {
    		BankApp.loggy.info("Account not found!");
    		return null;
    	}
		
		Customer customer = new Customer();
		customer = customerDao.getCustomer(account.getCustomerId());
			
		AccountType accountType = new AccountType();
		accountType = accountTypeDao.getAccountType(account.getAccountType());
	
		//		AccountViewModel Field Reference	
		//		================================
		//		private String accountId;
		//		private Integer customerId;
		//		private String firstName;
		//		private String lastName;
		//		private Date birthday;
		//		private String state;
		//		private String accountType;
		//		private String accountDescription;
		//		private BigDecimal balance;
		//		private boolean	approved;

		avm.setAccountId(account.getAccountId());
		avm.setCustomerId(customer.getCustomerId());
		avm.setFirstName(customer.getFirstName());
		avm.setLastName(customer.getLastName());
		avm.setBirthday(customer.getBirthday());
		avm.setState(customer.getState());
		avm.setAccountType(account.getAccountType());
		avm.setAccountDescription(accountType.getDescription());
		avm.setBalance(account.getBalance());
		avm.setApproved(account.isApproved());
		
		return avm;
	}

    public AccountViewModel approveAccount(String accountId) {
    	
		AccountViewModel avm = new AccountViewModel();
		
		Account account = new Account();
		
		// Surround with Try-Catch-Block
		try {
			account = accountDao.getAccount(accountId);
		} catch (NullPointerException e) {
			BankApp.loggy.error("Account not found.");
		}
		
		if (account == null) {
			return null;
		}
		
		//Update approved status to TRUE;
		if (!account.isApproved()) {
			
			// update account table set isApproved to true;
			account.setApproved(true);
			
			accountDao.updateAccount(account);
			
			// Record transaction with the amount unchanged
	    	recordTransaction(account, "APPR", account.getBalance());
			
			// create and insert transactions in transaction table.
//			Transaction transaction = new Transaction();
//			transaction.setAccountId(account.getAccountId());
//			transaction.setTransactionType("APPR");
//			transaction.setAmount(account.getBalance());
//			transaction.setEndingBalance(account.getBalance());
//	        transaction.setTransTime(Timestamp.valueOf(LocalDateTime.now())); 
//	        
//	        transaction = transactionDao.addTransaction(transaction);
			
		}
		
		Customer customer = new Customer();
		customer = customerDao.getCustomer(account.getCustomerId());
		
		AccountType accountType = new AccountType();
		accountType = accountTypeDao.getAccountType(account.getAccountType());
	
		//		AccountViewModel Field Reference	
		//		================================
		//		private String accountId;
		//		private Integer customerId;
		//		private String firstName;
		//		private String lastName;
		//		private Date birthday;
		//		private String state;
		//		private String accountType;
		//		private String accountDescription;
		//		private BigDecimal balance;
		//		private boolean	approved;

		avm.setAccountId(account.getAccountId());
		avm.setCustomerId(customer.getCustomerId());
		avm.setFirstName(customer.getFirstName());
		avm.setLastName(customer.getLastName());
		avm.setBirthday(customer.getBirthday());
		avm.setState(customer.getState());
		avm.setAccountType(account.getAccountType());
		avm.setAccountDescription(accountType.getDescription());
		avm.setBalance(account.getBalance());
		avm.setApproved(account.isApproved());
		
		return avm;
    }
    
    public List<Transaction> getLogList(){
    	
    	List<Transaction> tList = new ArrayList<Transaction>();
    	
    	tList = transactionDao.getAllTransactions();
   
    	return tList;
    }
    
    public Customer validateCustomerAccount(String firstName, String lastName, String accountId) {
    	
    	Account account = new Account();
    	account = accountDao.getAccount(accountId);
    	
    	if (account == null) {
    		BankApp.loggy.info("Customer name(s) and Account ID does not match!");
    		return null;
    	}
    	
		Customer customer = new Customer();
		customer = customerDao.getCustomer(account.getCustomerId());
		
    	if (customer == null) {
    		BankApp.loggy.info("Customer name(s) and Account ID does not match!");
    		return null;
    	}

    	if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName)){
    		if (account.isApproved()) {
    			BankApp.loggy.info("Customer validated!");
    			return customer;
    		} else {
    			BankApp.loggy.info("Account is not yet approved. Please wait or contact Bank.");
    			return null;
    		}
    	} else {
    		BankApp.loggy.info("Customer name(s) and Account ID does not match!");
    		return null;
    	}
    	
    };

    public Boolean registerUser(Integer customerId, String loginName, String loginPassword){
    	
    	boolean isRegistered = false;
    	
    	Registration registration = registrationDao.getRegistrationByCustomerId(customerId);
    	
    	if (registration == null) {
    		
    		boolean isLoginNameOk = registrationDao.isLoginNameNotUsed(loginName); 
    		
    		if (isLoginNameOk) {
    			registration = new Registration();
    			registration.setCustomerId(customerId);
    			registration.setLoginName(loginName);
    			registration.setLoginPassword(loginPassword);
    			registration = registrationDao.addRegistration(registration);
    			if (registration != null) {
    				isRegistered = true;
    			}
    		} else {
        		BankApp.loggy.info("Login Name already in use. Please try other names.");
        		isRegistered = false;
    		}
    	} else {
    		BankApp.loggy.info("Login Name and Password not created. It is already existing.");
    		BankApp.loggy.info("Please Sign On or contact admin if you forgot your Username and Password.");
    		isRegistered = false;
    	}
    	
    	return isRegistered;
    }

    
    public List<Account> getAccountListByCustomerId(Integer customerId) {
    	List<Account> aList = new ArrayList<Account>();
    	
    	aList = accountDao.getAllAccountsByCustomerId(customerId);
    	
    	return aList;
    	
    }
    
    public List<Account> getAllAccountList(){
    	return accountDao.getAllAccounts();    	
	}

    
    public Account depositAmount(Account account, BigDecimal depositAmount) {
    	
    	BigDecimal currentBalance = account.getBalance();
    	BigDecimal newBalance = currentBalance.add(depositAmount);
    	
    	account.setBalance(newBalance);
    	
    	accountDao.updateAccount(account);
    	
    	account = accountDao.getAccount(account.getAccountId());

    	
    	recordTransaction(account, "DEPO", depositAmount);

    	// Update Transaction Table
//    	Transaction transaction = new Transaction();
//		transaction.setAccountId(account.getAccountId());
//		transaction.setTransactionType("DEPO");
//		transaction.setAmount(depositAmount);
//		transaction.setEndingBalance(account.getBalance());
//        transaction.setTransTime(Timestamp.valueOf(LocalDateTime.now())); 
//        
//        transaction = transactionDao.addTransaction(transaction);
    	
    	return account;
    	
    }
    
    public Account withdrawAmount(Account account, BigDecimal withdrawAmount) {
    	
    	BigDecimal currentBalance = account.getBalance();
    	BigDecimal newBalance = currentBalance.subtract(withdrawAmount);
    	
    	account.setBalance(newBalance);
    	
    	accountDao.updateAccount(account);
    	
    	account = accountDao.getAccount(account.getAccountId());
    	
    	recordTransaction(account, "WITH", withdrawAmount);
    	
    	// Update Transaction Table
//    	Transaction transaction = new Transaction();
//		transaction.setAccountId(account.getAccountId());
//		transaction.setTransactionType("WITH");
//		transaction.setAmount(withdrawAmount);
//		transaction.setEndingBalance(account.getBalance());
//        transaction.setTransTime(Timestamp.valueOf(LocalDateTime.now())); 
//        
//        transaction = transactionDao.addTransaction(transaction);
    	
    	return account;

    	
    };

    public Account transferAmount(Account fromAccount, String toAccountId, BigDecimal transferAmount) {
    	
    	
    	Account sourceAccount = withdrawAmount(fromAccount, transferAmount);
    	
    	//recordTransaction(sourceAccount, "TRAN", transferAmount);
    	
    	Account toAccount = accountDao.getAccount(toAccountId);
    	
    	toAccount = depositAmount(toAccount, transferAmount);
    	
    	//record
    	
		return sourceAccount;
	};
	
	//helper
	public void recordTransaction(Account account, String transactionType, BigDecimal amount) {
    	Transaction transaction = new Transaction();
		transaction.setAccountId(account.getAccountId());
		transaction.setTransactionType(transactionType);
		transaction.setAmount(amount);
		transaction.setEndingBalance(account.getBalance());
        transaction.setTransTime(Timestamp.valueOf(LocalDateTime.now())); 
        
        transaction = transactionDao.addTransaction(transaction);
		
	}
	
	
    
}
