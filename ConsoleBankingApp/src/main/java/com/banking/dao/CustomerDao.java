package com.banking.dao;

import com.banking.exception.BusinessException;
import com.banking.models.Customer;

import java.util.List;

public interface CustomerDao {

    Customer createCustomer(Customer customer) throws BusinessException;
    Customer getCustomerByUsername(String username) throws BusinessException;
    Customer getCustomerByLogin(Customer customer) throws BusinessException;
    void deleteCustomer(String id) throws BusinessException;
    List<Customer> getAllCustomers() throws BusinessException;
    Customer getCustomerById(String id) throws BusinessException;
}
