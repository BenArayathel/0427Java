package com.bank.dao_implementation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bank.models.Account;
import com.bank.models.Transaction;
import com.bank.models.User;
import com.bank.tools.BankException;

public class AccountDAOImplementationTest {
	
	AccountDAOImplementation adi = new AccountDAOImplementation();
	UserDAOImplementation udi = new UserDAOImplementation();

	@Test
	public void loggingTransactionDepositTest() throws BankException {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		List<String> allNames = new ArrayList<String>();
		
		String amount = "40.00";
		String account_name = "UNITtest";
		String user_id = "0";
		String type = "deposit";
		

		// try using the logTransaction function
		adi.logTransaction(amount, account_name, user_id, type);
		// Fill empty list with all the transactions from the db table
		transactionList = adi.listAllTransactions();

		// fill the empty string list with all the account names
		for (Transaction t : transactionList) {
			allNames.add(t.getAccount_name());
		}

		// test that we successfully added our test case to the db
		assert(allNames.contains(account_name));
		
		
		// clean it up afterwards
		adi.deleteTransaction(account_name);
	}
	
	
	@Test
	public void testCreateAccount() throws BankException {
		
		Account account = new Account();
		String accountName = "TESTtestUNIT";
		String depositAmount = "100";
		
		User user = new User();
		user.setUsername("UNITtest2");
		user.setPassword("UNITtest2");
		
		udi.createUser(user);
		
		List<Account> accountsBefore = adi.listAccounts();
		int sizeBefore = accountsBefore.size();
		
		adi.createAccount(user, accountName, depositAmount);

		List<Account> accountsAfter = adi.listAccounts();
		int sizeAfter = accountsAfter.size();
		
		assert(sizeBefore < sizeAfter);
						
		udi.deleteUser("UNITtest2");
		adi.deleteAccount("TESTtestUNIT");
	}
	
	@Test
	public void depositTest() throws BankException {		
		// perm test user, test account, no creation no deletion
		// getBalance, make deposit, getBalance = bal + dep
		
		// access the user...
		User user = udi.accessUserObject("UNITtest");
		// ...and access the account
		Account accountBefore = adi.listAccountByID("572");
		
		// set up the deposit and the balance before
		String accountName = "UNITtest";
		String depositAmount = "10";
		Double balanceBefore = accountBefore.getBalance();
//		System.out.println(balanceBefore);
		
		// run the function to make a deposit
		adi.deposit(user, accountName, depositAmount);
		
		// find the balance after, access account again, and finally...
		Account accountAfter = adi.listAccountByID("572");
		Double balanceAfter = accountAfter.getBalance();
//		System.out.println(balanceAfter);
		
		// test that 10 was added
		assert(balanceAfter == (balanceBefore + 10.00));
		
	}
	
	@Test
	public void withdrawalTest() throws BankException {
		// follow pattern of deposit test
		
		// access the user...
		User user = udi.accessUserObject("UNITtest");
		// ...and access the account
		Account accountBefore = adi.listAccountByID("572");
		
		// set up the deposit and the balance before
		String accountName = "UNITtest";
		String withdrawalAmount = "10";
		Double balanceBefore = accountBefore.getBalance();
		
		// run the function to make a deposit
		adi.withdraw(user, accountName, withdrawalAmount);
		
		// find the balance after, access account again, and finally...
		Account accountAfter = adi.listAccountByID("572");
		Double balanceAfter = accountAfter.getBalance();
		
		// test that 10 was added
		assert(balanceAfter == (balanceBefore - 10.00));
		
	}
	

}
