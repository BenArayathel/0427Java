package com.bhank.main;

import org.apache.log4j.Logger;

import com.bhank.menu.Menu;

public class Main {
	
	public final static Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.mainMenu();
	}
}
