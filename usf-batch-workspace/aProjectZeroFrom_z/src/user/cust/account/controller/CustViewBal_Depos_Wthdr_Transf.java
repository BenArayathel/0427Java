package user.cust.account.controller;

import java.util.Scanner;

import bank.transaction.dao.BankDaoImpl;
import log.Log;
import user.cust.account.models.User;

public class CustViewBal_Depos_Wthdr_Transf {
	
	public static Scanner scanner = new Scanner(System.in);
	CustOptionsDirectory cd = new CustOptionsDirectory();
	BankDaoImpl b = new BankDaoImpl();
	
	void viewBalance(User user) {
		
		if (user.getA_access() == 1) {
			
			Log.logger("Your Balance " + user.getBalance());
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
				user.setBalance(deposit += user.getBalance());
				b.updateBalance(user);
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
				
				if ((user.getBalance() - withdraw) > 0) {
					user.setBalance(user.getBalance() - withdraw);
					b.updateBalance(user);
					
				} else {
					Log.logger("Invalid transaction");
					Log.logger("Withdraw would put balance below minimum.");
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
			
			Log.logger("Coming soon..");
			
//			Log.logger("Enter Withdraw Amount:");
//			if (scanner.hasNext()) {
//				double withdraw = Double.parseDouble(scanner.nextLine());
//				
//				if ((user.getBalance() - withdraw) > 0) {
//					user.setBalance(user.getBalance() - withdraw);
//				} else {
//					Log.logger("Invalid transaction");
//					Log.logger("Withdraw would put balance below minimum.");
//				}
//				
//			}
			cd.select(user);
			
		} else {
			Log.logger("Sorry you do not have access");
			cd.select(user);
		}
		
	}

}
