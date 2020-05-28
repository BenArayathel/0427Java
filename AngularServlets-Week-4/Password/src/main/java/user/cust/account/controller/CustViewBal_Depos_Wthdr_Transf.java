package user.cust.account.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import bank.transaction.dao.BankDaoImpl;
import bank.transaction.dao.TransactionDaoImpl;
import log.Log;
//import not.used.Account;
//import not.used.Customer;
import oracle.sql.DATE;
import user.cust.account.models.Transaction;
import user.cust.account.models.User;

public class CustViewBal_Depos_Wthdr_Transf {
	
	public static Scanner scanner = new Scanner(System.in);
	CustOptionsDirectory cd = new CustOptionsDirectory();
	BankDaoImpl b = new BankDaoImpl();
	private static double transferFunds;
	Transaction t = new Transaction();
	TransactionDaoImpl tDao = new TransactionDaoImpl();
	
	//Customer c = new Customer();
	//Account a = new Account();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	Date date = new Date(); 
    //System.out.println(formatter.format(date));
	
	void viewBalance(User user) {
		
		if (user.getA_access() == 1) {
			
			Log.logger("\nYour Balance " + user.getBalance());
			//Log.logger("... should be todays date here: " + now.toString());
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
	
	public int deposit(User user, double deposit) {
		
		if (user.getA_access() == 1) {
			
			//Log.logger("Enter Deposit Amount:");
			if (deposit != 0) {										// cannot check for null ?? ????????????????????????????????
				
				//double deposit = Double.parseDouble(scanner.nextLine());
				
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
					return -1;
				}

			}
			
			//cd.select(user);
			return 1;
			
		} else {
			Log.logger("Sorry you do not have access");
			return -1;
			//cd.select(user);
		}
		//return -2;
	}
	
	public int withdraw(User user, double withdraw) {
		
		if (user.getA_access() == 1) {
			
			Log.logger("\nEnter Withdraw Amount:");
			if (withdraw != 0) {
				
				//double withdraw = Double.parseDouble(scanner.nextLine());
				
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
					return -1;
				}
				
			}
			//cd.select(user);
			return 1;
			
		} else {
			Log.logger("Sorry you do not have access");
			//cd.select(user);
			return -1;
		}
		
	}
	
	public int transfer(User user, double amount, String recipientID) {
		
		if (user.getA_access() == 1) {
			
			//Log.logger("Under Construction..");
			
			if (transferHelperwithdraw(user, amount)) {
				
				BankDaoImpl b = new BankDaoImpl();
				List<User> transferRecipient = new ArrayList<>();
				// getAllUsers_needingAuth()
				//usersNeedApproval = b.getAllUsers();
				transferRecipient = b.getAllUsers_withAuth();

				Log.logger("\nListing recognized accounts:");
				Log.logger(transferRecipient.size() + " Total Customer(s):\n");


				for (User u : transferRecipient) {
					Log.logger("("+(transferRecipient.indexOf(u)+1)+")"  + u.toString());
				}
				

				
				Log.logger("\n1 - Confirm to Tansfer Funds");
				Log.logger("0 - Quit");
				
				
				//EmployeePortal e1 = new EmployeePortal();

				if (true) {	// scanner.hasNext()

					//int input = Integer.parseInt(scanner.nextLine());

					if (true) {

						// approve acct
						
						Log.logger("\nenter Number from list:");
						Log.logger("() <- to approve: ie: 1");
						Log.logger("0 - to Quit - back to menu");

						/**
						 * pass a THIRD PARAM in utility store the transfer recipient's ID
						 */
						
						if (true) {	// scanner.hasNext()
							
							//int nav = Integer.parseInt(scanner.nextLine());
							
							
							if (true) {	// nav != 0
								
								User u_with_recipientID = new User();
								u_with_recipientID.setUser_id(recipientID);
								
								
								Log.logger("Congratulations ! you Transfered Money to:");
								//Log.logger("this: " + transferRecipient.get(nav-1).toString());
								Log.logger("this: " + u_with_recipientID.toString());
								
								
								// send user, funds, recipient
								//Log.logger("service calling the back");
								
								//b.update_transfer(user, transferFunds, transferRecipient.get(nav-1));
								b.update_transfer(user, transferFunds, u_with_recipientID);
								
								t.setUser_id(user.getUser_id());
								t.setDate(formatter.format(date).toString());
								t.setDescription("Transfer");
								t.setTransactionValue(transferFunds);
								t.setDestination_id(u_with_recipientID.getUser_id());
								tDao.createTransaction(user, t.toString());
								
								
								//b.employeeRejectOrApprove_customerApplicationForAccount(transferRecipient.get(nav-1));
								//cd.select(user);
								
								return 1;
							}
							
							if (true) {	// nav == 0
								
								//cd.select(user);
							}
						}
					}
					if (true) {	// input == 0
						//EmployeePortal e2 = new EmployeePortal();
						//cd.select(user);
						return 0;
					}

				



				}
				
			} else {
				// might not need else here
			}
			


			
			return 1;
			//cd.select(user);
			
		} else {
			Log.logger("Sorry you do not have access");
			return 0;
			//cd.select(user);
		}
		
	}
	
	/**
	 * 
	 * @param user
	 * @return return boolean: if user has available funds to transfer
	 */
	boolean transferHelperwithdraw(User user, double amount) {
		
		if (user.getA_access() == 1) {
			
			Log.logger("Enter Transfer Amount:");
			if (true) {		// scanner.hasNext()
				//transferFunds = Double.parseDouble(scanner.nextLine());
				transferFunds = amount;
				
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
			//cd.select(user);
			
		} else {
			Log.logger("Sorry you do not have access");
			//cd.select(user);
		}
		return false;
	}

}
