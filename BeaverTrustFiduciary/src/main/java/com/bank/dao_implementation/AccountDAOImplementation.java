package com.bank.dao_implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.bank.dao_interface.AccountDAOInterface;
import com.bank.models.Account;
import com.bank.tools.BankException;
import com.bank.tools.DataConnection;

public class AccountDAOImplementation implements AccountDAOInterface {

	@Override
	public Account createAccount(Account account) throws BankException {
		
		try (Connection conn = DataConnection.getConnection()) {
			String sql = "{call create_new_account(?,?,?,?)}";
			CallableStatement cb = conn.prepareCall(sql);
			
			cb.setString(2, account.getUser_id());
			cb.setString(3, account.getAccount_name());
			cb.setDouble(4, account.getBalance());
			
			cb.registerOutParameter(1, java.sql.Types.VARCHAR);
			
			cb.execute();
			
			account.setAccount_id(cb.getString(1));
			System.out.println("New account is added");
					
					
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BankException("ACCOUNT DAO IMPLEMENTATION ERROR");
		}
		
		System.out.println("this line?");
		return account;
	}
	

	@Override
	public List<Account> listAccounts() throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

}
