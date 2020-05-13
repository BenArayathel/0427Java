package com.example.operations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.example.interfaces.DAO;
import com.example.models.Customer;
import com.example.models.Employee;
import com.example.presentations.MainMenu;
import exceptions.BusinessException;

public class DAOImpl implements DAO{

	final static Logger superlog = Logger.getLogger(DAOImpl.class);
	
	@Override
	public Customer createUser(Customer c, String name, String accname, Double balance) {
		try(Connection con = DConnection.getConnection())
		{
			String sql = "{call CREATEACCT(?,?,?,?,?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(2, accname);
			cs.setDouble(3, balance);
			cs.setString(4, name);
			cs.setString(5, c.getPassword());
			cs.registerOutParameter(1, java.sql.Types.VARCHAR);
			cs.executeUpdate();
			c.setUserid(cs.getString(1));
		} catch (ClassNotFoundException | SQLException e) {
			superlog.error(e);
		}
		return c;
	}

	@Override
	public void updateAcct(Double Balance, String acct) {
		try(Connection con = DConnection.getConnection())
		{
			String sql = "Update accounts set balance = ? Where Account_name = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, Balance);
			ps.setString(2, acct);
			ps.executeUpdate();
			con.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			superlog.error(e);
		}

	}

	@Override
	public void newAcct(Customer c, String name) {
		try(Connection con = DConnection.getConnection())
		{
			String sql = "Insert into accounts values (?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setDouble(2, c.getBalance(name));
			ps.setString(3, c.getUserid());
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {

			superlog.error(e);
		}


	}

	@Override
	public List<Customer> viewAllAccts() {
		List<Customer> pending = new ArrayList<Customer>();
		try(Connection con = DConnection.getConnection())
		{
			PreparedStatement ps = con.prepareStatement("SELECT * "
					+ "FROM pending_acct");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Customer c = new Customer();
				c.setUserid(rs.getString(1));
				c.setName(rs.getString(4));
				c.setPassword(rs.getString(5));
				Double d = rs.getDouble(3);
				c.newAccount(rs.getString(2), d);
				pending.add(c);
			}
			return pending;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			superlog.error(e);
		}
		return pending;

		
	}

	@Override
	public HashMap<String, Double> viewAcct(String name) {
		HashMap<String, Double> acct = new HashMap<>();
		try(Connection con = DConnection.getConnection())
		{
			String sql = "Select account_name, "
			+ "balance from accounts where account_name = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			acct.put(rs.getString(1), rs.getDouble(2));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			superlog.error(e);
		}
		return acct;
	}

	@Override
	public void deleteAcct(String acct) {
		try(Connection con = DConnection.getConnection())
		{
			String sql = "Delete from accounts where account_name = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, acct);
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			superlog.error(e);
		}
	}

	@Override
	public Customer validate(String user, String pass) {
		Customer c = new Customer();
		try(Connection con = DConnection.getConnection())
		{
			String sql = "select account_name, balance, customer_id from accounts "
			+ "join logins on logins.userID=accounts.customer_ID where logins.username = ? AND logins.password = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			c.setName(user);
			c.setPassword(pass);
			while (rs.next())
			{
				c.setUserid(rs.getString(3));
				c.newAccount(rs.getString(1), rs.getDouble(2));
			}
			if (c.getUserid() == null)
			{
				throw new BusinessException("Invalid login credentials.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			superlog.error(e);
		}
		catch(BusinessException e) {
			superlog.info(e);
			superlog.error(e);
		}
		
		return c;
	}

	@Override
	public void approveAccount(Customer c, String accName) {
		try(Connection con = DConnection.getConnection())
		{
			String sql = "{Call APPROVEACCOUNT(?,?,?,?,?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, c.getUserid());
			cs.setString(2, c.getName());
			cs.setString(3, c.getPassword());
			cs.setString(4, accName);
			cs.setDouble(5, c.getBalance(accName));
			cs.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			superlog.error(e);
		}
		
	}

	@Override
	public void denyAccount(String accname) {
		try(Connection con = DConnection.getConnection())
		{
			String sql = "Delete from Pending_acct where account_name = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, accname);
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			superlog.error(e);
		} 
		
	}

	@Override
	public List<HashMap<String, Double>> transactionLog() {
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
			}		
		return all;
		} catch (ClassNotFoundException | SQLException e) {
			superlog.error(e);
		}
		return all;
	}

	@Override
	public Employee EmployeeLogin(String email, String pass) {
		Employee e = new Employee();
		try(Connection con = DConnection.getConnection())
		{
			String sql = ("Select * from EMPLOYEELOGIN");
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				if (rs.getString(1).equals(email) && rs.getString(3).equals(pass))
				{
					e.setEmail(email);
					e.setPassword(pass);
					return e;
				}
			}
		} catch (ClassNotFoundException | SQLException e1) {
			superlog.error(e);
		} 
		return e;
	}

	@Override
	public Employee createEmployee(Employee e) {
		try(Connection con = DConnection.getConnection())
		{
		String sql = "Insert into EmployeeLogin values (?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, e.getEmail());
		ps.setString(2, e.getName());
		ps.setString(3, e.getPassword());
		ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e1) {
			superlog.error(e);
		} 
		return e;
	}

	@Override
	public void Transferrer(String accName, Double amount) {
		try(Connection con = DConnection.getConnection())
		{
			String sql = "Select balance from accounts where ACCOUNT_NAME = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, accName);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Double d = rs.getDouble(1);
			if (d == null)
			{
				throw new BusinessException("Account does not exist.");
			}
			d+=amount;
			sql = "Update accounts set balance = ? where account_name = ? ";
			ps = con.prepareStatement(sql);
			ps.setDouble(1, d);
			ps.setString(2, accName);
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			superlog.error(e);
		} catch (BusinessException e) {
			superlog.info(e);
			superlog.error(e);
		}
		
	}

}
