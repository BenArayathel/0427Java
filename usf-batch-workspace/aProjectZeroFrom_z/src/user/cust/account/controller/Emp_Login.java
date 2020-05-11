package user.cust.account.controller;

import java.util.Scanner;

import log.Log;

public class Emp_Login {
	
	public void employeeLogin() {
	
		Scanner scanner = new Scanner(System.in);
		String credential = "987";

		Log.logger("Enter Employee Password: ");

		if (scanner.hasNext()) {

			credential = scanner.nextLine();
			EmployeePortal e = new EmployeePortal();
			e.employeePortal();
		}

	}

}
