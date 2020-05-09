package com.bank.service.impl;

import com.bank.exception.BankException;
import com.bank.model.CheckingAccount;
import com.bank.model.Customer;
import com.bank.model.SavingsAccount;
import com.bank.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	public Customer createCustomerAccount(Customer createAccount) throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer viewBalance(String accountNumber) throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer withdraw(int withdraw) throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer deposit(int deposit) throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer moneyTransfer(String accountNumber, int transferAmount) throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer acceptTransfer(Boolean acceptance) throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer deleteCustomer(String username) throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CheckingAccount createCheckingAccount(CheckingAccount createChecking) throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SavingsAccount createSavingsAccount(SavingsAccount createSavings) throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

}
