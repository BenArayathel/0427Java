package com.accounts;

import com.maindriver.myScanner;

public class Deposit {

	public static void deposit() {
		String depositAccount = null;
		double depositAmount = 0.0;
		
		System.out.println("Please enter the name of the account you'd like to make the deposit into: ");
		depositAccount = myScanner.scan.nextLine();
		System.out.println("Please enter the amount that you'd like to deposit: ");
		depositAmount = Double.parseDouble(myScanner.scan.nextLine());
		System.out.println("Now depositing $" + depositAmount + " into " + depositAccount);
		

	}
	
}

//what else do i have to take into account? what exceptions and problems?
//use conditional to deal with incorrect account names, and with unacceptable amounts