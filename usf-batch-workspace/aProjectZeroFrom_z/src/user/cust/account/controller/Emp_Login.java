package user.cust.account.controller;

import java.util.Scanner;

public class Emp_Login {
	
	public void employeeLogin() {
	
		Scanner scanner = new Scanner(System.in);
		String credential = "987";

		System.out.println("Enter Employee Password: ");

		if (scanner.hasNext()) {

			credential = scanner.nextLine();
			EmployeePortal e = new EmployeePortal();
			e.employeePortal();
		}

	}

}
