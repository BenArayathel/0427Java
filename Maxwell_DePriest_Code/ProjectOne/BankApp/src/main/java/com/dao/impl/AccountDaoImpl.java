package com.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.dao.AccountDao;
import com.exceptions.BusinessException;
import com.models.Account;
import com.utilities.DatabaseConnection;

public class AccountDaoImpl implements AccountDao {
	final static Logger loggy = Logger.getLogger(Account.class);
	DecimalFormat df = new DecimalFormat("####.##");

	@Override
	public Account insertAccount(Account a) throws BusinessException{
		try (Connection conn = DatabaseConnection.getConn()) {
			PreparedStatement ps = conn.prepareStatement("call CREATEBANKACCOUNT(?,?,?,?,?,?,?)");
			
			ps.setString(1, "");
			ps.setString(2, a.getSavingsAccountNumber());
			ps.setString(3, a.getCheckingAccountNumber());
			ps.setString(4, a.getCheckingBalance());
			ps.setString(5, a.getSavingsBalance());
			ps.setString(6, a.getActive());
			ps.setString(7, a.getEmail());
			
			ps.execute();
			
			loggy.debug("Creating new account with email " + a.getEmail());
		} catch (SQLException e) {
			loggy.error("SQLException- " + e);
			throw new BusinessException("Internal Error. Please contact SYSADMIN");
		}
		return a;
		
		
	}

	@Override
	public Account selectAccountByEmail(String email) throws BusinessException{
		Account a = new Account();
		try (Connection conn = DatabaseConnection.getConn()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM bankaccount WHERE email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				a.setId(rs.getString("id"));
				a.setSavingsAccountNumber(rs.getString("savingsnumber"));
				a.setCheckingAccountNumber(rs.getString("checkingnumber"));
				a.setCheckingBalance(rs.getString("checkingbalance"));
				a.setSavingsBalance(rs.getString("savingsbalance"));
				a.setActive(rs.getString("active"));
				a.setEmail(rs.getString("email"));
			}	
			loggy.debug("Retrieved account information for email-" + a.getEmail());
		} catch (SQLException e) {
			e.printStackTrace();
			loggy.warn("Caught SQLException");
			e.printStackTrace();
			throw new BusinessException("Internal Error while updating. Contact SYSADMIN");
			
		}
		return a;
	
	}// End of selectByEmail
	
	public Account selectAccountByColumnName(String cName, String cValue) throws BusinessException{
		Account a = new Account();
		try (Connection conn = DatabaseConnection.getConn()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM bankaccount WHERE " + cName + " = '" + cValue + "'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				a.setId(rs.getString("id"));
				a.setSavingsAccountNumber(rs.getString("savingsnumber"));
				a.setCheckingAccountNumber(rs.getString("checkingnumber"));
				a.setCheckingBalance(rs.getString("checkingbalance"));
				a.setSavingsBalance(rs.getString("savingsbalance"));
				a.setActive(rs.getString("active"));
				a.setEmail(rs.getString("email"));
			}	
			loggy.debug("Retrieved all accounts");
		} catch (SQLException e) {
			e.printStackTrace();
			loggy.error("Caught SQLException " + e);
			e.printStackTrace();
			throw new BusinessException("Internal Error. Please contact SYSADMIN");
			
		}
		return a;
	}

	@Override
	public List<Account> selectAllAccounts() throws BusinessException{
		List<Account> aList = new ArrayList<>();
		try (Connection conn = DatabaseConnection.getConn()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM bankaccount");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				aList.add(new Account(rs.getString("id"), rs.getString("savingsnumber"), rs.getString("checkingnumber"), 
						rs.getString("checkingbalance"), rs.getString("savingsbalance"), rs.getString("active"), rs.getString("email")));
			}	
			loggy.debug("Retrieved all accounts");
		} catch (SQLException e) {
			e.printStackTrace();
			loggy.error("Caught SQLException " + e);
			e.printStackTrace();
			throw new BusinessException("Internal Error while updating. Contact SYSADMIN");
			
		}
		return aList;
	
	}
	
	public List<Account> selectAllAccountsByColumnName(String cName, String cValue) throws BusinessException{
		List<Account> filteredAccountList = new ArrayList<>();
		try (Connection conn = DatabaseConnection.getConn()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM bankaccount WHERE " + cName + " = '" + cValue + "'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				filteredAccountList.add(new Account(rs.getString("id"), rs.getString("savingsnumber"), rs.getString("checkingnumber"), 
						rs.getString("checkingbalance"), rs.getString("savingsbalance"), rs.getString("active"), rs.getString("email")));
			}	
			loggy.debug("Retrieved all accounts");
		} catch (SQLException e) {
			e.printStackTrace();
			loggy.error("Caught SQLException " + e);
			e.printStackTrace();
			throw new BusinessException("Internal Error. Please contact SYSADMIN");
			
		}
		return filteredAccountList;
	}// End of selectAllByColumn

	@Override
	public boolean updateAccount(String userEmail, String columnName, String newAtt) throws BusinessException{
		Account a = new Account();
		try (Connection conn = DatabaseConnection.getConn()) {
			//PreparedStatement ps = conn.prepareStatement("UPDATE bankaccount SET ? = ? WHERE email = ?");
			PreparedStatement ps = conn.prepareStatement("UPDATE bankaccount SET " + columnName + "  = '" + newAtt + "' WHERE email = '"+ userEmail +"'");
//			ps.setString(1, columnName);
//			ps.setString(2, newAtt);
//			ps.setString(3, userEmail);
			
			ps.executeUpdate();
			loggy.debug("Account with email " + userEmail + " updated.");
			return true;
			
		} catch (SQLException e) {
			loggy.error("Caught SQLException- " + e);
			e.printStackTrace();
			throw new BusinessException("Internal Error. Contact SYSADMIN");
		}
		
		
	}
	
	@Override
	public void deleteAccount(String email) throws BusinessException{
		try (Connection conn = DatabaseConnection.getConn()) {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM bankaccount WHERE email = ?");
			ps.setString(1, email);
			ps.execute();
			loggy.debug("Deleting record with email- " + email);
		} catch (SQLException e) {
			loggy.error("Caught SQLException- " + e);
			e.printStackTrace();
		}
		
	}
	
	public void deleteAllAccounts() throws BusinessException {
		try (Connection conn = DatabaseConnection.getConn()) {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM bankaccount");
			
			ps.execute();
			loggy.debug("Deleting all rows from Account table");
		} catch (SQLException e) {
			loggy.error("Caught SQLException- " + e);
			e.printStackTrace();
		}
	}
} // End of class

