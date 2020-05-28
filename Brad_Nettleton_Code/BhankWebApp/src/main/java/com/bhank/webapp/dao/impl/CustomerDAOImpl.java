package com.bhank.webapp.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bhank.webapp.dao.CustomerDAO;
import com.bhank.webapp.dbutil.OracleConnection;
import com.bhank.webapp.exception.BusinessException;
import com.bhank.webapp.main.Main;
import com.bhank.webapp.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	public CustomerDAOImpl() {

	}

	@Override
	public Customer createCustomer(Customer customer) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "{call CREATECUSTOMER(?,?,?,?)}";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(2, customer.getName());
			callableStatement.setString(3, customer.getPassword());
			callableStatement.setDate(4, new java.sql.Date(customer.getDob().getTime()));

			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);

			callableStatement.execute();
			customer.setId(callableStatement.getString(1));

		} catch (ClassNotFoundException | SQLException e) {
			Main.logger.error("Customer DAO failed to create customer in database");
			throw new BusinessException("Internal error occured please contact SYSADMIN");
		}
		Main.logger.info("Customer DAO successfully created customer \""+customer+"\" in database");
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws BusinessException {
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
			Main.logger.error("Customer DAO failed to select all customers from database");
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		Main.logger.info("Customer DAO successfully selected all customers from database");
		return customerList;
	}

	@Override
	public Customer selectCustomerByNameAndPassword(String customerName, String customerPassword) throws BusinessException {
		Customer customer = new Customer();
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "Select id,dob from customer where name= \'"+customerName+"\' and password=\'"+customerPassword+"\'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
			customer.setId(resultSet.getString("id"));
			customer.setName(customerName);
			customer.setPassword(customerPassword);
			customer.setDob(resultSet.getDate("dob"));
			Main.logger.info("Customer DAO successfully selected customer by name \""+customerName+"\" and password \""+customerPassword+"\" from database");
			} else {
				Main.logger.error("Customer DAO failed to select customer by name \""+customerName+"\" and password \""+customerPassword+"\" from database");
				throw new BusinessException("customer by name \""+customerName+"\" and password \""+customerPassword+"\" does not exist\"");
			}
		} catch (ClassNotFoundException | SQLException e) {
			Main.logger.error("Customer DAO failed to select customer by name \""+customerName+"\" and password \""+customerPassword+"\" from database due to "+e.getMessage());
			e.printStackTrace();
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		return customer;
	}

	@Override
	public Customer selectCustomerById(String customerId) throws BusinessException {
		Customer customer = new Customer();
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "Select id,name,password,dob from customer where id= \'"+customerId+"\'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
			customer.setId(resultSet.getString("id"));
			customer.setName("name");
			customer.setPassword("password");
			customer.setDob(resultSet.getDate("dob"));
			Main.logger.error("Customer DAO successfully selected customer by id \""+customerId+"\" from database");
			} else {
				throw new BusinessException("customer by id \""+customerId+"\" does not exist\"");
			}
		} catch (ClassNotFoundException | SQLException e) {
			Main.logger.error("Customer DAO failed to select customer by id \""+customerId+"\" from database due to "+e.getMessage());
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		return customer;
	}

	@Override
	public void deleteCustomer(Customer e) throws BusinessException {
		// TODO Auto-generated method stub

	}
}
