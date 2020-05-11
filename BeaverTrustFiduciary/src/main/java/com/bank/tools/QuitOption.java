package com.bank.tools;

import com.bank.main.Main;
import com.bank.presentation.WelcomeView;

public class QuitOption {
	
	public static void quit() throws BankException {

			
		Main.myLog.info("\nThanks for using Beaver Trust Fiduciary. Goodbye.");
		Main.myLog.info(".....................................................");
		WelcomeView.welcome();

	}

}
