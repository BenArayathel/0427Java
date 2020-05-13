package com.banking.service.impl;

import com.banking.dao.CustomerDao;
import com.banking.dao.impl.CustomerDaoImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private static EmployeeServiceImpl service;

    @BeforeClass
    public static void createEmployeeServiceImpl(){
        service = new EmployeeServiceImpl();
    }

    @Test
    void employeeLogin() {


    }

    @Test
    void validatesEmployee() {
    }

    @Test
    void isValidUsername() {
        //("[a-zA-Z0-9]{2,10}")
        service = new EmployeeServiceImpl();
        assertTrue(service.isValidUsername("ValIDUser4"));
        assertFalse(service.isValidUsername("%1nVa1dUsr "));
        assertFalse(service.isValidUsername(" "));
    }

    @Test
    void isValidPassword() {
        //("[a-zA-Z0-9]{4,20}")
        service = new EmployeeServiceImpl();
        assertTrue(service.isValidPassword("validpassword"));
        assertTrue(service.isValidPassword("ValIDp4ss"));
        assertFalse(service.isValidPassword("%1nVa1dp455 "));
        assertFalse(service.isValidPassword(" "));
    }

    @AfterClass
    public static void destroyEmployeeServiceImpl(){
        service = null;
    }
}