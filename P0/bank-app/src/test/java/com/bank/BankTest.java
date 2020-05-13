package com.bank;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class BankTest {

	private Init init;
	
	public BankTest() {
		init = new Init();
	}

	@Test
	public void coustomerTest() {
		System.out.println("Running Customer Test ..........");
		try {
			Customer testCust = init.getCustomer(15000);
			
			String firstName = testCust.getFirstNme();
			String lastName = testCust.getLastName();
			int accountNumber = testCust.getAccountNumber();
			boolean isInGoodStanding = testCust.isInGoodStanding();
			String occupation = testCust.getOccupation();
			double annualIncome = testCust.getAnnualIncome();
			boolean married = testCust.isMarried();
			boolean ownsHome = testCust.isOwnsHome();
			int ssn = testCust.getSsn();
			
			assertEquals("Test", firstName);
			assertEquals("Customer", lastName);
			assertEquals(15000, accountNumber);
			assertEquals(true, isInGoodStanding);
			assertEquals("Software Developer", occupation);
			assertEquals(150000, annualIncome, 0.00);
			assertEquals(true, married);
			assertEquals(false, ownsHome);
			assertEquals(555667777, ssn);
			
		} catch (SQLException e) {
			System.out.println("There was an error, contact system administrator");
		}
		System.out.println("Customer Test Finished");
	}
	
	@Test
	public void employeeTest() {
		System.out.println("Running Employee Test ..........");
		try {
			Employee testEmployee = init.getEmployee(123456);
			
			int idNum = testEmployee.getIdNum();
			int empPin = testEmployee.getEmpPin();
			String position = testEmployee.getPosition();
			
			assertEquals(123456, idNum);
			assertEquals(1234, empPin);
			assertEquals("Account Manager", position);
		} catch (SQLException e) {
			System.out.println("There was an error, contact system administrator");
		}
		System.out.println("Employee Test Finished");
	}
	
	@Test
	public void accountTest() {
		System.out.println("Running Account  Test ..........");
		try {
			Account testAccount = init.getAccount(15000);
			
			int acctNum = testAccount.getAccountNumber();
			int pin = testAccount.getPinNumber();
			double savings = testAccount.getSavingsAmount();
			double checking = testAccount.getCheckingAmount();
			
			assertEquals(15000, acctNum);
			assertEquals(1234, pin);
			assertEquals(100.00, savings, 0.00);
			assertEquals(100.00, checking, 0.00);
			
		} catch (SQLException e) {
			System.out.println("There was an error, contact system administrator");
		}
		System.out.println("Account Test Finished");
	}
}
