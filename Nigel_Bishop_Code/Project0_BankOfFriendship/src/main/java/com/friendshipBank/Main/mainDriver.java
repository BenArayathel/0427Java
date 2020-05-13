package com.friendshipBank.Main;

import org.apache.log4j.Logger;

import com.friendshipBank.presentation.welcomePage;

public class mainDriver {

	public final static Logger SystemLog = Logger.getLogger(mainDriver.class);
	
	public static void main(String[] args) 
	{

		welcomePage.BankWelcomePage();

	}

}


