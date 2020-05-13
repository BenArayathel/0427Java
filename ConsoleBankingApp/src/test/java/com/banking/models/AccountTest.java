package com.banking.models;

import com.banking.service.impl.CustomerServiceImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private static Account account;

    @BeforeClass
    public static void createAccount(){
        account = new Account();
    }

    @Test
    void testDeposit() {


    }

    @Test
    void testWithdraw() {

    }

    @Test
    void testTransfer() {
    }

    @AfterClass
    void destroyAccount(){
        account = null;
    }
}