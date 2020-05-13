package com.banking.dao.impl;

import com.banking.dao.CustomerDao;
import com.banking.models.Customer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDaoImplTest {

    private static CustomerDao dao;
    private static Customer customer;

    @BeforeClass
    public static void createCustomerDao(){
        dao = new CustomerDaoImpl();
    }
    @BeforeClass
    public static Customer testCreateTestCustomer(){
        customer = new Customer("MBU2343","testuser","testpass");
        return customer;
    }
    @Test

    void createCustomer() {

    }

    @Test
    void getCustomerByUsername() {

    }

    @Test
    void getCustomerByLogin() {
    }

    @Test
    void getAllCustomers() {
    }

    @Test
    void getCustomerById() {
    }

    @AfterClass
    public static void destroyCustomerDao(){
        dao = null;
    }
}