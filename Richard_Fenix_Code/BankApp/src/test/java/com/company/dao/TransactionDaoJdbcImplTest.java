package com.company.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.company.model.Transaction;

public class TransactionDaoJdbcImplTest {

    TransactionDaoJdbcImpl transactionDaoJdbcImpl = new TransactionDaoJdbcImpl();

  	@Before
	public void setUp() throws Exception {
		List<Transaction> cList = transactionDaoJdbcImpl.getAllTransactions();
		for (Transaction c: cList) {
			transactionDaoJdbcImpl.deleteTransaction(c.getTransactionId());
		}		
	}

	@Test
	public void testAddGetDeleteTransaction() {
        Transaction transaction = new Transaction();
        
        transaction.setAccountId("CH201");
        transaction.setTransactionType("INIT");
        
        transaction.setAmount(new BigDecimal("567.75"));
        transaction.setEndingBalance(new BigDecimal("567.75"));

        // Insert current local date and time of current local time.
        transaction.setTransTime(Timestamp.valueOf(LocalDateTime.now())); 
                
        transaction = transactionDaoJdbcImpl.addTransaction(transaction);
        
        System.out.println("transaction inserted");

        Transaction transaction1 = transactionDaoJdbcImpl.getTransaction(transaction.getTransactionId());

        System.out.println("Input by user through the app... " + transaction);
        System.out.println("Retrieved from DB... " + transaction1);
        
        assertEquals(transaction1, transaction);

        transactionDaoJdbcImpl.deleteTransaction(transaction.getTransactionId());

        transaction1 = transactionDaoJdbcImpl.getTransaction(transaction.getTransactionId());

        assertNull(transaction1);

	}


	@Test
	public void testGetAllTransactions() {
		Transaction transaction = new Transaction();
		
        transaction.setAccountId("CH201");
        transaction.setTransactionType("INIT");
        
        transaction.setAmount(new BigDecimal("567.75"));
        transaction.setEndingBalance(new BigDecimal("567.75"));

        // Insert current local date and time of current local time.
        transaction.setTransTime(Timestamp.valueOf(LocalDateTime.now())); 
                
        transaction = transactionDaoJdbcImpl.addTransaction(transaction);

        System.out.println("First transaction inserted... " + transaction);
        
        transaction = new Transaction();
        
        transaction.setAccountId("SA101");
        transaction.setTransactionType("DEPO");
        
        transaction.setAmount(new BigDecimal("200.00"));
        transaction.setEndingBalance(new BigDecimal("200.00"));

        
        // Insert current local date and time of current local time.
        transaction.setTransTime(Timestamp.valueOf(LocalDateTime.now())); 
                
        transaction = transactionDaoJdbcImpl.addTransaction(transaction);
        
        System.out.println("Second transaction inserted... " + transaction);
        
        List<Transaction> xList = transactionDaoJdbcImpl.getAllTransactions();

        System.out.println("Total records in the array as extracted from db is... " + xList.size());
        
        assertEquals(2, xList.size());
	}

	@Test
	public void testUpdateTransaction() {
        Transaction transaction = new Transaction();
        
        transaction.setAccountId("CH201");
        transaction.setTransactionType("INIT");
        
        transaction.setAmount(new BigDecimal("567.75"));
        transaction.setEndingBalance(new BigDecimal("567.75"));
        
        // Insert current local date and time of current local time.
        transaction.setTransTime(Timestamp.valueOf(LocalDateTime.now())); 
                
        transaction = transactionDaoJdbcImpl.addTransaction(transaction);
        
        System.out.println("Transaction for update inserted..." + transaction);
        
        transaction.setTransactionType("APPR");
        
        transactionDaoJdbcImpl.updateTransaction(transaction);
        
        Transaction transaction1 = transactionDaoJdbcImpl.getTransaction(transaction.getTransactionId());
  
        System.out.println("Updated transaction from DB... " + transaction1);

        assertEquals("APPR", transaction1.getTransactionType());
              
       
	}

}
