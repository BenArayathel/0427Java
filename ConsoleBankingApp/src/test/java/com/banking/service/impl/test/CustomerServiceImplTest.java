package com.banking.service.impl.test;

import com.banking.service.CustomerService;
import com.banking.service.impl.CustomerServiceImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class CustomerServiceImplTest {


    private static CustomerService service;

    @BeforeClass
    public static void createCustomerServiceObject(){
        service = new CustomerServiceImpl();
    }

    @AfterClass
    public static void destroyCustomerServiceObject(){
        service = null;
    }
}
