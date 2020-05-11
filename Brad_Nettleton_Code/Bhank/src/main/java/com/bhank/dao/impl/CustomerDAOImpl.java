package com.bhank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bhank.dao.CustomerDAO;
import com.bhank.dbutil.OracleConnection;
import com.bhank.exception.BusinessException;
import com.bhank.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	public CustomerDAOImpl() {

	}

	@Override
	public Customer createCustomer(Customer c) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "{call CREATECUSTOMER(?,?,?,?)}";
			System.out.println(c);
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(2, c.getName());
			callableStatement.setString(3, c.getPassword());
			callableStatement.setDate(4, new java.sql.Date(c.getDob().getTime()));

			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);

			callableStatement.execute();
			c.setId(callableStatement.getString(1));

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal error occured please contact SYSADMIN");
		}
		return c;
	}

	@Override
	public Customer updateCustomer(Customer e) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> selectAllCustomers() throws BusinessException {
		List<Customer> customerList = new ArrayList<>();
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "Select id,name,dob from customer";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Customer c = new Customer();
				c.setId(resultSet.getString("id"));
				c.setName(resultSet.getString("name"));
				c.setDob(resultSet.getDate("dob"));
				customerList.add(c);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		return null;
	}

	@Override
	public Customer selectCustomerByNameAndPassword(String name, String password) throws BusinessException {
		Customer customer = new Customer();
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "Select id,dob from customer where name= \'"+name+"\' and password=\'"+password+"\'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
			customer.setId(resultSet.getString("id"));
			customer.setName(name);
			customer.setPassword(password);
			customer.setDob(resultSet.getDate("dob"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		return customer;
	}

	@Override
	public Customer selectCustomerById(String id) throws BusinessException {
		Customer customer = new Customer();
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "Select id,name,password,dob from customer where id= \'"+id+"\'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
			customer.setId(resultSet.getString("id"));
			customer.setName("name");
			customer.setPassword("password");
			customer.setDob(resultSet.getDate("dob"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		return customer;
	}

	@Override
	public void deleteCustomer(Customer e) throws BusinessException {
		// TODO Auto-generated method stub

	}
}
