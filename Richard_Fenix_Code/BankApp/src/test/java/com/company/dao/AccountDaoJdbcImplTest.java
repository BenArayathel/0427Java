package com.company.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.company.model.Account;

public class AccountDaoJdbcImplTest {

    AccountDaoJdbcImpl accountDaoJdbcImpl = new AccountDaoJdbcImpl();

	
	@Before
	public void setUp() throws Exception {
		List<Account> cList = accountDaoJdbcImpl.getAllAccounts();
		for (Account c: cList) {
			accountDaoJdbcImpl.deleteAccount(c.getAccountId());
		}
	}

	@Test
	public void testAddGetDeleteAccount() {
		
        Account account = new Account();
        
        account.setCustomerId(10084);
        account.setAccountType("CHEC");
        account.setBalance(new BigDecimal("12345.67"));
        account.setApproved(false);
                
        account = accountDaoJdbcImpl.addAccount(account);
        
        System.out.println("account inserted");

        Account account1 = accountDaoJdbcImpl.getAccount(account.getAccountId());

        System.out.println("Input by user through the app... " + account);
        System.out.println("Retrieved from DB... " + account1);
        
        assertEquals(account1, account);

        accountDaoJdbcImpl.deleteAccount(account.getAccountId());

        account1 = accountDaoJdbcImpl.getAccount(account.getAccountId());

        assertNull(account1);
	}


	@Test
	public void testGetAllAccounts() {
		Account account = new Account();
		
        account.setCustomerId(10084);
        account.setAccountType("CHEC");
        account.setBalance(new BigDecimal("12345.67"));
        account.setApproved(false);
                
        account = accountDaoJdbcImpl.addAccount(account);
        
        System.out.println("First account inserted... " + account);
        
        account = new Account();
        account.setCustomerId(10084);
        account.setAccountType("SAVI");
        account.setBalance(new BigDecimal("500.00"));
        account.setApproved(false);
        
        //AccountDaoJdbcImpl accountDaoJdbcImpl = new AccountDaoJdbcImpl(); 
        account = accountDaoJdbcImpl.addAccount(account);
        
        System.out.println("Second account inserted... " + account);
        
        List<Account> xList = accountDaoJdbcImpl.getAllAccounts();

        System.out.println("Total records in the array as extracted from db is... " + xList.size());
        
        assertEquals(2, xList.size());

	}

	@Test
	public void testUpdateAccount() {
        Account account = new Account();
        		
        account.setCustomerId(10084);
        account.setAccountType("CHEC");
        account.setBalance(new BigDecimal("12345.67"));
        account.setApproved(false);

        // Add record
        account = accountDaoJdbcImpl.addAccount(account);
        
        System.out.println("Account for update inserted..." + account);
        
        account.setApproved(true);
        
        // Update record
        accountDaoJdbcImpl.updateAccount(account);
        
        Account account1 = accountDaoJdbcImpl.getAccount(account.getAccountId());
  
        System.out.println("Updated account from DB... " + account1);

        // Check if updated
        assertEquals(true, account1.isApproved());
        
   	}

}
