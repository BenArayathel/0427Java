package com.bank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.dao.userAccessDAO;
import com.bank.dbutil.OracleConnection;
import com.bank.exception.BusinessException;
import com.bank.model.userAccess;



public class userAccessDAOImpl implements userAccessDAO{

	@Override
	public userAccess createUserAccess(userAccess userAccess) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection()){
		String sql="{call CREATEUSERACCESS(?,?,?,?,?)}";
		CallableStatement callableStatement=connection.prepareCall(sql);
		callableStatement.setString(2, userAccess.getCustomerID());
		callableStatement.setString(3, userAccess.getUserName());
		callableStatement.setString(4, userAccess.getUserPassword());
		callableStatement.setString(5, userAccess.getAccountStatus());

		callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
		
		callableStatement.execute();
		
		userAccess.setUserID(callableStatement.getString(1));
		
		} catch (ClassNotFoundException | SQLException e) {
		throw new BusinessException("SYSTEM: AN INTERNAL ERROR HAS OCCURED, PLEASE CONTACT YOUR SYSTEM ADMINISTRATOR (CRE USER)");
		}
	return userAccess;
	}

	@Override
	public userAccess loginByUsernameAndPassword(String uName, String uPassword) throws BusinessException {
		userAccess uAccess=null;
		try(Connection connection=OracleConnection.getConnection()){
			String sql="Select USERID,CUSTOMERID,ACCOUNTSTATUS from USERACCESS where USERNAME=? and USERPASSWORD=?";				
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, uName);
			preparedStatement.setString(2, uPassword);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				uAccess= new userAccess();
				uAccess.setUserID(resultSet.getString("USERID"));
				uAccess.setCustomerID(resultSet.getString("CUSTOMERID"));
				uAccess.setUserName(uName);
				uAccess.setUserPassword(uPassword);
				uAccess.setAccountStatus(resultSet.getString("ACCOUNTSTATUS"));		
			}else {
				throw new BusinessException("SYSTEM: UserName or Password entered is incorrect");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("SYSTEM: AN INTERNAL ERROR HAS OCCURED, PLEASE CONTACT YOUR SYSTEM ADMINISTRATOR (INFO USER)");
		}
		return uAccess;
	}

	@Override
	public userAccess updateUserPassword(String cId, String newPassword) throws BusinessException {
		userAccess uAccess=null;
		try(Connection connection=OracleConnection.getConnection()){
			String sql="UPDATE USERACCESS SET USERPASSWORD=? where CUSTOMERID=?";	
			PreparedStatement preparedStatement=connection.prepareStatement(sql);			
			preparedStatement.setString(1, newPassword);
			preparedStatement.setString(2, cId);
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("SYSTEM: AN INTERNAL ERROR HAS OCCURED, PLEASE CONTACT YOUR SYSTEM ADMINISTRATOR (UPD USER)");
		}
		return uAccess;
	}

	@Override
	public void deleteUserAccess(String cId) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection()){
			String sql="DELETE from USERACCESS where CUSTOMERID=?";				
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, cId);
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("SYSTEM: AN INTERNAL ERROR HAS OCCURED, PLEASE CONTACT YOUR SYSTEM ADMINISTRATOR (DEL USER)");
		}
	}
	
	@Override
	public void updateUserAccessStatus(String cId, String aStatus) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection()){
			String sql="UPDATE USERACCESS SET ACCOUNTSTATUS=? where CUSTOMERID=?";			
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, aStatus);
			preparedStatement.setString(2, cId);
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("SYSTEM: AN INTERNAL ERROR HAS OCCURED, PLEASE CONTACT YOUR SYSTEM ADMINISTRATOR (UPD USER)");
		}
		
	}


	@Override
	public List<userAccess> getAllUserLoginAccounts() throws BusinessException {
		List<userAccess> userAccessList = new ArrayList<>();
		try(Connection connection=OracleConnection.getConnection()){
			String sql="Select USERID,CUSTOMERID,USERNAME,USERPASSWORD,ACCOUNTSTATUS from USERACCESS";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {			
				userAccess uAccess=new userAccess();
				uAccess.setUserID(resultSet.getString("USERID"));
				uAccess.setCustomerID(resultSet.getString("CUSTOMERID"));
				uAccess.setUserName(resultSet.getString("USERNAME"));
				uAccess.setUserPassword(resultSet.getString("USERPASSWORD"));
				uAccess.setAccountStatus(resultSet.getString("ACCOUNTSTATUS"));	
				userAccessList.add(uAccess);			
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("SYSTEM: AN INTERNAL ERROR HAS OCCURED, PLEASE CONTACT YOUR SYSTEM ADMINISTRATOR (INFO AL USER)");
		}
		
		return userAccessList;
	}


	@Override
	public List<userAccess> getAllUserLoginAccountsStatus(String aStatus) throws BusinessException {
		List<userAccess> userAccessList = new ArrayList<>();
		try(Connection connection=OracleConnection.getConnection()){
			String sql="Select USERID,CUSTOMERID,USERNAME,USERPASSWORD,ACCOUNTSTATUS from USERACCESS where ACCOUNTSTATUS=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, aStatus);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {			
				userAccess uAccess=new userAccess();
				uAccess.setUserID(resultSet.getString("USERID"));
				uAccess.setCustomerID(resultSet.getString("CUSTOMERID"));
				uAccess.setUserName(resultSet.getString("USERNAME"));
				uAccess.setUserPassword(resultSet.getString("USERPASSWORD"));
				uAccess.setAccountStatus(resultSet.getString("ACCOUNTSTATUS"));	
				userAccessList.add(uAccess);			
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("SYSTEM: AN INTERNAL ERROR HAS OCCURED, PLEASE CONTACT YOUR SYSTEM ADMINISTRATOR (INFO AL USER)");
		}
		
		return userAccessList;
	}

}
