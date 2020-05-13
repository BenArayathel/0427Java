package com.RevBank;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SelectionScreen implements Selection{

	@Override
	public Selection doSelection(Scanner scan, Application app) {
		
		System.out.println(" ******** " +app.getTitle()+ "********");
		System.out.println("PLEASE MAKE A SELECTION: \n" +
		
		"1: Apply for Loan\n2: Customer Login \n3: Employee Login\n4: Open Account\n5: Bank Deposits\n6: Bank Withdrawal ");
		
		
		Selection select;
		try {
			select = makeSel(scan);
			
		}
		catch(InputMismatchException e) {
			System.out.println(" Input Error Make Select 1 to 5");
			scan.next();
			select = new  SelectionScreen();
		}
		return select;
	}
			// next Selection
	private Selection makeSel(Scanner scan) {
		int y = scan.nextInt();
		Selection nextSel = null;
		
		switch(y) {
		
		case 1:			/// Where customer chooses Transaction type
				nextSel = new ApplyLoan();
				break;
		case 2:
			
			    nextSel = new CustomerLogin();
			    break;
			    
		case 3: nextSel = new EmployeeLogin();
				break;
				
				
		case 4: nextSel = new NewCustomer();
				break;
				
		case 5: nextSel = new BanKTrans();
				break;
		
		case 6: nextSel = new BankWith();
				
		default: 
				
		
		}
		return nextSel;
		
	}
	

}
