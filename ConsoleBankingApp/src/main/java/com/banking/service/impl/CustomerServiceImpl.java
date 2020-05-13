package com.banking.service.impl;

import com.banking.dao.CustomerDao;
import com.banking.dao.impl.CustomerDaoImpl;
import com.banking.exception.BusinessException;
import com.banking.models.Customer;
import com.banking.service.CustomerService;

import java.util.List;
import org.apache.log4j.*;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao dao = new CustomerDaoImpl();
    final static Logger loggy = Logger.getLogger(CustomerServiceImpl.class);


    @Override
    public Customer createCustomer(Customer customer) throws BusinessException{

        try{
            validateCustomer(customer);
            // Call the DAO
            dao.createCustomer(customer);
        } catch(BusinessException e){
            throw new BusinessException("Customer could not be created.");
        }
        return customer;
    }

    @Override
    public Customer customerLogin(Customer customer) throws BusinessException {

        // this is a basic Get customer from Database
        try{
            validateCustomer(customer);
            customer = dao.getCustomerByLogin(customer);
            return customer;
        }catch(BusinessException e) {
            loggy.error(e.getMessage());
        }
        throw new BusinessException("Customer was not found.");

    }

    public void validateCustomer(Customer customer) throws BusinessException {
        if(customer == null){
            throw new BusinessException("Customer Object was not found.");
        } else if (!isValidUsername(customer.getUsername())){
            throw new BusinessException("Username is invalid.");
        } else if (!isValidPassword(customer.getPassword())) {
            throw new BusinessException("Password is invalid. Password must be between 4 - 20 characters, can contain numbers.");
        }
    }


    public boolean isValidUsername(String username){
        boolean b = false;
        if (username.matches("[a-zA-Z0-9]{2,16}")){
            b = true;
        }
        return b;
    }
    public boolean isValidId(String id){
        boolean b = false;
        if (id.matches("MBU[A-Z]{3}[0-9]{4}")){
            b = true;
        }
        return b;
    }

    public boolean isValidPassword(String password){
        boolean b = false;
        if (password.matches("[a-zA-Z0-9]{4,20}")){
            b = true;
        }
        return b;
    }
//    private boolean isValidPhone(long phone){
//        boolean b = false;
//        if ((phone + "").matches("[0-9]{10}")){
//            b = true;
//        }
//        return b;
//    }
//    private boolean isValidCity(String city){
//        boolean b = false;
//        if (city.matches("[a-zA-Z]{2,11}")){
//            b = true;
//        }
//        return b;
//    }
//    private boolean isValidEmail(String email){
//        boolean b = false;
//        if (email.matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")){
//            b = true;
//        }
//        return b;
//    }



    @Override
    public Customer getCustomerById(String id) throws BusinessException {
        try{
            Customer customer = dao.getCustomerById(id);
            return customer;
        } catch (BusinessException e) {
            loggy.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Customer getCustomerByUsername(String username) throws BusinessException {
        try{
            Customer customer = dao.getCustomerByUsername(username);
            return customer;
        } catch (BusinessException e) {
            loggy.error(e.getMessage());
        }
        return null;
    }


    @Override
    public Customer updateCustomerPassword(String id, String Password) throws BusinessException {
        return null;
    }

    @Override
    public void deleteCustomer(String id) throws BusinessException {

    }

    @Override
    public List<Customer> getAllCustomers() throws BusinessException {
        try{
            List<Customer> accountList = dao.getAllCustomers();
            return accountList;
        } catch (BusinessException e) {
            loggy.error(e.getMessage());
        }
        return null;
    }

}
