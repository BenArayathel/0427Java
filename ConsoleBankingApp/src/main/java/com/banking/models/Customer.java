package com.banking.models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    public String id;
    private List<Account> accounts = new ArrayList<>();


    public Customer() {
        super();
    }

    public Customer(String id, String username){
        super(username);
        this.id = id;
    };

    public Customer(String id, String username, String password) {
        super(username, password);
        this.id = id;

    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", accounts=" + accounts +
                ", username='" + username + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setAccount(Account account){
        List<Account> newAccounts = new ArrayList<>();
        newAccounts.add(account);
        this.accounts = newAccounts;
    }
//    public double getBalance(int index){
//        //customer's can only have 1 bank account right now.
//        return this.accounts.get(index).getBalance();
//    }

}
