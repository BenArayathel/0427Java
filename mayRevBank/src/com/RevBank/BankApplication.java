package com.RevBank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankApplication extends Application{
	private Selection currentScreen;
	private Scanner scan;
	private Map<String, Object> appContext;
	
	public BankApplication() {}
	
	public BankApplication(String title) {
		this.title = title;
		this.currentScreen= new SelectionScreen();
		this.scan= new Scanner(System.in);
		
	}
	

	@Override
	public void run(String[] args) throws Exception {
		init();
		while(currentScreen != null) {
			currentScreen = currentScreen.doSelection(scan, this);
		}
	
		
	}
	
	public Map<String, Object> appContext(){
		return appContext;
	}
	
	private void init() throws Exception{
		appContext = new HashMap<>();
	}

}
