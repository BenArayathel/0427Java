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

public class CustomerDaoJdbcImpl implements CustomerDao {
	
	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String username = "bank_test";
	private static String password = "password";
	
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

  
    ResultSet rs = null;
    PreparedStatement ps = null;
    
	public Customer addCustomer(Customer customer) {
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
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
				System.out.println("Successfully added record id: "+rs.getInt(1));

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
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			
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
				System.out.println("Record not found.");
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
		// TODO Auto-generated method stub
		List<Customer> customers = new ArrayList<Customer>();
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			
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
			
			customers.forEach(c -> System.out.println(c));

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

	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	public void deleteCustomer(int id) {
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			
			ps = conn.prepareStatement(DELETE_CUSTOMER_SQL);
			ps.setInt(1,id);
			
			int deletedRows = ps.executeUpdate();
			
			if (deletedRows == 0) {
				System.out.println("No records deleted.");
			} else {
				System.out.println(deletedRows + " rows deleted.");
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
