package user.cust.account.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import bank.transaction.dao.BankDaoImpl;
import bank.transaction.dao.TransactionDaoImpl;
import log.Log;
import oracle.sql.DATE;
import user.cust.account.models.Account;
import user.cust.account.models.Customer;
import user.cust.account.models.Transaction;
import user.cust.account.models.User;

public class CustViewBal_Depos_Wthdr_Transf {
	
	public static Scanner scanner = new Scanner(System.in);
	CustOptionsDirectory cd = new CustOptionsDirectory();
	BankDaoImpl b = new BankDaoImpl();
	private static double transferFunds;
	Transaction t = new Transaction();
	TransactionDaoImpl tDao = new TransactionDaoImpl();
	
	Customer c = new Customer();
	Account a = new Account();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	Date date = new Date();  
    //System.out.println(formatter.format(date));
	
	void viewBalance(User user) {
		
		if (user.getA_access() == 1) {
			
			Log.logger("Your Balance " + user.getBalance());
			//c.setUser(user);
			t.setUser_id(user.getUser_id());
			t.setDate(formatter.format(date).toString());
			t.setDescription("Balance Inquery");
			t.setTransactionValue(0.00);
			t.setDestination_id("Balance Inquery");
			tDao.createTransaction(user, t.toString());
			cd.select(user);
			
		} else {
			Log.logger("Sorry you do not have access");
			cd.select(user);
		}
		
	}
	
	void deposit(User user) {
		
		if (user.getA_access() == 1) {
			
			Log.logger("Enter Deposit Amount:");
			if (scanner.hasNext()) {
				
				double deposit = Double.parseDouble(scanner.nextLine());
				
				if (deposit > 0) {
					
					t.setTransactionValue(deposit); // record deposit before adding to it
					//double deposit = Double.parseDouble(scanner.nextLine());
					user.setBalance(deposit += user.getBalance());
					b.updateBalance(user);	// database call
					t.setUser_id(user.getUser_id());
					t.setDate(formatter.format(date).toString());
					t.setDescription("DEPOSIT");
					
					t.setDestination_id("self");
					tDao.createTransaction(user, t.toString());
					
				} else {
					Log.logger("Cannot be a negative amout or zero");
				}

			}
			
			cd.select(user);
			
		} else {
			Log.logger("Sorry you do not have access");
			cd.select(user);
		}
		
	}
	
	void withdraw(User user) {
		
		if (user.getA_access() == 1) {
			
			Log.logger("Enter Withdraw Amount:");
			if (scanner.hasNext()) {
				double withdraw = Double.parseDouble(scanner.nextLine());
				
				if (withdraw > 0) {
					
					if ((user.getBalance() - withdraw) > 0) {
						user.setBalance(user.getBalance() - withdraw);
						b.updateBalance(user);
						t.setUser_id(user.getUser_id());
						t.setDate(formatter.format(date).toString());
						t.setDescription("WITHDRAW");
						t.setTransactionValue(withdraw);
						t.setDestination_id("self");
						tDao.createTransaction(user, t.toString());
						
					} else {
						Log.logger("Invalid transaction");
						Log.logger("Withdraw would put balance below minimum.");
					}
					
				} else {
					Log.logger("Cannot be zero or negative amount");
				}
				
			}
			cd.select(user);
			
		} else {
			Log.logger("Sorry you do not have access");
			cd.select(user);
		}
		
	}
	
	void transfer(User user) {
		
		if (user.getA_access() == 1) {
			
			Log.logger("Under Construction..");
			
			if (transferHelperwithdraw(user)) {
				
				BankDaoImpl b = new BankDaoImpl();
				List<User> transferRecipient = new ArrayList<>();
				// getAllUsers_needingAuth()
				//usersNeedApproval = b.getAllUsers();
				transferRecipient = b.getAllUsers_withAuth();

				Log.logger(transferRecipient.size() + " Customer(s):");
				Log.logger("Select -which- Customer to Transfer to:\n");


				for (User u : transferRecipient) {
					Log.logger("("+(transferRecipient.indexOf(u)+1)+")"  + u.toString());
				}
				

				
				Log.logger("\n1 - if you would like to Tansfer Funds");
				Log.logger("0 - to Quit");
				
				
				//EmployeePortal e1 = new EmployeePortal();

				if (scanner.hasNext()) {

					int input = Integer.parseInt(scanner.nextLine());

					if (input == 1) {

						// approve acct
						
						Log.logger("Select customer from list:");
						Log.logger("() <- approve: ie: 1");
						Log.logger("0 - Quit - back to Employee Directory");

						
						if (scanner.hasNext()) {
							
							int nav = Integer.parseInt(scanner.nextLine());
							
							
							if (nav != 0) {
								Log.logger("Congratulations ! you Transfered Money to:");
								Log.logger("this: " + transferRecipient.get(nav-1).toString());
								
								// send user, funds, recipient
								System.out.println("service calling the back");
								
								b.update_transfer(user, transferFunds, transferRecipient.get(nav-1));
								
								
								//b.employeeRejectOrApprove_customerApplicationForAccount(transferRecipient.get(nav-1));
								cd.select(user);
							}
							
							if (nav == 0) {
								
								cd.select(user);
							}
						}
					}
					if (input == 0) {
						//EmployeePortal e2 = new EmployeePortal();
						cd.select(user);
					}

				



				}
				
			} else {
				// might not need else here
			}
			


			

			cd.select(user);
			
		} else {
			Log.logger("Sorry you do not have access");
			cd.select(user);
		}
		
	}
	
	/**
	 * 
	 * @param user
	 * @return return boolean: if user has available funds to transfer
	 */
	boolean transferHelperwithdraw(User user) {
		
		if (user.getA_access() == 1) {
			
			Log.logger("Enter Transfer Amount:");
			if (scanner.hasNext()) {
				transferFunds = Double.parseDouble(scanner.nextLine());
				
				if (transferFunds > 0) {
					
					if ((user.getBalance() - transferFunds) > 0) {
						
						return true;
//						user.setBalance(user.getBalance() - withdraw);
//						b.updateBalance(user);
						
					} else {
						Log.logger("Invalid transaction");
						Log.logger("Transaction would put balance below minimum.");
						return false;
					}
					
				} else {
					Log.logger("Cannot be zero or negative amount");
					return false;
				}
				
			}
			// so I really want this navigation here ?? ?? ??
			cd.select(user);
			
		} else {
			Log.logger("Sorry you do not have access");
			cd.select(user);
		}
		return false;
	}

}
