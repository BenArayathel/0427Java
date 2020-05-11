package com.bank.service.impl.test;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.bank.exception.BankException;
import com.bank.model.Customer;

public class EmployeeServiceImplTest {

	
	
	@Test
	public void viewCustomerAccount() throws BankException {
		String customer=null;
		String accountNumber = "1000000000";
		if(accountNumber.matches("[0-9]{10}")) {
		customer= (accountNumber);
		}else {
			throw new BankException("Account number does not exist");
		}
	}
	@Test
	public void rejectAccount() throws BankException {
		String customer=null;
		String reject = "rejected";
		String accountNumber = "1000000001";
		if(reject != null && accountNumber.matches("[0-9]{10}")) {
		customer=(reject);
		}else {
			throw new BankException("Account has been rejected already ");
		}
	}
@Test
	public void approveAccount() throws BankException {
		String approve = "approved";
		String accountNumber = "100000000";
		String customer=null;
		if(approve != null && accountNumber.matches("[0-9]{9}")) {
		customer=(approve);
		}else {
			throw new BankException("Account has been approved already ");
		}
	}
}
