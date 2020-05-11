package com.hackbank.presentation;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.business.services.approval.PendingApprovalService;
import com.hackbank.business.services.approval.PendingApprovalSrvImpl;
import com.hackbank.business.services.atype.AccountTypeServiceImpl;
import com.hackbank.persistence.models.Account;
import com.hackbank.persistence.models.AccountType;
import com.hackbank.persistence.models.PendingApproval;
import com.hackbank.persistence.models.Person;

public class OpeningAccount {
	
	final static PendingApprovalService penginApprovalSrv = new PendingApprovalSrvImpl();
	
	public static void openForm(Scanner sc) {
		List<AccountType> listAccountType = new AccountTypeServiceImpl().getAllAccountType();
		if (listAccountType.size() > 0) {
//			System.out.println(listAccountType.get(0));
		}
		int opt = 0;
		do {
			Main.loggy.info("\n--- Open an Account Form ---");
			Main.loggy.info("--- What kind of Bank Account do you need?");
			Main.loggy.info("--- 1 - Saving");
			Main.loggy.info("--- 2 - Checking");
			Main.loggy.info("--- 3 - Main Menu");
			try {
				opt = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				opt = 999;
			}
			switch (opt) {
			case 1:
				SignupAccount.openForm(sc, listAccountType.get(0));
				break;
			case 2:
				SignupAccount.openForm(sc, listAccountType.get(1));
				break;
			default:
				Main.loggy.info("\nThis is not a valid option -> "+opt);
				Main.loggy.info("We're sorry try again.\n");
				break;
			}
		} while (opt != 3);
	}

	public static void menu(Scanner sc, Person person, AccountType accountType) {
		PendingApproval pApproval = new PendingApproval();
		Account iAccount = null;
		Main.loggy.info("\n--- Your new account is in Pre-Approval ---\n");
		try {
			
			Main.loggy.info("--- Client Id: "+person.getId());
			pApproval.setPerson(person);
			Main.loggy.info("--- Account Type: "+accountType.getName());
			pApproval.setAccountType(accountType);
			Main.loggy.info("\n--- Enter the next information:");
			Main.loggy.info("--- Start Balance:");
			pApproval.setStartBalance(Main.vd.isValidBalance(Double.parseDouble(sc.nextLine())));
			Main.loggy.info("--- Status:");
			pApproval.setStatus(changeStatus(sc));
			pApproval.setCreatedAt(new Date());
			Main.loggy.info("\nAccount's Information:\n");
			Main.loggy.info(pApproval.preview()+"\n");
			
			String choice = WindowAccept.openWindow(sc);
			
			if (choice.equals("1")) {
				iAccount = penginApprovalSrv.createApproval(pApproval);
				if(iAccount != null) {
					Main.loggy.info("Congratulation to open a new bank account with us!\n");
					iAccount.toString();
					Main.loggy.info("\nTo use the service of our bank\nfinish your register with the Account Number.!\n");
				}else {
					if(pApproval.getStatus().equals("Pending"))
						Main.loggy.info("You're account status is pending!. \nContact an employee to see how we can help you!");
					else
						Main.loggy.info("You're account was rejected!. \nContact an employee to see how we can help you!");
				}
			}else if (choice.equals("2")){
				menu(sc, person, accountType);
			}else if (choice.equals("3")) {
				Main.adminMenu(sc);
			}
			
		} catch (NumberFormatException | BusinessException e1) {
			Main.loggy.info("Start Balance isn't valid could not be negative.\n");
			menu(sc, person, accountType);
		}
	}
	
	private static String changeStatus(Scanner sc) {
		String status = "Pending";
		int opt = 0;
		boolean flag = false;
		do {
			Main.loggy.info("--- Enter one of the follow options:");
			Main.loggy.info("--- 1 - Approve");
			Main.loggy.info("--- 2 - Pending");
			Main.loggy.info("--- 3 - Reject");
			try {
				opt = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				opt = 999;
			}
			switch (opt) {
			case 1:
				status = "Approve";
				flag = true;
				break;
			case 2:
				status = "Pending";
				flag = true;
				break;
			case 3:
				status = "Reject";
				flag = true;
				break;
			default:
				Main.loggy.info("\nThis is not a valid option -> "+opt);
				Main.loggy.info("You must enter a valid option.\n");
				break;
			}
		} while (!flag);
		return status;
	}
	
}
