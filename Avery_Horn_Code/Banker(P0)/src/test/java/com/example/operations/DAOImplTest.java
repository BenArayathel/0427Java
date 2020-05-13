package com.example.operations;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.models.Customer;

public class DAOImplTest {
	
	private static DAOImpl Tester = new DAOImpl();


	@Test
	public void testCreateUser() {
		Customer c = new Customer();
		c.setPassword("Password");
		c = Tester.createUser(c, "AUser", "accounter", 5000.00);
		System.out.println(c);
		assertNotNull(c.getUserid());
	}

//	@Test commented out to avoid new transactions being logged
//	public void testUpdateAcct() {
//		Double D = null;
//		try(Connection con = DConnection.getConnection())
//		{
//
//				String sql = "Update accounts set balance = ? Where Account_name = ?";
//				PreparedStatement ps = con.prepareStatement(sql);
//				ps.setDouble(1, 5100.00);
//				ps.setString(2, "my1stacc");
//				ps.executeUpdate();
//				sql = "select balance from accounts where account_name = 'my1stacc'";
//				ps = con.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery();
//				rs.next();
//				D = rs.getDouble(1);
//				System.out.println(D);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		assertEquals(5100.00, D, 0);
//	}

	@Test
	public void testNewAcct() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewAllAccts() {
		List<Customer> pending = new ArrayList<>();
		try(Connection con = DConnection.getConnection())
		{
			PreparedStatement ps = con.prepareStatement("SELECT acct_id, account_name, "
					+ "beg_balance, username, pword FROM pending_acct");
			
			ResultSet rs = ps.executeQuery();
			int index = 0;
			while(rs.next())
			{
				
				Customer c = new Customer();
				System.out.println(c);
				c.setUserid(rs.getString(1));
				System.out.println(c);
				c.setName(rs.getString(4));
				System.out.println(c);
				c.setPassword(rs.getString(5));
				System.out.println(c);
				Double d = rs.getDouble(3);
				c.newAccount("New account", 500.00);
				System.out.println(c);
				pending.add(c);
				System.out.println(pending.get(index).getName());
				index++;
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertFalse(pending.isEmpty());
	}

	@Test
	public void testViewAcct() {
		HashMap<String, Double> acct = new HashMap<>();
		try(Connection con = DConnection.getConnection())
		{
			String sql = "Select account_name, "
			+ "balance from accounts where account_name = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "my1stacc");
			ResultSet rs = ps.executeQuery();
			rs.next();
			acct.put(rs.getString(1), rs.getDouble(2));
		}catch(SQLException e)
		{
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertNotNull(acct);
	}

	@Test
	public void testDeleteAcct() {
		Double D = null, G = null;
		try(Connection con = DConnection.getConnection())
		{
		String sql = "Insert into accounts values (?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, "3rdaccount");
		ps.setDouble(2, 200.00);
		ps.setString(3, "acMYgo20020");
		ps.executeUpdate();
		sql = "select balance from accounts where account_name = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, "3rdaccount");
		ResultSet rs = ps.executeQuery();
		rs.next();
		D = rs.getDouble(1);
		System.out.println(D);
		sql = "Delete from accounts where account_name = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, "3rdaccount");
		ps.executeUpdate();
		sql = "select balance from accounts where account_name = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, "3rdaccount");
		rs = ps.executeQuery();
		rs.next();
		G = rs.getDouble(1);
		System.out.println(D);
		} catch (ClassNotFoundException | SQLException e) {
			
		}
		assertNull(G);
	}

	@Test
	public void testValidate() {
		Customer c = new Customer();
		try(Connection con = DConnection.getConnection())
	{
		String sql = "select account_name, balance, customer_id from accounts "
		+ "join logins on logins.userID=accounts.customer_ID where logins.username = ? AND logins.password = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, "goodguy");
		ps.setString(2, "password");
		ResultSet rs = ps.executeQuery();
		c.setName("goodguy");
		System.out.println(c);
		c.setPassword("password");
		System.out.println(c);
		if (rs == null)
		{
			System.out.println("it's null dude");
		}
		while (rs.next())
		{
			System.out.println(rs.getString(3));
			c.setUserid(rs.getString(3));
			Double D = rs.getDouble(2);
			c.newAccount(rs.getString(1), D);
			System.out.println(c);
		}
		
		}catch(SQLException e)
		{
		e.printStackTrace();		
		} catch (ClassNotFoundException e1) {
		e1.printStackTrace();
		}
		assertNotNull(c.getUserid());	
		
	}

	@Test
	public void testApproveAccount() {
		Customer c = new Customer();
		c.setPassword("Password");
		c = Tester.createUser(c, "NewUser", "accounter1", 777.00); //object returned is incomplete
		Tester.approveAccount(c, "accounter1");
		Double D = null;
		try(Connection con = DConnection.getConnection())
		{
			String sql = "select account_name, balance, customer_id from accounts where account_name = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "accounter1");
			ResultSet rs = ps.executeQuery();
			rs.next();
			D = rs.getDouble(2);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertNull(D);
	}

	@Test
	public void testDenyAccount() {
		String test = null;
		try(Connection con = DConnection.getConnection())
		{
		con.setAutoCommit(false);
		PreparedStatement ps = con.prepareStatement("INSERT "
		+ "INTO PENDING_ACCT VALUES 'accountID', 'nameacc'," + 5000.00 
		+", 'JackB', password1);");
		ps.executeUpdate();
		Tester.denyAccount("nameacc");
		ps = con.prepareStatement("SELECT * FROM PENDING_ACCT where account_name = nameacc");
		ResultSet rs = ps.executeQuery();
		test = rs.getString(1);
		} catch (SQLException e) {
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertNull(test);
	}

	@Test
	public void testTransactionLog() {
		List<HashMap<String, Double>> all = new ArrayList<>();
		try(Connection con = DConnection.getConnection())
		{
			PreparedStatement ps = con.prepareStatement("SELECT ACCOUNT, AMOUNT" 
			+" FROM transactions");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				HashMap<String, Double> transactions = new HashMap<>();
				transactions.put(rs.getString(1), rs.getDouble(2));
				all.add(transactions);
				System.out.println(all);
			}		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertNotNull(all);
	}
	
	@Test
	public void testEmployeeLogin()
	{
		boolean validated = false;
		try(Connection con = DConnection.getConnection())
		{
			String sql = ("Select email, name, password from EMPLOYEELOGIN");
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();		
			while (rs.next())
			{
				if (rs.getString(1).equals("employee@example.com") && rs.getString(3).equals("password"))
				{
					validated = true;
			
				}
			}
			
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		assertTrue(validated);
}
}