package com.pzero.v1.presentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.pzero.v1.Main;
import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.persistence.models.Account;
import com.pzero.v1.persistence.models.Transfer;

public class TransferMenu {
	
	public static void create(Scanner sc, Account account) {
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
			transfer.setAmount(Main.vd.isValidBalance(Double.parseDouble(sc.nextLine())));
			transfer.setStatus("Pending");
			
			if (transfer.getAmount() <= account.getBalance()) {
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
			Main.loggy.info("Amount "+transfer.getAmount()+" isn't valid.");
		} catch (BusinessException e) {
			Main.loggy.info(e.getMessage());
		}
	}
	
	public static void search(Scanner sc) {
		List<Transfer> list = new ArrayList<>();
		try {
			list = Main.transferSrv.transferList(Main.sUser.iUser.getPersonId());
			Main.loggy.info("\n--- Pending Transfers ---");
			if(!list.isEmpty()) {				
				Main.loggy.info("No.  |  From Account  |  From Routing  |  To Account  |  To Routing  |  Amount  |  Status");
				Main.loggy.info("---- | -------------- | -------------- | ------------ | ------------ | -------- | ----------- ");
				for (Transfer a : list) {
					Main.loggy.info((list.indexOf(a)+1)+"   |  "+a.getIniAcccountNumber()+"  |  "+a.getIniRoutingNumber()+"  |  "+a.getEndAccountNumber()+"  |  "+a.getEndRoutingNumber()+"  |  "+a.getAmount()+" | "+a.getStatus());
					Main.loggy.info(" -------------------------------------------------------------------------------------- ");
				}
				
				Main.loggy.info("\nSelect a Transfer to Accept or Reject.");
				try {
					int index = Integer.parseInt(sc.nextLine());
					if (index <= list.size())
						updateTransfer(sc, list.get(index-1));
				} catch (NumberFormatException e) {
					Main.loggy.info(e.getMessage());
				}
			}else {
				Main.loggy.info("You don't have any active transfer.");
			}			
		} catch (BusinessException e) {
			Main.loggy.info(e.getMessage());
		}
	}
	
	public static void updateTransfer(Scanner sc, Transfer transfer) {
		String opt = "";
		Transfer iTransfer = transfer;
		Account account = null;
		boolean flag = false;
		do {
			try {
				Main.loggy.info("\nDo you want to accept the transfer?");
				Main.loggy.info("--- Enter one of the following options ---");
				Main.loggy.info("- 1 - Accept");
				Main.loggy.info("- 2 - Reject");
				opt = sc.nextLine();
				if (opt.equals("1")) {
					iTransfer.setStatus("Accept");
					account = Main.accountSrv.getAccountById(iTransfer.getEndAccountNumber());
					Main.transferSrv.acceptTransfer(iTransfer, account);
					Main.loggy.debug(Main.sUser.iUser.getEmail() + " - Transfer - "+iTransfer.toString());
					Main.loggy.info("Transfer success - Go to Accounts to see your new balance.\n");
//					Main.loggy.info(account.toString()+"\n");
					flag = true;
				}else if(opt.equals("2")) {
					iTransfer.setStatus("Reject");
					account = Main.accountSrv.getAccountById(iTransfer.getIniAcccountNumber());
					Main.transferSrv.acceptTransfer(iTransfer, account);
					Main.loggy.debug(Main.sUser.iUser.getEmail() + " - Transfer - "+iTransfer.toString());
					Main.loggy.info("Transfer success - Go to Accounts to see your new balance.\n");
//					Main.loggy.info(account.toString()+"\n");
					flag = true;
				}else {
					Main.loggy.info("Invalid option.");
				}
				
			} catch (BusinessException e) {
				Main.loggy.info(e.getMessage());
			}
		} while (!flag);
		
	}

}
