package com.bank.dao_implementation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bank.models.Transaction;
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
			System.out.println(allNames);
		}

		// test that we successfully added our test case to the db
		assert(allNames.contains(account_name));
		
		
		// clean it up afterwards
		adi.deleteTransaction(account_name);
		
		System.out.println(allNames);
	}
	
	@Test
	public void depositTest() {
		
	}
	
	@Test
	public void withdrawalTest() {
		
	}
	

}
