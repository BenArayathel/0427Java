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
	public void depositTest() throws BankException {
		User user = new User();
		user.setUser_id("0");
		user.setUsername("UNITtest");
		user.setPassword("test");
		user.setApproved(0);
		
		Account account = new Account();
		account.setAccount_id("0");
		account.setUser_id("0");
		account.setAccount_name("UNITtest");
		account.setBalance(5000);
		
		String accountName = "UNITtest";
		String depositAmount = "10";

		adi.deposit(user, accountName, depositAmount);
		System.out.println(account.getBalance());
		assert(account.getBalance() == 5010);
		
		
		
	}
	
	@Test
	public void withdrawalTest() {
		
	}
	

}
