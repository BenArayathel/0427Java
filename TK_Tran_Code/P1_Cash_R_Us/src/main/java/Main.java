import dao.DAOImp;
import exception.BusinessException;
import model.Account;
import org.apache.log4j.Logger;
import service.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/*
	Application (Presentation) Layer
*/
public class Main {

	static final Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) throws BusinessException {

		Scanner sc = new Scanner(System.in);
		DAOImp dao = new DAOImp(); // Data Access Object; used to communicate with DB
		Service service = new Service(); // Service Object, used for first-level validation
		List<String> transactionList = new ArrayList<>();

		service.displayWelcome();

		outerLoop:
		while (true) {
			service.displayMainPortal();

			String selection = sc.nextLine();
			switch (selection) {
				case ("1"): // REGISTER
					Account dummyRegisterAccount = new Account();

					log.info("Create a username (case-sensitive): ");
					String username = sc.nextLine();
					while (!service.isValidUsername(username)) {
						username = sc.nextLine();
					}
					dummyRegisterAccount.setUsername(username);

					log.info("Create a password (case-sensitive): ");
					String password = sc.nextLine();
					while (!service.isValidPassword(password)) {
						password = sc.nextLine();
					}
					dummyRegisterAccount.setPassword(password);

					log.info("Enter first and last name: ");
					String name = sc.nextLine();
					dummyRegisterAccount.setName(name);

					log.info("Enter an initial deposit amount: ");
					String initInputAmount = sc.nextLine(); // accept number as string to validate w/ RegEx
					while (!service.isValidAmount(initInputAmount)) {
						initInputAmount = sc.nextLine();
					}
					double initDepositAmount = Double.parseDouble(initInputAmount); // if deposit input in valid format, parses to double for manipulation
					dummyRegisterAccount.setBalance(initDepositAmount);

					// Constructs dummy account and adds to DB.
					dummyRegisterAccount = new Account(username, password, name, initDepositAmount, "customer"); // only allows creation of customer accounts!
					dao.createAccount(dummyRegisterAccount);

					// Adds transaction to transaction list.
					transactionList.add(String.format("$" + initInputAmount + " was deposited into " + dummyRegisterAccount.getUsername() + " on %1$tD %1$tT", new Date()));
					continue outerLoop;
				case ("2"): // LOGIN
					log.info("Enter username (case-sensitive): ");
					String inputUsername = sc.nextLine();
					log.info("Enter password: ");
					String inputPassword = sc.nextLine();

					Account dummyLoginAccount = dao.getAccount(inputUsername);
					String dbUsername = dummyLoginAccount.getUsername();
					if (inputUsername.equals(dbUsername)) {
						String dbPassword = dummyLoginAccount.getPassword();
						if (inputPassword.equals(dbPassword)) {
							while (true) {
								service.displayLoginPortal();

								selection = sc.nextLine();
								switch (selection) {
									case ("1"): // VIEW ACCOUNT
										log.info(dao.getAccount(inputUsername));
										break;
									case ("2"): // DEPOSIT
										log.info("Enter a deposit amount: ");
										String inputDepositAmount = sc.nextLine();

//										dao.deposit(inputUsername, inputAmount); // Throws StackOverflowError

										while (!service.isValidAmount(inputDepositAmount)) {
											inputDepositAmount = sc.nextLine();
										}
										double depositAmount = Double.parseDouble(inputDepositAmount);

										Account dummyDepositAccount = dao.getAccount(inputUsername);
										double dummyDepositBalance = dummyDepositAccount.getBalance();
										dummyDepositBalance += depositAmount;
										dummyDepositAccount.setBalance(dummyDepositBalance);
										dao.updateAccount(dummyDepositAccount);

										transactionList.add(String.format("$" + depositAmount + " was deposited into " + dummyDepositAccount.getUsername() + " on %1$tD %1$tT", new Date()));
										log.info("Balance: $" + dummyDepositAccount.getBalance());
										break;
									case ("3"): // WITHDRAW
										log.info("Enter a withdrawal amount: ");
										String inputAmount2 = sc.nextLine();

//										dao.withdraw(inputUsername, inputAmount2); // Throws StackOverflowError

										while (!service.isValidAmount(inputAmount2)) {
											inputAmount2 = sc.nextLine();
										}
										double withdrawAmount = Double.parseDouble(inputAmount2);

										Account dummyWithdrawAccount = dao.getAccount(inputUsername);
										double dummyBal3 = dummyWithdrawAccount.getBalance();
										if ((dummyBal3 - withdrawAmount) < 0) {
											log.warn("Cannot withdraw more than $" + dummyBal3 + ".");
										} else {
											dummyBal3 -= withdrawAmount;
										}
										dummyWithdrawAccount.setBalance(dummyBal3);
										dao.updateAccount(dummyWithdrawAccount);

										transactionList.add(String.format("$" + withdrawAmount + " was withdrawn from " + dummyWithdrawAccount.getUsername() + " on %1$tD %1$tT", new Date()));
										log.info("Balance: $" + dummyWithdrawAccount.getBalance());
										break;
									case ("4"): // TRANSFER
										log.info("Enter username of payee: ");
										String payee = sc.nextLine();
										log.info("Enter transfer amount: ");
										String inputTransferAmount = sc.nextLine();
										// Withdrawing from Payer
										Account payerAccount = dao.getAccount(inputUsername); // Fetch payer's account from DB
										double payerBalance = payerAccount.getBalance(); // Extract payer's balance
										while (!service.isValidAmount(inputTransferAmount)) {
											inputTransferAmount = sc.nextLine();
										}
										double transferWithdrawAmount = Double.parseDouble(inputTransferAmount);
										if ((payerBalance - transferWithdrawAmount) < 0) {
											log.warn("Cannot withdraw more than $" + payerBalance + ".");
										} else {
											payerBalance -= transferWithdrawAmount;
										}
										payerAccount.setBalance(payerBalance); // Update payer's balance
										dao.updateAccount(payerAccount); // Sends updated account back to DB
										// Depositing to Payee
										Account payeeAccount = dao.getAccount(payee);
										double payeeBalance = payeeAccount.getBalance();
										while (!service.isValidAmount(inputTransferAmount)) {
											inputTransferAmount = sc.nextLine();
										}
										double transferDepositAmount = Double.parseDouble(inputTransferAmount);
										payeeBalance += transferDepositAmount;
										payeeAccount.setBalance(payeeBalance);
										dao.updateAccount(payeeAccount);
										break;
									case ("5"): // EMPLOYEE: VIEW ALL ACCOUNTS
										Account adminTest1 = dao.getAccount(inputUsername);
										String temp1 = adminTest1.getType();
										if (temp1.equals("admin")) {
											List<Account> accountList = dao.getAllAccounts();
											for (Account a : accountList) {
												log.info(a);
											}
										} else {
											log.warn("YOU ARE NOT AN EMPLOYEE!");
										}
										break;
									case ("6"): // EMPLOYEE: VIEW ALL TRANSACTIONS
										Account adminTest2 = dao.getAccount(inputUsername);
										String temp2 = adminTest2.getType();
										if (temp2.equals("admin")) {
											for (String s : transactionList) {
												log.info(s);
											}
										} else {
											log.warn("YOU ARE NOT AN EMPLOYEE!");
										}
										break;
									case ("7"): // LOG OUT
										service.displayLogOut();
										continue outerLoop;
									default:
										log.warn("Select a valid option between 1-7.\n");
								}
							}
						} else {
							log.warn("Incorrect username or password.\n");
						}
					} else {
						log.warn("Incorrect username or password.\n");
					}
					break;
				case ("3"): // EXIT
					service.displayGoodbye();
					System.exit(0);
					break;
				default:
					log.warn("Select a valid option between 1-3.\n");
			}
		}
	}
}
