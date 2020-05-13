package com.company.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.company.model.Account;
import com.company.model.Customer;
import com.company.model.Transaction;
import com.company.service.ServiceLayer;
import com.company.view.BankApp;
import com.company.viewModel.AccountViewModel;

public class BankServiceController {
	
    private final ServiceLayer serviceLayer = new ServiceLayer();
	
	public Customer validateCustomerAccount(String firstName, String lastName, String accountId) {
		
		// call on serviceLayer to validate Registration. i.e. serviceLayer.checkRegistration();
		BankApp.loggy.info("validateRegistration triggered...");
		return serviceLayer.validateCustomerAccount(firstName, lastName, accountId);
	}
	
	public Customer validateLogin(String loginName, String password) {	
		BankApp.loggy.info("validateLogin triggered...");
		return serviceLayer.validateLogin(loginName, password);
		
	}
	
	public void createCustomerAccount(String firstName, String lastName, String birthday, String usState, String accountType, BigDecimal bigDecimalBalance) {
		serviceLayer.createCustomerAccount(firstName, lastName, birthday, usState, accountType, bigDecimalBalance);
	}
	
    public Boolean createAdditionalAccount(Integer customerId, String accountType, BigDecimal bigDecimalBalance) {
    	return serviceLayer.createAdditionalAccount(customerId, accountType, bigDecimalBalance);
    };

	
	public AccountViewModel getCustomerAccountDetail(String accountId) {
		return serviceLayer.getCustomerAccountDetail(accountId);
	}

	
    public AccountViewModel approveAccount(String accountId) {
    	return serviceLayer.approveAccount(accountId);
    };
    
    public List<Transaction> getLogList(){
    	return serviceLayer.getLogList();
    }
    
    public Boolean registerUser(Integer customerId, String loginName, String password) {
    	return serviceLayer.registerUser(customerId, loginName, password);
    }
    
    public List<Account> getAccountListByCustomerId(Integer customerId) {
    	return serviceLayer.getAccountListByCustomerId(customerId);
    }
    
    public Account depositAmount(Account account, BigDecimal depositAmount) {
    	return serviceLayer.depositAmount(account, depositAmount);
    };

}
