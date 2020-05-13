package com.hackbank.persistence.dao.transfer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.dao.account.AccountDAO;
import com.hackbank.persistence.dao.account.AccountDAOImpl;
import com.hackbank.persistence.dbutil.SingletonDBConnection;
import com.hackbank.persistence.models.Transfer;

public class TransferDAOImpl implements TransferDAO{

	final static Logger loggy = Logger.getLogger(TransferDAOImpl.class);
	final static AccountDAO accountDAO = new AccountDAOImpl();
	
	public static void main(String[] args) {
		loggy.setLevel(Level.INFO);
	}
	
	@Override
	public boolean createTransfer(Transfer transfer) throws BusinessException {
		boolean flag = false;
		String sql = "{CALL CREATE_TRANSFER(?,?,?,?,?,?,?)}";
		try(Connection conn = SingletonDBConnection.getConnection()){
			CallableStatement call = conn.prepareCall(sql);
			call.setString(1, transfer.getIniAcccountNumber());
			call.setString(2, transfer.getEndAccountNumber());
			call.setDate(3, new java.sql.Date(transfer.getCreatedAt().getTime()));
			call.setString(4, transfer.getStatus());
			call.setString(5, transfer.getIniRoutingNumber());
			call.setString(6, transfer.getEndRoutingNumber());
			call.setDouble(7, transfer.getAmount());
			
			call.execute();
			flag = true;
		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("SQL Error, please contact SYSADMIN");
			throw new BusinessException("SQL Error, please contact SYSADMIN");
		}
		return flag;
	}

	@Override
	public boolean acceptTransfer(Transfer transfer) throws BusinessException {
		boolean flag = false;
		String sql = "UPDATE HB_TRANSFER SET STATUS = ? WHERE ID = ?";
		try(Connection conn = SingletonDBConnection.getConnection()){
			PreparedStatement prepared = conn.prepareStatement(sql);
			prepared.setString(1, transfer.getStatus());
			prepared.setInt(2, transfer.getId());
			int result = prepared.executeUpdate();
			if(result > 0) {
				flag = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("SQL Error, please contact SYSADMIN");
			throw new BusinessException("SQL Error, please contact SYSADMIN");
		}

		return flag;
	}

	@Override
	public List<Transfer> transferList(String id) throws BusinessException {
		List<Transfer> list = new ArrayList<>();
		String sql = "SELECT ht.id, INIT_ACCOUNT_NUMBER , END_ACCOUNT_NUMBER, CREATED_AT, STATUS, INIT_ROUTING_NUMBER, END_ROUTING_NUMBER, AMOUNT " + 
					"FROM HB_TRANSFER ht INNER JOIN HB_ACCOUNT ha ON ha.ID = ht.INIT_ACCOUNT_NUMBER AND ha.ROUTING_NUMBER = ht.INIT_ROUTING_NUMBER " + 
					"WHERE ht.status = 'Pending' AND ha.PERSON_ID = ?";
		try(Connection conn = SingletonDBConnection.getConnection()){
			PreparedStatement prepared = conn.prepareStatement(sql);
			prepared.setString(1, id);
			
			ResultSet result = prepared.executeQuery();
			while(result.next()) {
				System.out.println();
				Transfer iTransfer = new Transfer();
				iTransfer.setId(result.getInt(1));
				iTransfer.setIniAcccountNumber(result.getString(2));
				iTransfer.setEndAccountNumber(result.getString(3));
				iTransfer.setCreatedAt(result.getDate(4));
				iTransfer.setStatus(result.getString(5));
				iTransfer.setIniRoutingNumber(result.getString(6));
				iTransfer.setEndRoutingNumber(result.getString(7));
				iTransfer.setAmount(result.getDouble(8));
				list.add(iTransfer);
			}
		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("SQL Error, please contact SYSADMIN");
			throw new BusinessException("SQL Error, please contact SYSADMIN");
		}
		return list;
	}

}
