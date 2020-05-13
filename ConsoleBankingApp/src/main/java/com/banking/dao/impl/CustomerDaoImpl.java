package com.banking.dao.impl;

import com.banking.dao.CustomerDao;
import com.banking.dbutil.OracleConnection;
import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.*;

public class CustomerDaoImpl implements CustomerDao {

    final static Logger loggy = Logger.getLogger(CustomerDaoImpl.class);

    @Override
    public Customer createCustomer(Customer customer) throws BusinessException {

        try(Connection connection = OracleConnection.getConnection()){
            String sql= "{call CREATECUSTOMERANDACCOUNT(?, ?, ?)}";

            //fill in the ?'s starting index at 1

            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, customer.getUsername());
            callableStatement.setString(2, customer.getPassword());


            //TODO add multiple accounts via foreach
            List<Account> accounts = customer.getAccounts();
            callableStatement.setDouble(3, accounts.get(0).getBalance());

            //register id because it is an OUT param
            //callableStatement.registerOutParameter(1, Types.VARCHAR);

            callableStatement.execute();

            getCustomerByLogin(customer);

            //callableStatement should have executed and now contains the ID param

//           customer.setId(callableStatement.getString(1));
            loggy.info("Customer account successfully created.");

        } catch (SQLException | ClassNotFoundException e) {
            throw new BusinessException("Internal error. Please don't panic.");
        }
        return customer;
    }

    @Override
    public Customer getCustomerByUsername(String Username) throws BusinessException {
        Customer c = null;

        try(Connection connection=OracleConnection.getConnection()){
            String sql="Select customer.id AS userID, customer.username, account.balance, " +
                    "account.id AS accountID " +
                    "FROM Customer " +
                    "JOIN Account ON Account.id = customer.accountid " +
                    "WHERE username = ?";

            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1, Username);

            ResultSet resultSet=ps.executeQuery();

            List<Account> accounts = new ArrayList<>();
            Account a = null;
            if(resultSet.next()) {
                c = new Customer();
                a = new Account();
                c.setId(resultSet.getString("userID"));
                c.setUsername(resultSet.getString("username"));
                a.setId(resultSet.getString("accountID"));
                a.setBalance(resultSet.getDouble("balance"));
                accounts.add(a);
                c.setAccounts(accounts);
                return c;
            }else {
                throw new BusinessException(Username +" does not exist");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new BusinessException("Internal Error, please don't panic.");

        }
    }

    @Override
    public Customer getCustomerByLogin(Customer customer) throws BusinessException {

        Customer c = null;

        try(Connection connection=OracleConnection.getConnection()){
//            String sql="Select id, username from customer where username = ? AND password = ?";
            String sql="Select customer.id AS userID, customer.username, account.balance, " +
                    "account.id AS accountID " +
                    "FROM Customer " +
                    "JOIN Account ON Account.id = customer.accountid " +
                    "WHERE username = ? AND password = ? ";

            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1, customer.getUsername());
            ps.setString(2, customer.getPassword());

            ResultSet resultSet=ps.executeQuery();

            List<Account> accounts = new ArrayList<>();
            Account a = null;
            if(resultSet.next()) {
                c = new Customer();
                a = new Account();
                c.setId(resultSet.getString("userID"));
                c.setUsername(resultSet.getString("username"));
                a.setId(resultSet.getString("accountID"));
                a.setBalance(resultSet.getDouble("balance"));
                accounts.add(a);
                c.setAccounts(accounts);
//                loggy.info("\nYou have been logged in. \nWelcome " + c.getUsername());
                return c;
            }else {
                throw new BusinessException("Username "+ customer.getUsername() +" does not exist");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new BusinessException("Internal Error, please don't panic.");
        }
    }

    @Override
    public void deleteCustomer(String Username) throws BusinessException {

    }

    @Override
    public List<Customer> getAllCustomers() throws BusinessException {
        List<Customer> accountList=new ArrayList<>();
        try(Connection connection=OracleConnection.getConnection()){
            String sql="Select customer.id AS userID, customer.username, account.balance, " +
                    "account.id AS accountID " +
                    "FROM Customer " +
                    "JOIN Account ON Account.id = customer.accountid";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                Customer c = new Customer();
                Account a = new Account();
                List<Account> accounts = new ArrayList<>();

                c.setId(resultSet.getString("userID"));
                c.setUsername(resultSet.getString("Username"));
                a.setId(resultSet.getString("accountID"));
                a.setBalance(resultSet.getDouble("balance"));
                accounts.add(a);
                c.setAccounts(accounts);
                accountList.add(c);
            }
            return accountList;
        } catch (ClassNotFoundException | SQLException e) {
            throw new BusinessException("Internal Error contact SYSADMIN");
        }
    }

    @Override
    public Customer getCustomerById(String id) throws BusinessException {
        Customer c=null;
        try(Connection connection=OracleConnection.getConnection()){
            String sql="Select customer.id AS userID, customer.username, account.balance, " +
                    "account.id AS accountID " +
                    "FROM Customer " +
                    "JOIN Account ON Account.id = customer.accountid " +
                    "WHERE customer.id = ?";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1, id);


            ResultSet resultSet=ps.executeQuery();

            Account account = new Account();

            if(resultSet.next()) {
                c = new Customer();
                c.setId(resultSet.getString("userID"));
                c.setUsername(resultSet.getString("username"));
                account.setId(resultSet.getString("accountID"));
                account.setBalance(resultSet.getDouble("balance"));
                c.setAccount(account);
                return c;
            }else {
                throw new BusinessException("User "+ id+" does not exist");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new BusinessException("Internal Error, please don't panic.");
        }
    }

//    @Override
//    public Customer updateBalance(Customer customer) throws BusinessException {
//        try (Connection connection = OracleConnection.getConnection()) {
//            String sql = "{UPDATE CUSTOMER SET BALANCE = ? WHERE ID = ?}";
//            CallableStatement callableStatement = connection.prepareCall(sql);
//            callableStatement.setDouble(1, customer.getBalance());
//            callableStatement.setString(2, customer.getId());
//
//            callableStatement.execute();
//
//
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new BusinessException("Internal error. Please don't panic.");
//        }
//        return customer;
//    }
}
