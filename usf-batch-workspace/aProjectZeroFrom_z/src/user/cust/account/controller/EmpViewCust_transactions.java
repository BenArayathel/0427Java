package user.cust.account.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bank.transaction.dao.BankDaoImpl;
import bank.transaction.dao.TransactionDaoImpl;
import log.Log;
import user.cust.account.models.User;

public class EmpViewCust_transactions {

	public static Scanner scanner = new Scanner(System.in);

	public void empViewCustRecords() {

		//System.out.println("here is your for loop");

		

		BankDaoImpl b = new BankDaoImpl();
		TransactionDaoImpl tDao = new TransactionDaoImpl();
		
		List<User> allUsers = new ArrayList<>();
		// getAllUsers_needingAuth()
		allUsers = b.getAllUsers();
		//usersNeedApproval = b.getAllUsers_needingAuth();

		Log.logger(allUsers.size() + " Total Customer(s)");
		Log.logger("Select -which- Customer to View:\n");


		for (User u : allUsers) {
			Log.logger("("+(allUsers.indexOf(u)+1)+")"  + u.toString());
		}
		

		
		Log.logger("\n1 - view records");
		Log.logger("0 - to Quit");
		
		
		EmployeePortal e1 = new EmployeePortal();

		if (scanner.hasNext()) {

			int input = Integer.parseInt(scanner.nextLine());

			if (input == 1) {

				// approve acct
				
				Log.logger("Select Number from list:");
				Log.logger("() <- approve: ie: 1");
				Log.logger("0 - Quit - back to Employee Directory");

				
				if (scanner.hasNext()) {
					
					int nav = Integer.parseInt(scanner.nextLine());
					
					
					if (nav != 0) {
						Log.logger("Records printing:");
						Log.logger("this customers records: " + allUsers.get(nav-1).toString());
						
						tDao.viewCustTransactions(allUsers.get(nav-1));
						//b.employeeRejectOrApprove_customerApplicationForAccount(allUsers.get(nav-1));
						e1.employeePortal();
					}
					
					if (nav == 0) {
						
						e1.employeePortal();
					}
				}
			}
			if (input == 0) {
				//EmployeePortal e2 = new EmployeePortal();
				e1.employeePortal();
			}

		



		}
	}

//	void old_empApprovals() {
//		
//		System.out.println("here is your for loop");
//		
//		
//		int input=0;
//		
//
//
//		BankDaoImpl b = new BankDaoImpl();
//		List<User> usersNeedApproval = new ArrayList<>();
//		usersNeedApproval = b.getAllUsers();
//		
//		Log.logger(usersNeedApproval.size() + " Customer(s) Need Approval");
//		
//		for(User u : usersNeedApproval) {
//			Log.logger("Select from Options:");
//			Log.logger("1 - approve");
//			Log.logger("2 - skip");
//			Log.logger("approve or skip\n" + u.toString());
//			input = Integer.parseInt(scanner.nextLine());
//			if(input == 1) {
//				b.employeeRejectOrApprove_customerApplicationForAccount(u);
//			}
//		}
//
//		// call datatbase check for boolean
//
//		// read total num of approvals pending
//
//		// loop through approvals and stop to approve, skip, or deny the approvals
//
//		
//
//		
//		// System.out.println("Apply for Another Account");
//
//		if (scanner.hasNext()) {
//
//			int nav = Integer.parseInt(scanner.nextLine());
//
//			if (nav == 1) {
//
//				// approve acct
//			}
//
//			if (nav == 2) {
//				System.exit(0);
//			}
//
//			if (nav == 3) {
//				System.exit(0);
//			}
//
//		}
//	}

}
