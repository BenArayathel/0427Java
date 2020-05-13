package com.hackbank.presentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.models.Account;
import com.hackbank.persistence.models.Transfer;

public class AccountMenu {
	
	public static void searchCustomer(Scanner sc) {
		List<Account> listAccount = new ArrayList<>();
		Main.loggy.info("\n--- Search Customer Form ---");
		Main.loggy.info("--- Enter the next information:");
		try {
			Main.loggy.info("--- Customes's SSN:");
			String personId = Main.personSrv.getPersonIdBySSN(sc.nextLine());

			if(!personId.equals("")) {
				listAccount = Main.accountSrv.getAllAccountsByCustomer(personId);
				if(!listAccount.isEmpty()) {
					Main.loggy.info("\n--- Accounts ---\n");				
					Main.loggy.info("No.	Type	  Account No.");
					Main.loggy.info("---- | -------- | ------------ ");
					for (Account a : listAccount) {
						Main.loggy.info((listAccount.indexOf(a)+1)+"  -   "+a.getAccountType().getName()+"	  "+a.getId());
						Main.loggy.info("---- | -------- | ------------ ");
					}
					
					Main.loggy.info("Press any key to continue.");
					sc.nextLine();
				}else {
					Main.loggy.info("The client doesn't have any active account.");
				}
			}else {
				Main.loggy.info("The Customer doesn't exist.\n");
			}
			
		} catch (BusinessException e) {
			Main.loggy.info(e.getMessage());
		}
	}

	public static void showAccounts(List<Account> list, Scanner sc) {
		if(!list.isEmpty()) {
			Main.loggy.info("\n--- Accounts ---\n");
			Main.loggy.info("No.	Type	  Account No.");
			Main.loggy.info("---- | -------- | ------------ ");
			for (Account a : list) {
				Main.loggy.info((list.indexOf(a)+1)+"  -   "+a.getAccountType().getName()+"	  "+a.getId());
				Main.loggy.info("---- | -------- | ------------ ");
			}
			Main.loggy.info("\nSelect an Account to see details.");
			try {
				int index = Integer.parseInt(sc.nextLine());
				if (index <= list.size())
					detailAccounts(list.get(index-1));
					updateBalanceAccount(sc, list.get(index-1));
			} catch (NumberFormatException e) {
				Main.loggy.info(e.getMessage());
			}
		}else {
			Main.loggy.info("The client doesn't have any active account.");
		}
		
	}
	
	public static void detailAccounts(Account account) {
		Main.loggy.info("\n--- Account's Detail ---\n");
		Main.loggy.info("No.	Type	  Account No.	  Routing No.	  Balance");
		Main.loggy.info("---- | -------- | ------------ | ------------ | ----------");
		Main.loggy.info("1  -   "+account.getAccountType().getName()+"	  "+account.getId()+"	  "+account.getRoutingNumber()+"	 "+account.getBalance());
		Main.loggy.info("---- | -------- | ------------ | ------------ | ----------");
	}
	
	public static void updateBalanceAccount(Scanner sc, Account account) {
		int opt = 0;
		Account iAccount = account;
		double balance = 0;
		do {
			try {
				Main.loggy.info("\n--- Menu Account ---");
				Main.loggy.info("--- Enter one of the following options ---");
				Main.loggy.info("- 1 - Deposit");
				Main.loggy.info("- 2 - Withdraw");
				Main.loggy.info("- 3 - Transfer Money To");
				Main.loggy.info("- 4 - Accounts");
				opt = Integer.parseInt(sc.nextLine());
				switch (opt) {
				case 1:
					Main.loggy.info("\nHow much do you want to deposit?");
					balance = Double.parseDouble(sc.nextLine());
					iAccount = Main.accountSrv.depositBalanceAccount(iAccount, balance);
					detailAccounts(iAccount);
					break;
				case 2:
					Main.loggy.info("\nHow much do you want to withdraw?");
					balance = Double.parseDouble(sc.nextLine());
					iAccount = Main.accountSrv.withdrawBalanceAccount(iAccount, balance);
					detailAccounts(iAccount);
					break;
				case 3:
					createTransferForm(sc, iAccount);
					opt = 4;
					break;
				case 4:
					//loggy.info("Under construction");
					break;
				default:
					Main.loggy.info("");
					opt = 0;
					break;
				}
			} catch (NumberFormatException e) {
				Main.loggy.info("You have an error, digiting a number. Try again.");
				opt = 0;
			} catch (BusinessException e) {
				Main.loggy.info(e.getMessage());
				opt = 0;
			}
		} while (opt != 4);
		
	}
	
	public static void createTransferForm(Scanner sc, Account account) {
		Transfer transfer = new Transfer();
		transfer.setIniAcccountNumber(account.getId());
		transfer.setIniRoutingNumber(account.getRoutingNumber());
		transfer.setCreatedAt(new Date());
		boolean flag = false;
		try {
			Main.loggy.info("\n--- Transfer Form ---");
			Main.loggy.info("--- Enter the next information");
			Main.loggy.info("-- Account Number:");
			transfer.setEndAccountNumber(sc.nextLine());
			Main.loggy.info("-- Routing Number:");
			transfer.setEndRoutingNumber(sc.nextLine());
			Main.loggy.info("-- Amount:");
			transfer.setBalance(Main.vd.isValidBalance(Double.parseDouble(sc.nextLine())));
			transfer.setStatus("Pending");
			
			if (transfer.getBalance() <= account.getBalance()) {
				Main.loggy.info("--- Is the transfer correct?");
				String choice = WindowAccept.openWindow(sc);
				
				if (choice.equals("1")) {
					flag = Main.transferSrv.createTransfer(transfer, account);
					if(flag) {
						Main.loggy.info("Transfer success!\n");
						// Main.loggy.info(iAccount.toString());
					}
				}
			}else {
				Main.loggy.info("Transfer amount is mayor than currently balance.");
			}
//			else if (choice.equals("2")){
//				menu(sc, person, accountType);
//			}else if (choice.equals("3")) {
//				Main.adminMenu(sc);
//			}
		} catch (NumberFormatException e) {
			Main.loggy.info("Amount "+transfer.getBalance()+" isn't valid.");
		} catch (BusinessException e) {
			Main.loggy.info(e.getMessage());
		}
	}
	
}
