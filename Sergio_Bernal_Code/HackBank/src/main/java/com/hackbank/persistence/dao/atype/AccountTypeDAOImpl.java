package com.hackbank.persistence.dao.atype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.hackbank.persistence.dbutil.SingletonDBConnection;
import com.hackbank.persistence.models.AccountType;

public class AccountTypeDAOImpl implements AccountTypeDAO{
	
	final static Logger loggy = Logger.getLogger(AccountTypeDAOImpl.class);

	@Override
	public List<AccountType> getAllAccountType() {
		loggy.setLevel(Level.INFO);
		List<AccountType> listAccount = new ArrayList<>();
		try(Connection conn = SingletonDBConnection.getConnection()){
			String sql = "SELECT id, name FROM HB_ACCOUNT_TYPE";
			PreparedStatement prepared = conn.prepareStatement(sql);
			ResultSet result = prepared.executeQuery();
			while(result.next()) {
				listAccount.add(new AccountType(result.getByte("ID"),result.getString("NAME")));
			}
		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("Fatal Error contact SYSADMIN");
		}
		return listAccount;
	}

}
