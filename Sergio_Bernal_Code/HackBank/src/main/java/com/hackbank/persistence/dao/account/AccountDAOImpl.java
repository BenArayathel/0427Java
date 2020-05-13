package com.hackbank.persistence.dao.account;

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
import com.hackbank.persistence.dbutil.SingletonDBConnection;
import com.hackbank.persistence.models.Account;
import com.hackbank.persistence.models.AccountType;
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
//			call.registerOutParameter("ID", java.sql.Types.VARCHAR);
//			call.setString("PERSON_ID", pApproval.getPerson().getId());
//			call.setByte("ACCOUNT_TYPE_ID", pApproval.getAccountType().getId());
//			call.registerOutParameter("ROUTING_NUMBER", java.sql.Types.VARCHAR);
//			call.setDouble("BALANCE", pApproval.getStartBalance());
			
			call.setString(2, pApproval.getPerson().getId());
			call.setByte(3, pApproval.getAccountType().getId());
			call.setDouble(5, pApproval.getStartBalance());
			
			call.registerOutParameter(1, java.sql.Types.NUMERIC);
			call.registerOutParameter(4, java.sql.Types.NUMERIC);
			
			call.execute();
			
			iAccount = new Account();
			iAccount.setId(call.getString(1));
			iAccount.setRoutingNumber(call.getString(4));
			iAccount.setPerson(pApproval.getPerson());
			iAccount.setBalance(pApproval.getStartBalance());
			iAccount.setAccountType(pApproval.getAccountType());
			iAccount.setAccountTypeId(pApproval.getAccountType().getId());

		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("SQL Error, please contact SYSADMIN");
			throw new BusinessException("SQL Error, please contact SYSADMIN");
		}
		return iAccount;
	}
	
	@Override
	public String getPersonAccountById(String id) throws BusinessException {
		String personId = "";
		try(Connection conn = SingletonDBConnection.getConnection()){
			String sql = "SELECT person_id FROM HB_ACCOUNT WHERE id = ?";
			PreparedStatement prepared = conn.prepareStatement(sql);
			prepared.setString(1, id);
			
			ResultSet result = prepared.executeQuery();
			if(result.next()) {
				personId = result.getString("person_id");
			}else {
				throw new BusinessException("Account Number: "+id+" not found.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("SQL Error, please contact SYSADMIN");
			throw new BusinessException("SQL Error, please contact SYSADMIN");
		}
		return personId;
	}

	@Override
	public Account updateBalanceAccount(String id, double balance) throws BusinessException {
		Account account = null;
		String sql = "UPDATE HB_ACCOUNT SET BALANCE = ? WHERE ID = ?";
		try(Connection conn = SingletonDBConnection.getConnection()){
			PreparedStatement prepared = conn.prepareStatement(sql);
			prepared.setDouble(1, balance);
			prepared.setString(2, id);
			
			int result = prepared.executeUpdate();
			if (result > 0) {
				account = getAccountById(id);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("SQL Error, please contact SYSADMIN");
			throw new BusinessException("SQL Error, please contact SYSADMIN");
		}
		return account;
	}

	@Override
	public List<Account> getAllAccountsByCustomer(String id) throws BusinessException {
		List<Account> listAccount = new ArrayList<>();
		String sql = "SELECT ha.id, hat.id, hat.name, ha.routing_number, ha.balance "
					+ "FROM HB_ACCOUNT ha INNER JOIN HB_ACCOUNT_TYPE hat ON ha.account_type_id = hat.id "
					+ "WHERE person_id = ?";
		try(Connection conn = SingletonDBConnection.getConnection()){
			PreparedStatement prepared = conn.prepareStatement(sql);
			prepared.setString(1, id);
			
			ResultSet result = prepared.executeQuery();
			while(result.next()) {
				AccountType at = new AccountType(result.getByte(2), result.getString(3));
				listAccount.add(new Account(result.getString(1), at, result.getString(4), result.getDouble(5)));
			}
		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("SQL Error, please contact SYSADMIN");
			throw new BusinessException("SQL Error, please contact SYSADMIN");
		}
		return listAccount;
	}

	@Override
	public Account getAccountById(String id) throws BusinessException {
		Account account = null;
		String sql = "SELECT ha.id, hat.id, hat.name, ha.routing_number, ha.balance "
					+ "FROM HB_ACCOUNT ha INNER JOIN HB_ACCOUNT_TYPE hat ON ha.account_type_id = hat.id "
					+ "WHERE ha.id = ?";
		try(Connection conn = SingletonDBConnection.getConnection()){
			PreparedStatement prepared = conn.prepareStatement(sql);
			prepared.setString(1, id);
			
			ResultSet result = prepared.executeQuery();
			while(result.next()) {
				AccountType at = new AccountType(result.getByte(2), result.getString(3));
				account = new Account(result.getString(1), at, result.getString(4), result.getDouble(5));
			}
		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("SQL Error, please contact SYSADMIN");
			throw new BusinessException("SQL Error, please contact SYSADMIN");
		}
		return account;
	}

	@Override
	public boolean getAccountByIdAndRoutingNumber(String id, String routingNumber) throws BusinessException {
		boolean flag = false;
		String sql = "SELECT id FROM HB_ACCOUNT WHERE id = ? AND routing_number = ?";
		try(Connection conn = SingletonDBConnection.getConnection()){
			PreparedStatement prepared = conn.prepareStatement(sql);
			prepared.setString(1, id);
			prepared.setString(2, routingNumber);
			
			ResultSet result = prepared.executeQuery();
			while(result.next()) {
				flag = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("SQL Error, please contact SYSADMIN");
			throw new BusinessException("SQL Error, please contact SYSADMIN");
		}
		return flag;
	}
	
}
