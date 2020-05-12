package com.bank.service;

import com.bank.exception.BankException;
import com.bank.model.Customer;
import com.bank.model.Employee;

public interface CustomerService {
	public Customer createCustomerAccount (Customer createAccount) throws BankException;
	public Customer viewBalance(String accountNumber) throws BankException;
	public Customer withdraw (String accountNumber, String withdraw, String accountBalance) throws BankException;
	public Customer deposit (String deposit, String accountNumber) throws BankException;
	public Customer moneyTransfer (String fromAccountNumber, String transferAmount, String toAccountNumber) throws BankException;
	public Customer acceptTransfer (String fromAccountNumber, String transferAmount, String toAccountNumber) throws BankException;
	public String deleteCustomer(String accountNumber) throws BankException;
	public Customer loginVerification(String username) throws BankException;
}
