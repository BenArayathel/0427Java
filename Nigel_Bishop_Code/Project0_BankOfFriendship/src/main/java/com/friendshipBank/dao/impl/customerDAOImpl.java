package com.friendshipBank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.friendshipBank.dao.customerDAO;
import com.friendshipBank.dbutil.OracleConnection;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.customer;


public class customerDAOImpl implements customerDAO{

	@Override
	public customer createCustomer(customer customer) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection()){
		String sql="{call CREATECUSTOMER(?,?,?,?,?,?,?,?,?)}";
		CallableStatement callableStatement=connection.prepareCall(sql);
		callableStatement.setString(2, customer.getLastName());
		callableStatement.setString(3, customer.getFirstName());
		callableStatement.setString(4, customer.getEmailAddress());
		callableStatement.setLong(5, customer.getContactNumber());
		callableStatement.setDate(6, new java.sql.Date(customer.getDob().getTime()));
		callableStatement.setString(7, customer.getStreet());
		callableStatement.setString(8, customer.getCity());
		callableStatement.setString(9, customer.getState());
		
		callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
		
		callableStatement.execute();
		
		customer.setCustomerID(callableStatement.getString(1));
	
		} catch (ClassNotFoundException | SQLException e) {
		throw new BusinessException("Internal error occured please contact SYSADMIN");
		}
	
	return customer;
	}

	@Override
	public customer getCustomerById(String cId) throws BusinessException {
		customer cust=null;
		try(Connection connection=OracleConnection.getConnection()){
			String sql="Select LASTNAME,FIRSTNAME,EMAIL,CONTACT,DOB,STREET,CITY,STATE from CUSTOMER where CUSTOMERID=?";				
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, cId);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				cust=new customer();
				cust.setCustomerID(cId);
				cust.setLastName(resultSet.getString("LASTNAME"));
				cust.setFirstName(resultSet.getString("FIRSTNAME"));
				cust.setEmailAddress(resultSet.getString("EMAIL"));
				cust.setContactNumber(resultSet.getLong("CONTACT"));
				cust.setDob(resultSet.getDate("DOB"));
				cust.setStreet(resultSet.getString("STREET"));
				cust.setCity(resultSet.getString("CITY"));
				cust.setState(resultSet.getString("STATE"));		
			}else {
				throw new BusinessException("CUSTOMER ID: "+cId+" does not exist");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		
		return cust;
	}

	@Override
	public customer updateCustomerContact(String cId, long newContact) throws BusinessException {
		customer cust=null;
		try(Connection connection=OracleConnection.getConnection()){
			String sql="UPDATE CUSTOMER SET CONTACT =? where CUSTOMERID=?";	
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setLong(1, newContact);
			preparedStatement.setString(2, cId);
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		
		return cust;
	}

	@Override
	public void deleteCustomer(String cId) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection()){
			String sql="DELETE from CUSTOMER where CUSTOMERID=?";				
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, cId);
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
	}

	@Override
	public List<customer> getCustomerByState(String state) throws BusinessException {
		List<customer> customerList = new ArrayList<>();
		try(Connection connection=OracleConnection.getConnection()){
			String sql="Select CUSTOMERID,LASTNAME,FIRSTNAME,EMAIL,CONTACT,DOB,STREET,CITY,STATE from CUSTOMER where STATE=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, state);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {	
				customer cust=new customer();
				cust.setCustomerID(resultSet.getString("CUSTOMERID"));
				cust.setLastName(resultSet.getString("LASTNAME"));
				cust.setFirstName(resultSet.getString("FIRSTNAME"));
				cust.setEmailAddress(resultSet.getString("EMAIL"));
				cust.setContactNumber(resultSet.getLong("CONTACT"));
				cust.setDob(resultSet.getDate("DOB"));
				cust.setStreet(resultSet.getString("STREET"));
				cust.setCity(resultSet.getString("CITY"));
				cust.setState(resultSet.getString("STATE"));	
				customerList.add(cust);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		return customerList;	
	}

	@Override
	public List<customer> getCustomerByCity(String city) throws BusinessException {
		List<customer> customerList = new ArrayList<>();
		try(Connection connection=OracleConnection.getConnection()){
			String sql="Select CUSTOMERID,LASTNAME,FIRSTNAME,EMAIL,CONTACT,DOB,STREET,CITY,STATE from CUSTOMER where CITY=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, city);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {	
				customer cust=new customer();
				cust.setCustomerID(resultSet.getString("CUSTOMERID"));
				cust.setLastName(resultSet.getString("LASTNAME"));
				cust.setFirstName(resultSet.getString("FIRSTNAME"));
				cust.setEmailAddress(resultSet.getString("EMAIL"));
				cust.setContactNumber(resultSet.getLong("CONTACT"));
				cust.setDob(resultSet.getDate("DOB"));
				cust.setStreet(resultSet.getString("STREET"));
				cust.setCity(resultSet.getString("CITY"));
				cust.setState(resultSet.getString("STATE"));	
				customerList.add(cust);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		return customerList;	
	}

	@Override
	public List<customer> getAllCustomers() throws BusinessException {
		List<customer> customerList = new ArrayList<>();
		try(Connection connection=OracleConnection.getConnection()){
			String sql="Select CUSTOMERID,LASTNAME,FIRSTNAME,EMAIL,CONTACT,DOB,STREET,CITY,STATE from CUSTOMER";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {			
				customer cust=new customer();
				cust.setCustomerID(resultSet.getString("CUSTOMERID"));
				cust.setLastName(resultSet.getString("LASTNAME"));
				cust.setFirstName(resultSet.getString("FIRSTNAME"));
				cust.setEmailAddress(resultSet.getString("EMAIL"));
				cust.setContactNumber(resultSet.getLong("CONTACT"));
				cust.setDob(resultSet.getDate("DOB"));
				cust.setStreet(resultSet.getString("STREET"));
				cust.setCity(resultSet.getString("CITY"));
				cust.setState(resultSet.getString("STATE"));	
				customerList.add(cust);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		
		return customerList;
	}

}
