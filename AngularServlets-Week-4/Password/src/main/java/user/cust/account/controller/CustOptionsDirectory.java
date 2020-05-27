package user.cust.account.controller;

import java.util.Scanner;

import log.Log;
import user.cust.account.models.User;

public class CustOptionsDirectory {
	
	public static Scanner scanner = new Scanner(System.in);
	

	public void select(User user) {
		
		Log.logger("\nWelcome Customer");
		Log.logger("Select Customer Options");
		Log.logger("1 - Apply for Account");
		Log.logger("2 - View Balance");
		Log.logger("3 - Deposit");
		Log.logger("4 - Withdraw");
		Log.logger("5 - Transfer");
		Log.logger("6 - Employee Portal");
		Log.logger("7 - Quit");


		if (scanner.hasNext()) {

			int nav = Integer.parseInt(scanner.nextLine());
			
			if (nav == 1) {
			CustApplyForAcct c = new CustApplyForAcct();
			c.acctForm(user);
			}
			
			if (nav == 2){
				CustViewBal_Depos_Wthdr_Transf c = new CustViewBal_Depos_Wthdr_Transf();
				c.viewBalance(user);
			}
			
			if (nav == 3){
				CustViewBal_Depos_Wthdr_Transf c = new CustViewBal_Depos_Wthdr_Transf();
				//c.deposit(user);
			}
			
			if (nav == 4){
				CustViewBal_Depos_Wthdr_Transf c = new CustViewBal_Depos_Wthdr_Transf();
				//c.withdraw(user);
			}
			
			if (nav == 5){
				CustViewBal_Depos_Wthdr_Transf c = new CustViewBal_Depos_Wthdr_Transf();
				c.transfer(user);
			}
			
			if (nav == 6) {
				Emp_Login e = new Emp_Login();
				e.employeeLogin();
			}
//				
			if (nav == 7){
				System.exit(0);
			}
			
		}
	}

}
