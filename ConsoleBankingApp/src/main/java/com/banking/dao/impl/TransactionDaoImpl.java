package com.banking.dao.impl;

import com.banking.dao.TransactionDao;
import com.banking.dbutil.OracleConnection;
import com.banking.exception.BusinessException;
import com.banking.models.Account;
import com.banking.models.Employee;
import com.banking.models.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.*;

public class TransactionDaoImpl implements TransactionDao {

    final static Logger loggy = Logger.getLogger(TransactionDaoImpl.class);
    @Override
    public void createTransaction(Transaction transaction) throws BusinessException {

        try (Connection connection = OracleConnection.getConnection()) {
            String sql = "{call CREATETRANSACTION(?, ?, ?, ?)}";

            //fill in the ?'s

            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.setString(2, transaction.getSender().getId());
            callableStatement.setString(3, transaction.getReceiver().getId());
            callableStatement.setDouble(4, transaction.getAmount());
            //register id because it is an OUT param
            callableStatement.registerOutParameter(1, Types.VARCHAR);

            callableStatement.execute();

            //callableStatement should have executed and now contains the ID param

//           customer.setId(callableStatement.getString(1));
            loggy.info("Customer account successfully created.");

        } catch (SQLException | ClassNotFoundException e) {
            throw new BusinessException("Internal error. Please don't panic.");
        }
    }

    @Override
    public List<Transaction> getAllTransactions() throws BusinessException {
        List<Transaction> transactionList = new ArrayList<>();
        try(Connection connection = OracleConnection.getConnection()){
            String sql = "Select * from transaction";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                    Transaction t = new Transaction();
                    Account sender = new Account();
                    Account receiver = new Account();

                    sender.setId(rs.getString("senderId"));
                    receiver.setId(rs.getString("receiverId"));
                    t.setId(rs.getString("id"));
                    t.setSender(sender);
                    t.setReceiver(receiver);
                    t.setAmount(rs.getDouble("amount"));

                    transactionList.add(t);
                }
        } catch(ClassNotFoundException | SQLException e){
            loggy.error(e.getMessage());
        }
        return transactionList;
    }


}


//    @Override
//        public List<Transaction> getAllTransactions() throws BusinessException {
//            List<Transaction> transactionList = new ArrayList<>();
//            try (Connection connection = OracleConnection.getConnection()) {
//                String sql = "SELECT id, senderid, receiverid, amount FROM TRANSACTION";
//                PreparedStatement ps = connection.prepareStatement(sql);
//                ResultSet resultSet = ps.executeQuery();
//
//                while(resultSet.next()) {
//                    Transaction t = new Transaction();
//                    Account sender = new Account();
//                    Account receiver = new Account();
//
//                    sender.setId(resultSet.getString("sender"));
//                    receiver.setId(resultSet.getString("receiver"));
//                    t.setId(resultSet.getString("id"));
//                    t.setSender(sender);
//                    t.setReceiver(receiver);
//                    t.setAmount(resultSet.getDouble("amount"));
//
//                    transactionList.add(t);
//                }
//                return transactionList;
//            } catch (ClassNotFoundException | SQLException e) {
//                throw new BusinessException("Internal Error, please don't panic.");
//            }
//    }
//}
