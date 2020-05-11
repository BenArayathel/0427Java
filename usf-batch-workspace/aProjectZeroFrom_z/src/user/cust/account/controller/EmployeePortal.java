package user.cust.account.controller;

import java.util.Scanner;

import bank.transaction.dao.TransactionDaoImpl;
import log.Log;

public class EmployeePortal {
	
	public static Scanner scanner = new Scanner(System.in);
	TransactionDaoImpl tDao = new TransactionDaoImpl();

	public void employeePortal() {
		
		Log.logger("Employee Options");
		


		
		Log.logger("1 - approve or reject an account");
		Log.logger("2 - view all transactions");
		Log.logger("3 - view Customer's transactions");
		Log.logger("4 - Quit");
		//System.out.println("Apply for Another Account");


		if (scanner.hasNext()) {

			int nav = Integer.parseInt(scanner.nextLine());
			
			if (nav == 1) {

				// approve acct
				EmpApprovals e = new EmpApprovals();
				e.empApprovals();
			}
			
			if (nav == 2){
				tDao.viewAllTransactions();
				employeePortal(); // recursion ?
			}
			
			if (nav == 3){
				EmpViewCust_transactions evc = new EmpViewCust_transactions();
				evc.empViewCustRecords();
			}
			
			if (nav == 4){
				System.exit(0);
			}
			
		}
	}

}
