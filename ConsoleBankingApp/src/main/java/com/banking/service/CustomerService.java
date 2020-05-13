package com.banking.service;

import com.banking.exception.BusinessException;
import com.banking.models.Customer;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(Customer customer) throws BusinessException;
    Customer customerLogin(Customer customer) throws BusinessException;
    Customer getCustomerById(String id) throws BusinessException;
    Customer getCustomerByUsername(String username) throws BusinessException;
    Customer updateCustomerPassword(String id, String password) throws BusinessException;
    void deleteCustomer(String id) throws BusinessException;
    List<Customer> getAllCustomers() throws BusinessException;

    boolean isValidPassword(String string);

    boolean isValidUsername(String string);
}
