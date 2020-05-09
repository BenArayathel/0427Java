package user.cust.account.controller;

import java.util.Scanner;

import log.Log;

public class EmployeePortal {
	
	public static Scanner scanner = new Scanner(System.in);

	public void employeePortal() {
		
		Log.logger("Employee Options");
		


		
		Log.logger("1 - approve or reject an account");
		Log.logger("2 - view a log of all transactions: Coming Soon");
		Log.logger("3 - view a customer's bank accounts: Coming Soon");
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
				System.exit(0);
			}
			
			if (nav == 3){
				System.exit(0);
			}
			
			if (nav == 4){
				System.exit(0);
			}
			
		}
	}

}
