package com.bhank.menu;

import java.util.List;
import java.util.Scanner;

import com.bhank.exception.BusinessException;
import com.bhank.main.Main;
import com.bhank.model.Account;
import com.bhank.model.Customer;
import com.bhank.model.Employee;
import com.bhank.service.impl.AccountServiceImpl;
import com.bhank.service.impl.CustomerServiceImpl;
import com.bhank.service.impl.EmployeeServiceImpl;

public class Menu {

	CustomerServiceImpl customerService = new CustomerServiceImpl();
	EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
	AccountServiceImpl accountService = new AccountServiceImpl();
	Scanner s = new Scanner(System.in);

	public void mainMenu() {
		Main.logger.info("Bhank main menu entered");
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
				Main.logger.info("Bhank application exited");
				System.out.println("Thank you for using Bhank....");
				break;
			case 5:
				Employee employee = new Employee();
				System.out.println("Employee Registration");
				System.out.println("---------------------");
				System.out.println("Enter name");
				employee.setName(s.nextLine());
				System.out.println("Enter password");
				employee.setPassword(s.nextLine());
				System.out.println("Enter date of birth in dd.MM.yyyy format only");
				try {
					employee.setDob(CustomerServiceImpl.isValidDob(s.nextLine()));
					employee = employeeService.createEmployee(employee);
					if (employee.getId() != null) {
						System.out.println("employee registered");
						System.out.println(employee);
					}
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
				}
			default:
				Main.logger.error("Invalid option \"" + ch + "\" selected in main menu");
				System.out.println("Enter (1-4)");
				break;
			}
		} while (ch != 4);
	}

	private void employeeLoginMenu() {
		Main.logger.info("Employee Login menu entered");
		Employee employee = new Employee();
		System.out.println("Employee login");
		System.out.println("--------------");
		System.out.println("Enter employee name");
		String employeeName = s.nextLine();
		System.out.println("Enter employee password");
		String employeePassword = s.nextLine();
		try {
			employee = employeeService.selectEmployeeByNameAndPassword(employeeName, employeePassword);
		} catch (BusinessException e) {
			Main.logger.info("Employee service failed to select employee by name \"" + employeeName
					+ "\" and password \"" + employeePassword + "\"");
			System.out.println(e.getMessage());
		}
		if (employee.getName() != null) {
			employeeMenu(employee);
		} else {
			System.out.println("Login Error");
		}
	}

	private void employeeMenu(Employee employee) {
		Main.logger.info("Emplpoyee menu entered");
		int ch = 0;
		do {
			System.out.println("Employee Menu");
			System.out.println("Logged in as: " + employee.getName());
			System.out.println("-------------");
			System.out.println("1)View pending accounts");
			System.out.println("2)View a customer's accounts");
			System.out.println("3)Approve a customer's account");
			System.out.println("4)Decline a customer's account");
			System.out.println("5)View log of transactions");
			System.out.println("6)Return to main menu");
			ch = Integer.parseInt(s.nextLine());
			switch (ch) {
			case 1:
			{
				List<Account> pendingAccountsList = null;
				try {
					pendingAccountsList = accountService.selectAllPendingAccounts();
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				for(Account account: pendingAccountsList) {
					System.out.println(account);
				}
				break;
			}
			case 2:
			{
				List<Account> customerAccounts = null;
				System.out.println("Enter custommer id");
				String customerId = s.nextLine();
				try {
					customerAccounts = accountService.selectAllAccountsByCustomer(customerId);
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
				}
				if(customerAccounts != null) {
					for(Account account:customerAccounts) {
						System.out.println(account);
					} 
				} else {
					System.out.println("Couldn't view accounts of customoer with id \""+customerId+"\"");
				}
				break;
			}
			case 3:
			{
				Account account = null;
				System.out.println("Enter account id of account to approve");
				String accountId = s.nextLine();
				try {
					account = accountService.selectAccountById(accountId);
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
				}
				try {
					account = accountService.approveAccount(account);
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 4:
			{
				Account account = null;
				System.out.println("Enter account id of account to decline");
				String accountId = s.nextLine();
				try {
					account = accountService.selectAccountById(accountId);
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
				}
				try {
					account = accountService.declineAccount(account);
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 5:
				break;
			case 6:
				Main.logger.info("Exiting employee menu");
				System.out.println("Exiting employee menu...");
				System.out.println("Returning to main menu");
				return;
			default:
				Main.logger.error("Invalid option \"" + ch + "\" selected in employee menu");
				System.out.println("Enter 1-6");
			}
		} while (ch != 6);
	}

	private void customerRegistrationMenu() {
		Main.logger.info("Customer Registration menu entered");
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
			if (customer.getId() != null) {
				System.out.println("Customer registered");
				System.out.println(customer);
			}
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		return;
	}

	private void customerLoginMenu() {
		Main.logger.info("Customer Login menu entered");
		Customer customer = new Customer();
		System.out.println("Customer login");
		System.out.println("--------------");
		System.out.println("Enter customer name");
		String customerName = s.nextLine();
		System.out.println("Enter customer password");
		String customerPassword = s.nextLine();
		try {
			customer = customerService.selectCustomerByNameAndPassword(customerName, customerPassword);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		if (customer.getName() != null) {
			customerMenu(customer);
		} else {
			System.out.println("Login Error");
		}
	}

	private void customerMenu(Customer customer) {
		Main.logger.info("Customer Menu entered");
		int ch = 0;
		do {
			System.out.println("Customer Menu");
			System.out.println("Logged in as: " + customer.getName());
			System.out.println("-------------");
			System.out.println("1)Apply for new account");
			System.out.println("2)View balance of an account");
			System.out.println("3)Withdrawal");
			System.out.println("4)Deposit");
			System.out.println("5)Post money transfer");
			System.out.println("6)Accept money transfer");
			System.out.println("7)Return to main menu");
			ch = Integer.parseInt(s.nextLine());
			switch (ch) {
			case 1: {
				Main.logger.info("Customer apply for accunt menu entered");
				System.out.println("Apply for account");
				System.out.println("-----------------");
				System.out.println("Enter starting balance");
				double startingBalance = Double.parseDouble(s.nextLine());
				Account account = new Account(startingBalance);
				account.setCustomerId(customer.getId());
				try {
					accountService.createAccount(account);
					Main.logger.info("Successfully created customer account");
				} catch (BusinessException e) {
					Main.logger.error("Customer account creation was unsuccessful");
					System.out.println(e.getMessage());
				}
				System.out.println(account);
				break;
			}
			case 2: {
				Main.logger.info("Customer view account balance menu entered");
				Account account = null;
				System.out.println("View account balance");
				System.out.println("--------------------");
				System.out.println("Enter account id");
				String id = s.nextLine();
				try {
					account = accountService.selectAccountById(id);
					Main.logger.info("Account service selected account by id: " + id);
				} catch (BusinessException e) {
					Main.logger.error("Couldn't select account with id: " + id);
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				if (account.isApproved()) {
					System.out.println(account);
				} else if (account.isPending()) {
					System.out.println("Account is pending review");
				} else if (!account.isApproved()) {
					System.out.println("Account has been declined");
				}
				break;
			}
			case 3: {
				Main.logger.info("Customer withdrawal menu entered");
				Account account = null;
				System.out.println("Withdrawal");
				System.out.println("----------");
				System.out.println("Enter account id of account to withdraw from");
				String id = s.nextLine();
				System.out.println("Enter amount to withdraw");
				double amount = Double.parseDouble(s.nextLine());
				try {
					account = accountService.withdraw(id, amount);
					System.out.println("Account updated");
					System.out.println(accountService.selectAccountById(account.getId()));
					Main.logger.info("Account service successfully withdrew " + amount + " from account with id " + id);
				} catch (BusinessException e) {
					Main.logger.error("Account service failed to withdraw " + amount + " from account with id: " + id);
					System.out.println(e.getMessage());
				}
				break;
			}
			case 4: {
				Main.logger.info("Customer deposit menu entered");
				Account account = null;
				System.out.println("Deposit");
				System.out.println("-------");
				System.out.println("Enter account id of account to deposit into");
				String id = s.nextLine();
				System.out.println("Enter amount to deposit");
				double amount = Double.parseDouble(s.nextLine());
				try {
					account = accountService.deposit(id, amount);
					System.out.println("Account updated");
					System.out.println(accountService.selectAccountById(account.getId()));
					Main.logger.info("Account service successfully deposited " + amount + " into account with id " + id);
				} catch (BusinessException e) {
					Main.logger.error("Account service failed to deposit " + amount + "into account with id " + id);
					System.out.println(e.getMessage());
				}
				break;
			}
			case 5: {
				Main.logger.info("Customer post money transfer menu entered");
				System.out.println("Post Money Transfer");
				System.out.println("-------------------");
				System.out.println("Enter account to transfer money to");
				String toAccountId = s.nextLine();
				System.out.println("Enter amount to transfer");
				double transferAmount = Double.parseDouble(s.nextLine());

				break;
			}
			case 6:
				break;
			case 7:
				Main.logger.info("Exiting customer menu");
				System.out.println("Exiting customer menu...");
				System.out.println("Returning to main menu");
				return;
			default:
				Main.logger.error("Invalid option: \"" + ch + "\" selected in customer menu");
				System.out.println("Enter 1-7");
			}
		} while (ch != 7);
		return;
	}
}
