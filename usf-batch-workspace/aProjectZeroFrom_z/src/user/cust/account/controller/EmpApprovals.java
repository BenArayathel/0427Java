package user.cust.account.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bank.transaction.dao.BankDaoImpl;
import log.Log;
import user.cust.account.models.User;

public class EmpApprovals {

	public static Scanner scanner = new Scanner(System.in);

	public void empApprovals() {

		System.out.println("here is your for loop");

		

		BankDaoImpl b = new BankDaoImpl();
		List<User> usersNeedApproval = new ArrayList<>();
		usersNeedApproval = b.getAllUsers();

		Log.logger(usersNeedApproval.size() + " Customer(s) Need Approval");
		Log.logger("Select -which- Customer to Approve:\n");


		for (User u : usersNeedApproval) {
			Log.logger("("+(usersNeedApproval.indexOf(u)+1)+")"  + u.toString());
		}
		

		
		Log.logger("\n1 - if you would like to approve");
		Log.logger("0 - to Quit");
		
		
		EmployeePortal e1 = new EmployeePortal();

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
						Log.logger("Congratulations ! you approved !");
						Log.logger("this: " + usersNeedApproval.get(nav-1).toString());
						b.employeeRejectOrApprove_customerApplicationForAccount(usersNeedApproval.get(nav-1));
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

	void old_empApprovals() {
		
		System.out.println("here is your for loop");
		
		
		int input=0;
		


		BankDaoImpl b = new BankDaoImpl();
		List<User> usersNeedApproval = new ArrayList<>();
		usersNeedApproval = b.getAllUsers();
		
		Log.logger(usersNeedApproval.size() + " Customer(s) Need Approval");
		
		for(User u : usersNeedApproval) {
			Log.logger("Select from Options:");
			Log.logger("1 - approve");
			Log.logger("2 - skip");
			Log.logger("approve or skip\n" + u.toString());
			input = Integer.parseInt(scanner.nextLine());
			if(input == 1) {
				b.employeeRejectOrApprove_customerApplicationForAccount(u);
			}
		}

		// call datatbase check for boolean

		// read total num of approvals pending

		// loop through approvals and stop to approve, skip, or deny the approvals

		

		
		// System.out.println("Apply for Another Account");

		if (scanner.hasNext()) {

			int nav = Integer.parseInt(scanner.nextLine());

			if (nav == 1) {

				// approve acct
			}

			if (nav == 2) {
				System.exit(0);
			}

			if (nav == 3) {
				System.exit(0);
			}

		}
	}

}
