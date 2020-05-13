package com.banking.service.impl;

import com.banking.dao.AccountDao;
import com.banking.dao.CustomerDao;
import com.banking.dao.impl.AccountDaoImpl;
import com.banking.dao.impl.CustomerDaoImpl;
import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Employee;
import com.banking.service.AccountService;

import org.apache.log4j.*;


public class AccountServiceImpl implements AccountService {


    private AccountDao dao = new AccountDaoImpl();
    final static Logger loggy = Logger.getLogger(AccountServiceImpl.class);

    @Override
    public Account createAccount(Account account) throws BusinessException {
        return null;
    }

    @Override
    public Account getAccount(Account account) throws BusinessException {
        return null;
    }

    @Override
    public Account updateBalance(Account account) throws BusinessException {
        try{
            validatesAccount(account);
        //may need to validate balance input here
            account = dao.updateAccountBalance(account);
            return account;
        } catch(BusinessException e) {
            loggy.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteAccount(Account account) throws BusinessException {

    }
    public void validatesAccount(Account account) throws BusinessException {
        if(account == null){
            throw new BusinessException("Account was not found.");
        } else if (!isValidId(account.getId())){
            throw new BusinessException("AccountID is invalid.");
        }
    }

    private boolean isValidId(String id){
        boolean b = false;
        if (id.matches("MBA[0-9]{4}")){
            b = true;
        }
        return b;
    }
    private boolean isValidDeposit(double deposit){
        boolean b = false;
        if (deposit > 0){
            b = true;
        }
        return b;
    }
    private boolean isValidWithdrawal(double withdrawal, double balance){
        boolean b = false;
        if (withdrawal <= balance ){
            b = true;
        }
        return b;
    }

}
