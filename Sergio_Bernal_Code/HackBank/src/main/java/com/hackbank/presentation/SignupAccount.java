package com.hackbank.presentation;

import java.util.Scanner;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.models.AccountType;
import com.hackbank.persistence.models.Person;

public class SignupAccount {
	
	public static void openForm(Scanner sc, AccountType accountType) {
		Person person = new Person();
		String dob = null;
		Main.loggy.info("\n--- Signup Account Form ---");
		Main.loggy.info("--- Enter the next information:");
		try {
			Main.loggy.info("--- Name:");
			person.setName(sc.nextLine());
			Main.loggy.info("--- Last Name:");
			person.setLastName(sc.nextLine());
			Main.loggy.info("--- SSN:");
			person.setSsn(sc.nextLine());
			Main.loggy.info("--- Date of Birth:");
			dob = sc.nextLine();
			person.setDob(Main.vd.isValidDate(dob));
			Main.loggy.info("--- Phone Number:");
			person.setPhoneNumber(sc.nextLine());
			Main.loggy.info("--- City:");
			person.setCityd(sc.nextLine());
			Main.loggy.info("\nClient's Information:");
			Main.loggy.info("Account Type: "+accountType.getName()+"\n");
			Main.loggy.info(person.preview());
			
			String choice = WindowAccept.openWindow(sc);
			
			if (choice.equals("1")) {
				Person iPerson = Main.personSrv.createPerson(person);
				if(iPerson != null) {
					OpeningAccount.menu(sc, iPerson, accountType);
//					loggy.info("Congratulations to join us!\n");
//					loggy.info("Now go on an open a new bank account.\n");
				}else {
					Main.loggy.info("Creating a new Client fail contact SYSADMIN\n");
				}
			}else if (choice.equals("2")){
				openForm(sc, accountType);
			}else if (choice.equals("3")) {
				Main.adminMenu(sc);
			}
			
		} catch (BusinessException e) {
			Main.loggy.info(e.getMessage());
		} catch (NumberFormatException e1) {
			Main.loggy.info("Person contact isn't valid.\n");
		}
	}

}
