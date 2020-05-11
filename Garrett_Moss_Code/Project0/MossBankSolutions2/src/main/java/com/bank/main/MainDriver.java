package com.bank.main;

import java.util.Scanner;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.bank.exception.BankException;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.service.CustomerService;
import com.bank.service.EmployeeService;
import com.bank.service.impl.CustomerServiceImpl;
import com.bank.service.impl.EmployeeServiceImpl;

public class MainDriver {
	
	final static Logger logger = Logger.getLogger(MainDriver.class);

	public static void main(String[] args) {
		logger.setLevel(Level.INFO);
		Scanner scanner = new Scanner(System.in);
		logger.info("Welcome to Moss Bank Solutions");
		logger.info("------------------------------");
		CustomerService service = new CustomerServiceImpl();
		EmployeeService employeeService = new EmployeeServiceImpl();
		int choice = 0;
		do {
			logger.info("Login");
			logger.info("Select an option below");
			logger.info("---------------------------");
			logger.info("1) Are you a Customer?");
			logger.info("2) Are you an Employee");
			logger.info("3) Exit");
			try {
				choice=Integer.parseInt(scanner.nextLine());
				
			} catch (NumberFormatException e) {
			}
			switch (choice) {
			case 1:
			int choice2 = 0;	
				do {
					
					logger.info("Welcome to the Customer portal!");
					logger.info("-------------------------------");
					logger.info("1) Existing Customer");
					logger.info("2) New Customer");
					logger.info("3) Exit");
				try {
					choice2=Integer.parseInt(scanner.nextLine());
				} catch (NumberFormatException e) {

				}
				switch (choice2) {
				case 1:
				int choice3 = 0;
				logger.info("Please Login");
				Customer customer = new Customer();
				logger.info("Enter Username");
				String username=scanner.nextLine();
				logger.info("Enter Password");
				String password=scanner.nextLine();
				try {
					Customer customer1=service.loginVerification(username, password);
					if(customer1!=null) {
						logger.info("Login Verified");
					}
				}catch (BankException e) {
					logger.error(e.getMessage());
				}
					do {
						logger.info("Welcome back!");
						logger.info("-------------------------------");
						logger.info("1) View Balance");
						logger.info("2) Make Withdrawl");
						logger.info("3) Make Deposit");
						logger.info("4) Make Transfer");
						logger.info("5) Accept Transfer");
						logger.info("6) Apply for new Account");
						logger.info("7) Exit");
						try {
							 choice3=Integer.parseInt(scanner.nextLine());
						} catch (NumberFormatException e) {

						}
						switch (choice3) {
						case 1:
							logger.info("Enter AccountNumber");
							String accountNumber=scanner.nextLine();
							try {
								Customer customer1=service.viewBalance(accountNumber);
								if(customer1!=null) {
									logger.info("Here is your balance: "+customer1);
								}
							}catch (BankException e) {
								logger.error(e.getMessage());
							}
							
							break;
						case 2:
							logger.info("Enter accountNumber");
							String accountNumber1=scanner.nextLine();
							logger.info("Enter Withdrawl Amount to two decimal places");
							String withdraw=scanner.nextLine();
							logger.info("Enter Account Balance to two decimal places");
							String accountBalance=scanner.nextLine();
							try {
								Customer customer1=service.withdraw(accountNumber1, withdraw, accountBalance);
								if(customer1==null) {
									logger.info("You have withdrawn: $"+withdraw);
								}
							} catch (BankException e) {
								logger.error(e.getMessage());
							}
							break;
						case 3:
							logger.info("Enter Deposit Amount to two decimal places");
							String deposit=scanner.nextLine();
							logger.info("Enter accountNumber");
							String accountNumber3=scanner.nextLine();
							try {
								Customer customer1=service.deposit(deposit, accountNumber3);
								if(customer1==null) {
									logger.info("You have deposited: $"+deposit);
								}
							} catch (BankException e) {
								logger.error(e.getMessage());
							}
							break;
						case 4:
							logger.info("Enter Your Account Number");
							String accountNumber2=scanner.nextLine();
							logger.info("Enter Transfer Amount to two decimal places");
							String transferAmount=scanner.nextLine();
							logger.info("Enter Account Number the transfer is going to");
							String toAccountNumber=scanner.nextLine();
							try {
								Customer customer1=service.moneyTransfer(accountNumber2, transferAmount, toAccountNumber);
								if(customer1==null) {
									logger.info("You have sucessfully transfered $"+transferAmount + " to :"+toAccountNumber);
								}
							} catch (BankException e) {
								logger.error(e.getMessage());
							}
							break;
						case 5:
							logger.info("Enter Your Account Number");
							String fromAccountNumber=scanner.nextLine();
							logger.info("Enter Transfer Amount to two decimal places");
							String transferAmount1=scanner.nextLine();
							logger.info("Enter Account Number the transfer is coming from");
							String toAccountNumber1=scanner.nextLine();
							try {
								Customer customer1=service.acceptTransfer(fromAccountNumber,transferAmount1, toAccountNumber1);
								if(customer1==null) {
									logger.info("You have accepted a money Transfer of $"+ transferAmount1);
								}
							} catch (BankException e) {
								logger.error(e.getMessage());
							}
							
							break;
						case 6:
							Customer customer1 = new Customer();
							logger.info("Enter Username");
							customer1.setUsername(scanner.nextLine());
							try {
								logger.info("Enter Password");
								customer1.setPassword(scanner.nextLine());
								logger.info("Enter AccountBalance to two decimal places");
								customer1.setAccountBalance(scanner.nextLine());
								customer1 = service.createCustomerAccount(customer1);
								logger.info("New Account Created");
								logger.info(customer1);
							} catch (BankException e) {
								logger.error(e.getMessage());
							}
							
							break;
						case 7:
							logger.info("Thanks for using our App.......");
							System.exit(0);
							break;

						default:
							logger.info("Entered choice should be between(1-7)");
							break;
						}
						choice3=0;
					} while (choice3 !=7);
				case 2:
					int choice4=0;
					do {
						logger.info("Welcome new customer!");
						logger.info("-------------------------------");
						logger.info("1) Create Account");
						logger.info("2) Exit");
						try {
							 choice4=Integer.parseInt(scanner.nextLine());
						} catch (NumberFormatException e) {

						}
						switch (choice4) {
						
						case 1:
							Customer customer1 = new Customer();
							logger.info("Enter Username");
							customer1.setUsername(scanner.nextLine());
							try {
								logger.info("Enter Password");
								customer1.setPassword(scanner.nextLine());
								logger.info("Enter AccountBalance to two decimal places");
								customer1.setAccountBalance(scanner.nextLine());
								customer1 = service.createCustomerAccount(customer1);
								logger.info("Customer Registered");
								logger.info(customer1);
							} catch (BankException e) {
								logger.error(e.getMessage());
							}
							break;
						case 2:
							logger.info("Thanks for using our App.......");
							System.exit(0);
							break;
						
						default:
							logger.info("Entered choice should be between (1-2)");
							break;
						} 
						choice4 = 0;
					} while (choice4 !=2);
				case 3:
					logger.info("Thanks for using our App.......");
					System.exit(0);
					break;

				default:
					logger.info("Entered choice should be between(1-3)");
					break;
				}
				choice2=0;
				} while (choice2 !=3);
				
			case 2:
				int choice5 = 0;	
				do {
					
					logger.info("Welcome to the Employee portal!");
					logger.info("-------------------------------");
					logger.info("1) Sign In");
					logger.info("2) Exit");
				try {
					choice5=Integer.parseInt(scanner.nextLine());
				} catch (NumberFormatException e) {

				}
				switch (choice5) {
				case 1:
					

				int choice6 = 0;
				do {					
					Employee employee = new Employee();
					logger.info("Enter Username");
					String username=scanner.nextLine();
					logger.info("Enter Password");
					String password=scanner.nextLine();
					try {
						Employee employee1=employeeService.loginVerification(username, password);
						if(employee1!=null) {
							logger.info("Login Verified");
						}
					}catch (BankException e) {
						logger.error(e.getMessage());
					}
					logger.info("What would you like to do today");
					logger.info("-------------------------------");
					logger.info("1) Approve Accounts");
					logger.info("2) Reject Accounts");
					logger.info("3) View Customer Account");
					logger.info("4) View Transaction Logs");
					logger.info("5) Exit");
					try {
						choice6=Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {

					}
					switch (choice6) {
					case 1:
						logger.info("Enter the Account Number You would like to approve");
						String accountNumber=scanner.nextLine();
						logger.info("Please Enter 'Approved by and then your EmployeeID'");
						String approve=scanner.nextLine();
						try {
							Customer customer=employeeService.approveAccount(approve, accountNumber);
							if(customer==null) {
								logger.info("You have approved account: "+accountNumber);
							}
						} catch (BankException e) {
							logger.error(e.getMessage());
						}
						
						break;
					case 2:
						logger.info("Enter the Account Number You would like to reject");
						String accountNumber1=scanner.nextLine();
						logger.info("Please Enter 'Rejected by and then your EmployeeID'");
						String reject=scanner.nextLine();
						try {
							Customer customer=employeeService.rejectAccount(reject, accountNumber1);
							if(customer==null) {
								logger.info("You have rejected account: "+accountNumber1);
							}
						} catch (BankException e) {
							logger.error(e.getMessage());
						}
						
						
						break;
					case 3:
						logger.info("Enter Account Number");
						String accountNumber2=scanner.nextLine();
						try {
							Customer customer=employeeService.viewCustomerAccount(accountNumber2);
							if(customer!=null) {
								logger.info("Here is the CustomerAccount: "+customer);
							}
						}catch (BankException e) {
							logger.error(e.getMessage());
						}
		
						break;
					case 4:
						logger.info("Enter Yes");
						String answer=scanner.nextLine();
						try {
							employeeService.viewTransactionLogs(answer);
							logger.info("Here are the Transaction Logs: ");
							
						} catch (BankException e) {
							logger.error(e.getMessage());
						}
		
						break;
					case 5:
						logger.info("Thanks for using our App.......");
						System.exit(0);
						break;

					default:
						logger.info("Entered choice should be between(1-5)");
						break;
					}
						choice6 = 0;
					} while (choice6 !=5);
					
				case 2:
					logger.info("Thanks for using our App.......");
					System.exit(0);
					break;

				default:
					logger.info("Entered choice should be between(1-2)");
					break;
				}
				choice5 =0;
				} while (choice5 !=2);
			
			case 3:
				logger.info("Thanks for using our App.......");
				System.exit(0);
				break;
			default:
				logger.info("Entered choice should be between(1-3)");
				break;
			}
			choice=0;
		} while (choice !=3);
	}

}
