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

	final static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) throws BusinessException {

		Scanner sc = new Scanner(System.in);
		DAOImp dao = new DAOImp(); // Data Access Object
		Service service = new Service(); // Service Object
		List<String> transactionList = new ArrayList<>();

		service.displayWelcome();

		outerLoop:
		while (true) {
			service.displayMainPortal();
			String selection = sc.nextLine();
			switch (selection) {
				case ("1"): // REGISTER
					Account dummyAcc1 = new Account(); // dummy account to construct and send to DB

					log.info("Create a username (case-sensitive): ");
					String username = sc.nextLine();
					while (!service.isValidUsername(username)) {
						username = sc.nextLine();
					}
					dummyAcc1.setUsername(username);

					log.info("Create a password (case-sensitive): ");
					String password = sc.nextLine();
					while (!service.isValidPassword(password)) {
						password = sc.nextLine();
					}
					dummyAcc1.setPassword(password);

					log.info("Enter first and last name: ");
					String name = sc.nextLine();
					dummyAcc1.setName(name);

					log.info("Enter an initial deposit amount: ");
					String initInpAmt = sc.nextLine();
					while (!service.isValidAmount(initInpAmt)) {
						initInpAmt = sc.nextLine();
					}
					double initDepAmt = Double.parseDouble(initInpAmt);
					dummyAcc1.setBalance(initDepAmt);
					transactionList.add(String.format("$" + initInpAmt + " was deposited into " + dummyAcc1.getUsername() + " on %1$tD %1$tT", new Date()));

					dummyAcc1 = new Account(username, password, name, initDepAmt, "customer"); // Creation of customer accounts only!
					dao.createAccount(dummyAcc1);
					continue outerLoop;
				case ("2"): // LOGIN
					log.info("Enter username (case-sensitive): ");
					String inputUsername = sc.nextLine();
					log.info("Enter password: ");
					String inputPassword = sc.nextLine();

					Account dummyLoginAcc = dao.getAccount(inputUsername);
					String dbUsername = dummyLoginAcc.getUsername();
					if (inputUsername.equals(dbUsername)) {
						String dbPassword = dummyLoginAcc.getPassword();
						if (inputPassword.equals(dbPassword)) {
							while (true) {
								service.displayCustEmpPortal();
								selection = sc.nextLine();
								switch (selection) {
									case ("1"): // VIEW ACCOUNT
										log.info(dao.getAccount(inputUsername));
										break;
									case ("2"): // DEPOSIT
										log.info("Enter a deposit amount: ");
										String inputAmount = sc.nextLine();

//										dao.deposit(inputUsername, inputAmount); // Throws StackOverflowError

										while (!service.isValidAmount(inputAmount)) {
											inputAmount = sc.nextLine();
										}
										double depositAmount = Double.parseDouble(inputAmount);

										Account dummyAcc2 = dao.getAccount(inputUsername);
										double dummyBal2 = dummyAcc2.getBalance();
										dummyBal2 += depositAmount;
										dummyAcc2.setBalance(dummyBal2);
										dao.updateAccount(dummyAcc2);
										transactionList.add(String.format("$" + depositAmount + " was deposited into " + dummyAcc2.getUsername() + " on %1$tD %1$tT", new Date()));
										log.info("Balance: $" + dummyAcc2.getBalance());
										break;
									case ("3"): // WITHDRAW
										log.info("Enter a withdrawal amount: ");
										String inputAmount2 = sc.nextLine();

//										dao.withdraw(inputUsername, inputAmount2); // Throws StackOverflowError

										while (!service.isValidAmount(inputAmount2)) {
											inputAmount2 = sc.nextLine();
										}
										double withdrawAmount = Double.parseDouble(inputAmount2);

										Account dummyAcc3 = dao.getAccount(inputUsername);
										double dummyBal3 = dummyAcc3.getBalance();
										if ((dummyBal3 - withdrawAmount) < 0) {
											log.error("Cannot withdraw more than $" + dummyBal3 + ".");
										} else {
											dummyBal3 -= withdrawAmount;
										}
										dummyAcc3.setBalance(dummyBal3);
										dao.updateAccount(dummyAcc3);
										transactionList.add(String.format("$" + withdrawAmount + " was withdrawn from " + dummyAcc3.getUsername() + " on %1$tD %1$tT", new Date()));
										log.info("Balance: $" + dummyAcc3.getBalance());
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
											log.error("Cannot withdraw more than $" + payerBalance + ".");
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
											log.info("YOU ARE NOT AN EMPLOYEE!");
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
											log.info("YOU ARE NOT AN EMPLOYEE!");
										}
										break;
									case ("7"): // LOG OUT
										service.displayLogOut();
										continue outerLoop;
									default:
										log.info("Select a valid option between 1-7.\n");
								}
							}
						} else {
							log.info("Incorrect username or password.\n");
						}
					} else {
						log.info("Incorrect username or password.\n");
					}
					break;
				case ("3"): // EXIT
					service.displayGoodbye();
					System.exit(0);
					break;
				default:
					log.info("Select a valid option between 1-3.\n");
			}
		}
	}
}
