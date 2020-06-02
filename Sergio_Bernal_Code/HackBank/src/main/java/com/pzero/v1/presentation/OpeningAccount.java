package com.pzero.v1.presentation;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.pzero.v1.Main;
import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.business.services.approval.PendingApprovalService;
import com.pzero.v1.business.services.approval.PendingApprovalSrvImpl;
import com.pzero.v1.business.services.atype.AccountTypeServiceImpl;
import com.pzero.v1.persistence.models.Account;
import com.pzero.v1.persistence.models.AccountType;
import com.pzero.v1.persistence.models.PendingApproval;
import com.pzero.v1.persistence.models.Person;

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
			Main.loggy.info("--- What type of Bank Account do you need?");
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
				opt = 3;
				break;
			case 2:
				SignupAccount.openForm(sc, listAccountType.get(1));
				opt = 3;
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
			Main.loggy.info("--- Select Status:");
			pApproval.setStatus(ChangeStatus.status(sc));
			pApproval.setCreatedAt(new Date());
			Main.loggy.info("\nAccount's Information:\n");
			Main.loggy.info(pApproval.preview()+"\n");
			
			Main.loggy.info("Is the new Account correct?");
			String choice = WindowAccept.openWindow(sc);
			
			if (choice.equals("1")) {
				iAccount = penginApprovalSrv.createApproval(pApproval);
				if(iAccount != null) {
					Main.loggy.info("Your new Bank Account is ready to use!!!\n");
					Main.loggy.info("\nTo use the services of our bank\nfinish your registration with the Account Number.!\n");
					Main.loggy.info(iAccount.toString());
				}else {
					if(pApproval.getStatus().equals("Pending"))
						Main.loggy.info("You're account status is pending!. \nContact an employee to finish it!");
					else
						Main.loggy.info("You're account was rejected!. \nContact an employee for Help!");
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
	
	public static void customerForm(Scanner sc) {
		List<AccountType> listAccountType = new AccountTypeServiceImpl().getAllAccountType();
		if (listAccountType.size() > 0) {
//			System.out.println(listAccountType.get(0));
		}
		int opt = 0;
		do {
			Main.loggy.info("\n--- Open an Account Form ---");
			Main.loggy.info("--- What type of Bank Account do you need?");
			Main.loggy.info("--- 1 - Saving");
			Main.loggy.info("--- 2 - Checking");
			Main.loggy.info("--- 3 - Customer's Menu");
			try {
				opt = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				opt = 999;
			}
			switch (opt) {
			case 1:
				openAccount(sc, Main.sUser.iUser.getPersonId(), listAccountType.get(0));
				opt = 3;
				break;
			case 2:
				openAccount(sc, Main.sUser.iUser.getPersonId(), listAccountType.get(0));
				opt = 3;
				break;
			default:
				Main.loggy.info("\nThis is not a valid option -> "+opt);
				Main.loggy.info("We're sorry try again.\n");
				break;
			}
		} while (opt != 3);
	}
	
	public static void openAccount(Scanner sc, String personId, AccountType accountType) {
		PendingApproval pApproval = new PendingApproval();
		Account iAccount = null;
		Main.loggy.info("\n--- Your new account is in Pre-Approval ---\n");
		try {
			
			Main.loggy.info("--- Client Id: "+personId);
			Person person = new Person();
			person.setId(personId);
			pApproval.setPerson(person);
			Main.loggy.info("--- Account Type: "+accountType.getName());
			pApproval.setAccountType(accountType);
			Main.loggy.info("\n--- Enter the next information:");
			Main.loggy.info("--- Start Balance:");
			pApproval.setStartBalance(Main.vd.isValidBalance(Double.parseDouble(sc.nextLine())));
			pApproval.setStatus("Pending");
			pApproval.setCreatedAt(new Date());
			Main.loggy.info("\nAccount's Information:\n");
			Main.loggy.info(pApproval.preview()+"\n");
			
			Main.loggy.info("Is the new Account correc?");
			String choice = WindowAccept.openWindow(sc);
			
			if (choice.equals("1")) {
				iAccount = penginApprovalSrv.createApproval(pApproval);
				if(iAccount == null) {
					if(pApproval.getStatus().equals("Pending"))
						Main.loggy.info("You're account status is pending!. \nNow contact an employee to finish it!\n");
				}else {
					Main.loggy.info("You're account status is Approve!.");
				}
			}
		} catch (NumberFormatException | BusinessException e1) {
			Main.loggy.info("Start Balance isn't valid could not be negative.\n");
		}
	}
	
}
