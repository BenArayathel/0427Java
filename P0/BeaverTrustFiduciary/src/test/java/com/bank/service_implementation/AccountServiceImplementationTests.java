package com.bank.service_implementation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bank.tools.BankException;

public class AccountServiceImplementationTests {
	AccountServiceImplementation asi = new AccountServiceImplementation();

	@Test
	public void testAmountEntered() throws BankException {
		String testAmount1 = "1.12";				
		assert(asi.validTransactionFormat(testAmount1));
		
		String testAmount2 = "1.12.03";				
		assert(!asi.validTransactionFormat(testAmount2));
		
		String testAmount3 = "10";				
		assert(asi.validTransactionFormat(testAmount3));
	}
	
	
	
	

}
