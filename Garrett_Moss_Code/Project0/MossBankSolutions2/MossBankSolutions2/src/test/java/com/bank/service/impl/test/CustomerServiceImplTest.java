package com.bank.service.impl.test;

import static org.hamcrest.CoreMatchers.nullValue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bank.exception.BankException;
import com.bank.model.Customer;
import com.bank.service.CustomerService;
import com.bank.service.impl.CustomerServiceImpl;

public class CustomerServiceImplTest {
	
@Test
public void validDepositAmount() {
	String deposit = "70.00";
	boolean b = false;
	if (deposit.matches("[0-9]{1,10}[.]{1}[0-1]{2}")) {
		b=true;
	}
}
@Test
public void validAccountNumber() {
	String accountNumber = "1000000000";
	boolean b = false;
	if (accountNumber.matches("[0-9]{10}")) {
		b=true;
	}
}
	
	@Test
public void deposit() throws BankException {
		String deposit = "1";
		Customer customer=null;
		if (Double.parseDouble(deposit) > 0) {
			customer.setAccountBalance(deposit);
		} else {
			throw new BankException("Deposit ammount must be greater than 0");
		}
	}
	@Test
	public void validWithdrawAmount() {
		boolean b = false;
		String withdraw = "20.00";
		String accountBalance = "30.00";
		if (withdraw.matches("[0-9]{1,10}[.]{1}[0-9]{2}") && (Double.parseDouble(withdraw) < Double.parseDouble(accountBalance))) {
			b=true;
		}
	}
}
	

