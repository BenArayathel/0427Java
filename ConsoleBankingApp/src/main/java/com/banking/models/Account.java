package com.banking.models;

import com.banking.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.*;

public class Account {

    private String id;
    private double balance;
//    List<Customer> customers = new ArrayList<Customer>();
    final static Logger loggy = Logger.getLogger(Account.class);


    public Account() {
    }

    public Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
//
//    public List<Customer> getCustomers() {
//        return customers;
//    }
//
//    public void setCustomers(List<Customer> customers) {
//        this.customers = customers;
//    }



    public void deposit(double amount) throws BusinessException{
        if(amount < 0){
            throw new BusinessException("Invalid amount entered.");
        }
        this.balance += amount;
    }
    public void withdraw(double amount) throws BusinessException{
        if (amount <= this.balance){
            this.balance -= amount;
        } else {
            throw new BusinessException("Insufficient funds.");
        }
    }


    public Transaction transfer(Customer receiver, double amount) throws BusinessException {
        // Try to withdraw from payer's account
        try {
            this.withdraw(amount);
            List<Account> payeeAccounts = receiver.getAccounts();
            // If successful, find the receiver's bank account and deposit the money
            Account receiverAccount = payeeAccounts.get(0);
            receiverAccount.deposit(amount);

            //Update both accounts in the db and store the transaction object in the Transaction table
            Transaction transaction = new Transaction(this, receiverAccount,amount );
            return transaction;
        } catch(BusinessException e){
            loggy.error(e.getMessage());
        }
        throw new BusinessException("Transfer could not be completed.");
    }



}

