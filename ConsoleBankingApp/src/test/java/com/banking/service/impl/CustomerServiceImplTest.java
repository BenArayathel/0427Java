package com.banking.service.impl;

import com.banking.service.CustomerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {

    private static CustomerService service;

    @Test
    void isValidUsername() {
        //("[a-zA-Z0-9]{2,16}")
        service = new CustomerServiceImpl();
        assertTrue(service.isValidUsername("ValIDUser4"));
        assertFalse(service.isValidUsername("%1nVa1dUsr "));
        assertFalse(service.isValidUsername(" "));
    }

    @Test
    void isValidId() {
    }

    @Test
    void isValidPassword() {
        //("[a-zA-Z0-9]{4,20}")
        service = new CustomerServiceImpl();
        assertTrue(service.isValidPassword("validpassword"));
        assertTrue(service.isValidPassword("ValIDp4ss"));
        assertFalse(service.isValidPassword("%1nVa1dp455 "));
        assertFalse(service.isValidPassword(" "));
    }
}