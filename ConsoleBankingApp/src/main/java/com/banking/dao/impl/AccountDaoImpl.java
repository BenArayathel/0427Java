package com.banking.dao.impl;

import com.banking.dao.AccountDao;
import com.banking.dbutil.OracleConnection;
import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.*;

public class AccountDaoImpl implements AccountDao {

    final static Logger loggy = Logger.getLogger(AccountDaoImpl.class);

    public Account createAccount(Customer customer) throws BusinessException{
        return null;
    }
    public Account getAccountById(String accountId) throws BusinessException{
        return null;
    }
    public Account getAccountByUser(Customer customer) throws BusinessException{
        return null;
    }

    @Override
    public Account updateAccountBalance(Account account) throws BusinessException {
        try(Connection connection= OracleConnection.getConnection()){
            String sql="UPDATE account SET balance = ? WHERE id = ?";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setDouble(1, account.getBalance());
            ps.setString(2, account.getId());

            ResultSet resultSet=ps.executeQuery();
            return account;
        } catch (ClassNotFoundException | SQLException e) {
            throw new BusinessException("Internal Error, please don't panic.");
        }
    }

}
