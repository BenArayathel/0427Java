package com.friendshipBank.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.friendshipBank.dao.impl.customerDAOImpl;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.customer;
import com.friendshipBank.presentation.welcomePage;
import com.friendshipBank.service.customerService;
import com.friendshipBank.service.impl.customerServiceImpl;
import com.friendshipBank.service.impl.myScanner;

public class mainDriver {

	final static Logger loggy = Logger.getLogger(mainDriver.class);
	
	public static void main(String[] args) 
	{
		
//		String name = "Testing Log4j";
//		
//		loggy.setLevel(Level.INFO);
//		
//		loggy.info("THIS IS AN INFO UPDATE!!!!  " + name + " This complete");
//		loggy.warn("THIS IS AN INFO UPDATE!!!!  " + name + " This complete");
//		loggy.error("THIS IS AN INFO UPDATE!!!!  " + name + " This complete");
		

		

		welcomePage.BankWelcomePage();

	}

}
