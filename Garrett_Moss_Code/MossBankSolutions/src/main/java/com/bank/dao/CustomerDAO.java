package com.bank.dao;

import com.bank.exception.BankException;
import com.bank.model.CheckingAccount;
import com.bank.model.Customer;
import com.bank.model.SavingsAccount;

public interface CustomerDAO {
	public Customer createCustomerAccount (Customer createAccount) throws BankException;
	public CheckingAccount createCheckingAccount (CheckingAccount createChecking) throws BankException;
	public SavingsAccount createSavingsAccount (SavingsAccount createSavings) throws BankException;
	public Customer viewBalance(int accountNumber) throws BankException;
	public Customer withdraw (int withdraw) throws BankException;
	public Customer deposit (int deposit) throws BankException;
	public Customer moneyTransfer (String accountNumber, int transferAmount) throws BankException;
	public Customer acceptTransfer (Boolean acceptance) throws BankException;
	public Customer deleteCustomer(String username) throws BankException;

}
