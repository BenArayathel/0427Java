package com.company.view.admin;

import java.util.List;
import java.util.Scanner;

import com.company.controller.BankServiceController;
import com.company.view.BankApp;
import com.company.model.Transaction;;

public class ViewTransactionLog {
	
    private Scanner scanner  = new Scanner(System.in);

	private final BankServiceController bankServiceController = new BankServiceController();
	

	public void displayAllTransactionLog() {
		
		List<Transaction> tList = bankServiceController.getLogList();
		
	    BankApp.loggy.info("================ TRANSACTION LOG ================");
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info(" Trans_ID     Account    Trans_Code       Amount      Ending_Balance    Transaction Time ");
	    BankApp.loggy.info(" --------     --------   ----------   -------------   ---------------   --------------------");


	    
		tList.forEach(t ->
		    BankApp.loggy.info ("  " + t.getTransactionId() + "          " + t.getAccountId() + "       " 
		    		+ t.getTransactionType() + "             " + t.getAmount() + "         " + t.getEndingBalance() + "            " 
		    		+ t.getTransTime())
		    );
		
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info("       Hit ENTER to go to previous menu...");
	    
        scanner.nextLine();

	}

}
