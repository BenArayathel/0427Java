package com.company.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.model.Customer;
import com.company.view.BankApp;

public class CustomerDaoJdbcImpl implements CustomerDao {
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERNAME = "bank_test";
	private static final String PASSWORD = "password";

    ResultSet rs = null;
    PreparedStatement ps = null;

    private static final String INSERT_CUSTOMER_SQL =
            "insert into customer (first_name, last_name, birthday, us_state) " +
                    "values (?, ?, ?, ?)";

    private static final String SELECT_CUSTOMER_SQL =
            "select * from customer where customer_id = ?";

    private static final String SELECT_ALL_CUSTOMERS_SQL =
            "select * from customer";

    private static final String UPDATE_CUSTOMER_SQL =
            "update customer set first_name = ?, last_name = ?, birthday = ?, us_state = ? " +
                    "where customer_id = ?";

    private static final String DELETE_CUSTOMER_SQL =
            "delete from customer where customer_id = ?";
    
	public Customer addCustomer(Customer customer) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
			
			String returnCols[] = { "customer_id" };
			
			// PreparedStatement ps = conn.prepareStatement(INSERT_CUSTOMER_SQL, Statement.RETURN_GENERATED_KEYS);
			ps = conn.prepareStatement(INSERT_CUSTOMER_SQL, returnCols);
			
			//ps.setInt(1, customer.getCustomerId());
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setDate(3, customer.getBirthday());
			ps.setString(4, customer.getState());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if (rs!= null && rs.next()) {
				customer.setCustomerId(rs.getInt(1));
				BankApp.loggy.info("Successfully added customer id: "+rs.getInt(1));
				
			}
			return customer;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
		return null;
	}

	public Customer getCustomer(int id) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(SELECT_CUSTOMER_SQL);
			ps.setInt(1,id);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getInt("customer_id"));
		        customer.setFirstName(rs.getString("first_name"));
		        customer.setLastName(rs.getString("last_name"));
		        customer.setBirthday(rs.getDate("birthday"));
		        customer.setState(rs.getString("us_state"));
				return customer;
			} else {
				BankApp.loggy.info("Record not found.");
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
		return null;
	
	}

	public List<Customer> getAllCustomers() {

		List<Customer> customers = new ArrayList<Customer>();
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(SELECT_ALL_CUSTOMERS_SQL);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getInt("customer_id"));
		        customer.setFirstName(rs.getString("first_name"));
		        customer.setLastName(rs.getString("last_name"));
		        customer.setBirthday(rs.getDate("birthday"));
		        customer.setState(rs.getString("us_state"));

		        customers.add(customer);
			}
			
			//customers.forEach(c -> BankApp.loggy.info(c));

		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
		
		
		return customers;
	}

	public void updateCustomer(Customer customer) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(UPDATE_CUSTOMER_SQL);
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setDate(3, customer.getBirthday());
			ps.setString(4, customer.getState());
			ps.setInt(5,customer.getCustomerId());
			
			int updatedRows = ps.executeUpdate();
			
			if (updatedRows == 0) {
				BankApp.loggy.info("No records updated.");
			} else {
				BankApp.loggy.info(updatedRows + " rows updated.");
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}

	}

	public void deleteCustomer(int id) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(DELETE_CUSTOMER_SQL);
			ps.setInt(1,id);
			
			int deletedRows = ps.executeUpdate();
			
			if (deletedRows == 0) {
				BankApp.loggy.info("No records deleted.");
			} else {
				BankApp.loggy.info(deletedRows + " rows deleted.");
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
	}

}
