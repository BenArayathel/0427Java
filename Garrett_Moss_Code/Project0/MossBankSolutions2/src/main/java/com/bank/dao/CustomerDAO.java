package com.bank.dao;

import com.bank.exception.BankException;
import com.bank.model.Customer;

public interface CustomerDAO {
	public Customer createCustomerAccount (Customer createAccount) throws BankException;
	public Customer viewBalance(String accountNumber) throws BankException;
	public Customer withdraw (String accountNumber, String withdraw) throws BankException;
	public Customer deposit (String deposit, String accountNumber) throws BankException;
	public Customer moneyTransfer (String fromAccountNumber, String transferAmount, String toAccountNumber) throws BankException;
	public Customer acceptTransfer (String fromAccountNumber, String transferAmount, String toAccountNumber) throws BankException;
	public String deleteCustomer(String accountnumber) throws BankException;
	public Customer loginVerification(String username, String password) throws BankException;

}
