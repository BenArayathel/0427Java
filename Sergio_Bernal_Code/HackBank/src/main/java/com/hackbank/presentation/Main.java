package com.hackbank.presentation;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.business.services.account.AccountService;
import com.hackbank.business.services.account.AccountServiceImpl;
import com.hackbank.business.services.approval.PendingApprovalService;
import com.hackbank.business.services.approval.PendingApprovalSrvImpl;
import com.hackbank.business.services.person.PersonService;
import com.hackbank.business.services.person.PersonServiceImplementation;
import com.hackbank.business.services.transfer.TransferService;
import com.hackbank.business.services.transfer.TransferServiceImpl;
import com.hackbank.business.services.user.SingletonUser;
import com.hackbank.business.validations.Validation;
import com.hackbank.persistence.models.Account;

public class Main {
	
	final static Logger loggy = Logger.getLogger(Main.class);
	final static SingletonUser sUser = SingletonUser.getUser();
	final static Validation vd = new Validation(); 
	final static PersonService personSrv = new PersonServiceImplementation();
	final static AccountService accountSrv = new AccountServiceImpl();
	final static PendingApprovalService pApprovalSrv = new PendingApprovalSrvImpl();
	final static TransferService transferSrv = new TransferServiceImpl();

	public static void main(String[] args) {

		loggy.setLevel(Level.ALL);
		Scanner sc = new Scanner(System.in);
		
		header();
		mainMenu(sc);
		// 200512636492
		// clearScreen();
	}

	private static void mainMenu(Scanner sc){
		int opt = 0;
		do {
			loggy.info("--- Main Menu ---");
			loggy.info("--- Enter one of the following options:");
			loggy.info("--- 1 - Login");
			loggy.info("--- 2 - Finish Register");
			loggy.info("--- 3 - Exit Program");
			try {
				opt = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				loggy.info("\nInvalid option.");
			}
			switch (opt) {
			case 1:
				LoginForm.openForm(sc);
				break;
			case 2:
				FinishRegister.openForm(sc);
				break;
			case 3:
				if (exitProgram(sc)) {
					loggy.info("\nHave a nice day!\n");
					System.exit(0);
				}
				break;
			default:
				loggy.info("\nThis is not a valid option -> "+opt);
				loggy.info("We're sorry try again.\n");
				break;
			}
			opt = 0;
		}while(opt!=3);
	}
	
	public static void adminMenu(Scanner sc){
		int opt = 0;
		loggy.info("\n--- Welcome to the Employee's Portal ---");
		loggy.info("--- Have a wonderful day!!! ---\n");
		do {
			loggy.info("--- Menu ---");
			loggy.info("--- Enter one of the following options:");
			loggy.info("--- 1 - Register a New Client");
			loggy.info("--- 2 - Search Client");
			loggy.info("--- 3 - Pending to Approve");
//			loggy.info("--- 4 - View Transactions");
			loggy.info("--- 4 - Logout");
			try {
				opt = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				opt = 9999;
			}
			switch (opt) {
			case 1:
				OpeningAccount.openForm(sc);
				break;
			case 2:
				AccountMenu.searchCustomer(sc);
				break;
			case 3:
				PendingAccountMenu.showPendingApproval(sc);
				break;
			case 4:
//				loggy.info("Under Construction!\n");
				break;
			default:
				loggy.info("\nThis is not a valid option -> "+opt);
				loggy.info("We're sorry try again.\n");
				opt = 0;
				break;
			}
		}while(opt!=4);
	}
	
	public static void customerMenu(Scanner sc){
		int opt = 0;
		loggy.info("\n--- Welcome to the Custumer's Portal ---");
		loggy.info("--- How can we help you today? ---\n");
		do {
			loggy.info("--- Customer's Menu ---");
			loggy.info("--- Enter one of the following options:");
			loggy.info("--- 1 - Show Accounts");
			loggy.info("--- 2 - Open Account");
			loggy.info("--- 3 - Pending Transfers");
			loggy.info("--- 4 - Logout");
			try {
				opt = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				opt = 9999;
			}
			switch (opt) {
			case 1:
				try {
					;
					Main.loggy.debug(Main.sUser.iUser.getEmail() + " - Show Accounts");
					List<Account> iList = accountSrv.getAllAccountsByCustomer(sUser.iUser.getPersonId());
					AccountMenu.showAccounts(iList, sc);
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				Main.loggy.debug(Main.sUser.iUser.getEmail() + " - Open Account");
				OpeningAccount.customerForm(sc);
				break;
			case 3:
				Main.loggy.debug(Main.sUser.iUser.getEmail() + " - Pending Transfers");
				TransferMenu.search(sc);
				break;
			case 4:
				//loggy.info("Under Construction!\n");
				break;
			default:
				loggy.info("\nThis is not a valid option -> "+opt);
				loggy.info("We're sorry try again.\n");
				opt = 0;
				break;
			}
		}while(opt!=4);
	}
	
	private static void header() {
		
		loggy.info("\n$$\\   $$\\                     $$\\             $$$$$$$\\                      $$\\\n"
				+ "$$ |  $$ |                    $$ |            $$  __$$\\                     $$ |      \n"
				+ "$$ |  $$ | $$$$$$\\   $$$$$$$\\ $$ |  $$\\       $$ |  $$ | $$$$$$\\  $$$$$$$\\  $$ |  $$\\ \n"
				+ "$$$$$$$$ | \\____$$\\ $$  _____|$$ | $$  |      $$$$$$$\\ | \\____$$\\ $$  __$$\\ $$ | $$  |\n"
				+ "$$  __$$ | $$$$$$$ |$$ /      $$$$$$  /       $$  __$$\\  $$$$$$$ |$$ |  $$ |$$$$$$  / \n"
				+ "$$ |  $$ |$$  __$$ |$$ |      $$  _$$<        $$ |  $$ |$$  __$$ |$$ |  $$ |$$  _$$<  \n"
				+ "$$ |  $$ |\\$$$$$$$ |\\$$$$$$$\\ $$ | \\$$\\       $$$$$$$  |\\$$$$$$$ |$$ |  $$ |$$ | \\$$\\ \n"
				+ "\\__|  \\__| \\_______| \\_______|\\__|  \\__|      \\_______/  \\_______|\\__|  \\__|\\__|  \\__|");
		
		loggy.info("\nWelcome to the Management Console App V1.0!!!");
		loggy.info("Where create an Account is a pleasure :)\n");
		
	}
	
	private static boolean exitProgram(Scanner sc) {
		boolean flag = false;
		String opt = null;
		loggy.info("\nAre you sure do you want to exit?\n");
		loggy.info("--- Select one of the follow options:");
		loggy.info("--- 1 - YES");
		loggy.info("--- Press Any Key - NO");
		opt = sc.nextLine();
		if(opt.equals("1")) {
			flag = true;
		}
		return flag;
	}
	
	public static void clearScreen() {
		final String ESC = "\033[";
		System.out.println("\033[H\033[2J");
		System.out.print(ESC + "2J");
	}
	

}
