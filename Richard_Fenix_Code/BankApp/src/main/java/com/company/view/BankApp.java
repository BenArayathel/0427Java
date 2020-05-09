package com.company.view;

import java.util.Scanner;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class BankApp {
	
	public final static Logger loggy = Logger.getLogger(BankApp.class);
	
	public static void main(String[] args) {
		
		loggy.setLevel(Level.INFO);
		
		MainMenu mainMenu = new MainMenu();
		mainMenu.displayMenu();

    }

	
}
