package com.bhank.menu;

import java.util.Scanner;

import com.bhank.exception.BusinessException;
import com.bhank.main.Main;
import com.bhank.model.Account;
import com.bhank.model.Customer;
import com.bhank.model.Employee;
import com.bhank.service.impl.AccountServiceImpl;
import com.bhank.service.impl.CustomerServiceImpl;
import com.bhank.service.impl.EmployeeServiceImpl;
import org.apache.log4j.Logger;

public class Menu {
	
	CustomerServiceImpl customerService = new CustomerServiceImpl();
	EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
	AccountServiceImpl accountService = new AccountServiceImpl();
	Scanner s = new Scanner(System.in);
	
	public void mainMenu() {
		Main.logger.info("Bhank main menu started");
		System.out.println("Welcome to Bhank!");
		int ch = 0;
		do {
			System.out.println("Please, Log in or apply for an account");
			System.out.println("1)Customer login");
			System.out.println("2)Employee login");
			System.out.println("3)Customer registration");
			System.out.println("4)Exit");
			ch = Integer.parseInt(s.nextLine());
			switch (ch) {
			case 1:
				customerLoginMenu();
				break;
			case 2:
				employeeLoginMenu();
				break;
			case 3:
				customerRegistrationMenu();
				break;
			case 4:
				System.out.println("Thank you for using Bhank....");
				break;
			default:
				System.out.println("Enter (1-3)");
				break;
			}
		} while (ch != 4);
	}

	private void employeeLoginMenu() {
		Employee employee = new Employee();
		System.out.println("Employee login");
		System.out.println("--------------");
		System.out.println("Enter employee name");
		String employeeName = s.nextLine();
		System.out.println("Enter employee password");
		String employeePassword = s.nextLine();
		try {
			employee=employeeService.selectEmployeeByNameAndPassword(employeeName, employeePassword);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		if(employee!=null) {
			employeeMenu(employee);
		}
		System.out.println("exiting customerLoginMenu");		
	}

	private void employeeMenu(Employee employee) {
		// TODO Auto-generated method stub
	}

	private void customerRegistrationMenu() {
		Customer customer = new Customer();
		System.out.println("Customer Registration");
		System.out.println("---------------------");
		System.out.println("Enter name");
		customer.setName(s.nextLine());
		System.out.println("Enter password");
		customer.setPassword(s.nextLine());
		System.out.println("Enter date of birth in dd.MM.yyyy format only");
		try {
			customer.setDob(CustomerServiceImpl.isValidDob(s.nextLine()));
			customer = customerService.createCustomer(customer);
			if(customer.getId()!=null) {
				System.out.println("Customer registered");
				System.out.println(customer);
			}
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		return;
	}

	private void customerLoginMenu() {
		Customer customer = new Customer();
		System.out.println("Customer login");
		System.out.println("--------------");
		System.out.println("Enter customer name");
		String customerName = s.nextLine();
		System.out.println("Enter customer password");
		String customerPassword = s.nextLine();
		try {
			customer=customerService.selectCustomerByNameAndPassword(customerName, customerPassword);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		if(customer!=null) {
			customerMenu(customer);
		}
//		System.out.println("exiting customerLoginMenu");
	}

	private void customerMenu(Customer customer) {
		int ch=0;
		System.out.println(customer);
		do {
			System.out.println("Customer Menu");
			System.out.println("Logged in as: " + customer.getName());
			System.out.println("-------------");
			System.out.println("1)Apply for new account");
			System.out.println("2)View balance of an account");
			System.out.println("3)Withdraw");
			System.out.println("4)Deposit");
			System.out.println("5)Post money transfer");
			System.out.println("6)Accept money transfer");
			System.out.println("7)Return to main menu");
			ch = Integer.parseInt(s.nextLine());
			switch(ch) {
			case 1:
			{
				System.out.println("Apply for account");
				System.out.println("-----------------");
				System.out.println("Enter starting balance");
				double startingBalance = Double.parseDouble(s.nextLine());
				Account account = new Account(startingBalance);
				account.setId(customer.getId());
				try {
					accountService.createAccount(account);
				} catch (BusinessException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				break;
			}
			case 2:
			{
				Account account = null;
				System.out.println("View account balance");
				System.out.println("--------------------");
				System.out.println("Enter account id");
				String id = s.nextLine();
				try {
					account = accountService.selectAccountById(id);
				} catch (BusinessException e) {
					Main.logger.error("Couldn't select account with id: "+id);
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				System.out.println(account);
				break;
			}
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				return;
			default:
				System.out.println("Enter 1-7");
			}
		} while(ch!=7);
		return;
	}
}
