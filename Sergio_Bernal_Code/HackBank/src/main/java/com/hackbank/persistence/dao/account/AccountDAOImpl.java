package com.hackbank.persistence.dao.account;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.dbutil.SingletonDBConnection;
import com.hackbank.persistence.models.Account;
import com.hackbank.persistence.models.PendingApproval;

public class AccountDAOImpl implements AccountDAO{
	
	final static Logger loggy = Logger.getLogger(AccountDAOImpl.class);

	public static void main(String[] args) {
		loggy.setLevel(Level.INFO);
	}
	
	@Override
	public Account createAccount(PendingApproval pApproval) throws BusinessException {
		Account iAccount = null;
		try(Connection conn = SingletonDBConnection.getConnection()){
			String query = "{CALL CREATE_ACCOUNT(?,?,?,?,?)}";
			CallableStatement call = conn.prepareCall(query);
			call.setString("PERSON_ID", pApproval.getPerson().getId());
			call.setShort("ACCOUNT_TYPE_ID", pApproval.getAccountTypeId());
			call.setDouble("BALANCE", pApproval.getStartBalance());
			call.registerOutParameter("ID", java.sql.Types.NUMERIC);
			call.registerOutParameter("ROUTING_NUMBER", java.sql.Types.NUMERIC);
			
			ResultSet result = call.executeQuery();
			if(result.next()) {
				iAccount = new Account();
				iAccount.setId(result.getInt("ID"));
				iAccount.setRoutingNumber(result.getInt("ROUTING_NUMBER"));
				iAccount.setPerson(pApproval.getPerson());
				iAccount.setBalance(pApproval.getStartBalance());
				iAccount.setAccountTypeId(pApproval.getAccountTypeId());
			}
		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("Fatal Error contact SYSADMIN");
			throw new BusinessException("Fatal Error contact SYSADMIN");
		}
		return iAccount;
	}

	@Override
	public String getAccountById(int id) throws BusinessException {
		String personId = "";
		try(Connection conn = SingletonDBConnection.getConnection()){
			String sql = "SELECT person_id FROM HB_ACCOUNT WHERE id = ?";
			PreparedStatement prepared = conn.prepareStatement(sql);
			prepared.setInt(1, id);
			
			ResultSet result = prepared.executeQuery();
			if(result.next()) {
				personId = result.getString("person_id");
			}else {
				throw new BusinessException("Account Number: "+id+" not found.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("Fatal Error contact SYSADMIN");
			throw new BusinessException("Fatal Error contact SYSADMIN");
		}
		return personId;
	}

	@Override
	public Account updateBalanceAccount(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllAccountsByCustomer(String id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
