package com.bank.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.bank.dao_interface.UserDAOInterface;
import com.bank.models.Account;
import com.bank.models.User;
import com.bank.presentation.AccountsView;
import com.bank.presentation.EmployeeView;
import com.bank.presentation.WelcomeView;
import com.bank.service_implementation.AccountServiceImplementation;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.service_interface.AccountServiceInterface;
import com.bank.service_interface.UserServiceInterface;
import com.bank.tools.BankException;

public class Main {
	
	// Setting up logger, scanner, for use in whole application
	public static Logger myLog = Logger.getLogger(Main.class);
	public static final Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws BankException {
		WelcomeView.welcome();		
	}
}