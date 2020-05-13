package com.hackbank.presentation;

import java.util.Scanner;

public class ChangeStatus {

	public static String status(Scanner sc) {
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
