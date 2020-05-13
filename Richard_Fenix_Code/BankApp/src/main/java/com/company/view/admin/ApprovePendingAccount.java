package com.company.view.admin;

import java.util.Scanner;

import com.company.controller.BankServiceController;
import com.company.view.BankApp;
import com.company.viewModel.AccountViewModel;

public class ApprovePendingAccount {

    private Scanner scanner  = new Scanner(System.in);

	private final BankServiceController bankServiceController = new BankServiceController();
	
	public void customerAccountDetail() {
	    BankApp.loggy.info("================= Rich Program =================");
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info("    **** APPROVE CUSTOMER ACCOUNT PAGE ****");
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info("       1. Enter Account ID:");
        String accountId = scanner.nextLine();
                
        AccountViewModel avm = bankServiceController.approveAccount(accountId);
        
        if (avm != null) {
        	//displayCustomerDetail();
    	    displayCustomerAccountDetail(avm, "...");

        } else {
    	    displayCustomerAccountDetail(avm, "Account Detail not found.");
        }
	
	}

	
	public void displayCustomerAccountDetail(AccountViewModel avm, String message) {
		//		AccountViewModel Field Reference	
		//		================================
		//		private String accountId;
		//		private Integer customerId;
		//		private String firstName;
		//		private String lastName;
		//		private Date birthday;
		//		private String state;
		//		private String accountType;
		//		private String accountDescription;
		//		private BigDecimal balance;
		//		private boolean	approved;

	    BankApp.loggy.info("============== CUSTOMER ACCOUNT DETAIL ==============");
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info("       1. Account ID: " + avm.getAccountId());
	    BankApp.loggy.info("       2. Customer ID :" + avm.getCustomerId());
	    BankApp.loggy.info("       3. First Name: " + avm.getFirstName());
	    BankApp.loggy.info("       4. Last Name: " + avm.getLastName());
	    BankApp.loggy.info("       5. Birthday: " + avm.getBirthday());
	    BankApp.loggy.info("       6. State: " + avm.getState());
	    BankApp.loggy.info("       7. Account Type: " + avm.getAccountType() + " - " + avm.getAccountDescription());
	    BankApp.loggy.info("       8. Balance: " + avm.getBalance());
	    BankApp.loggy.info("       9. Approved? : " + avm.isApproved());
	    BankApp.loggy.info("       ");
	    BankApp.loggy.info("       Note: " + message);
	    BankApp.loggy.info("       Hit ENTER to go to previous menu...");
	    
        String accountId = scanner.nextLine();

	}
	
}
