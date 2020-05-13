package com.banking.models;

public class Transaction {
    String id;
    Account sender;
    Account receiver;
    double amount;
    //TODO add a date timestamp to front and back end

    public Transaction() {
    }

    public Transaction(Account sender, Account receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public Transaction(String id, Account sender, Account receiver, double amount) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
