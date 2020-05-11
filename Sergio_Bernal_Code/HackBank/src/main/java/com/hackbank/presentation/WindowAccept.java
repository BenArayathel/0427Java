package com.hackbank.presentation;

import java.util.Scanner;

public class WindowAccept {
	
	public static String openWindow(Scanner sc) {
		String opt = null;
		boolean flag = false;
		do {
			Main.loggy.info("--- Select one of the follow options:");
			Main.loggy.info("--- 1 - Accept");
			Main.loggy.info("--- 2 - Cancel");
			Main.loggy.info("--- 3 - Menu");
			opt = sc.nextLine();
			if (opt.equals("1") || opt.equals("2") || opt.equals("3")) {
				flag = true;
			}else {
				Main.loggy.info("Please enter a valid option.");
			}
		} while (!flag);
		return opt;
	}
}
