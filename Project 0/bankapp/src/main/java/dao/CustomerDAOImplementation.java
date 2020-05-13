package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import connection.OracleConnection;
import BankException.bankException;
import bankapp.UserTemplate;


/*
 * Still left:
 *  - Account amount transfer
 */
public class CustomerDAOImplementation implements CustomerDAO
{
	private static String url = "jdbc:oracle:thin:@database-1.c7cy1w0cmkrg.us-east-2.rds.amazonaws.com:1521:ORCL";
	private static String username = "admin";
	private static String password = "password";
	
	@Override
	public void createCustomer()
	{
		UserTemplate customer = new UserTemplate();
		try(Connection connection=DriverManager.getConnection(url, username, password))
		{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO pending VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			System.out.println("Enter Username");
			ps.setString(1,customer.getUn());
			System.out.println("Enter Password");
			ps.setString(2,customer.getPw());
			System.out.println("Enter First Name");
			ps.setString(3,customer.getFn());
			System.out.println("Enter Last Name");
			ps.setString(4,customer.getLn());
			System.out.println("Enter Date of Birth");
			ps.setString(5,customer.getDob());
			System.out.println("Enter Phone Number");
			ps.setString(6,customer.getPn());
			System.out.println("Enter Social Security Number");
			ps.setString(7,customer.getSsn());
			System.out.println("Enter Credit Score");
			ps.setInt(8,customer.getCs());
			System.out.println("Enter Starting Balance");
			ps.setDouble(9,customer.getSb());
			System.out.println("Enter Email");
			ps.setString(10,customer.getEmail());
			System.out.println("Enter User Type (Customer or Employee)");
			ps.setString(11,customer.getUt());
			
			ps.executeUpdate();
			
			System.out.println("Thank you, you have been added to our system");
			System.out.println("Returning to Main Menu");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public List<UserTemplate> showCustomerDetails(String ssn) throws bankException 
	{
		//UserTemplate t=new UserTemplate();
		List<UserTemplate> customer = new ArrayList<UserTemplate>();
		try(Connection connection=DriverManager.getConnection(url, username, password))
		{
			
			String sql="Select * from pending where SSN="+ssn;
			
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			
			//preparedStatement.setString(1, ssn);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) 
			{
				customer.add(
				new UserTemplate(resultSet.getString("UN"), 
						resultSet.getString("PW"), 
						resultSet.getString("FN"), 
						resultSet.getString("LN"), 
						resultSet.getString("DOB"), 
						resultSet.getString("PN"), 
						resultSet.getString("SSN"), 
						resultSet.getInt("CS"), 
						resultSet.getDouble("SB"), 
						resultSet.getString("EMAIL"),
						resultSet.getString("UT")));
			}
		}
		
		catch (SQLException e) 
		{
			System.out.println("Customer SSN "+ssn+" does not exist");
			e.printStackTrace();
		}
		System.out.println(customer);
		System.out.println("Returning to Customer Menu");
		return customer;
		
	}
	
	@Override
	public void withdraw(double withdraw, String ssn) throws bankException
	{
		try(Connection connection=DriverManager.getConnection(url, username, password))
		{
			String sql="Update accounts set SB = SB-"+withdraw+" where SSN="+ssn;
			
			CallableStatement callStatement=connection.prepareCall(sql);
			
			callStatement.executeUpdate();
			
		}
		
		catch (SQLException e) 
		{
			System.out.println("Customer SSN "+ssn+" does not exist");
			e.printStackTrace();
		}
		System.out.println("You have withdrawn "+withdraw);
		System.out.println("Returning to Customer Menu");
	}
	
	@Override
	public void deposit(double deposit, String ssn) throws bankException
	{
		try(Connection connection=DriverManager.getConnection(url, username, password))
		{
			System.out.println("Please enter the amount you wish to deposit:");
			String sql="Update accounts set SB = SB+"+deposit+" where SSN="+ssn;
			
			CallableStatement callStatement=connection.prepareCall(sql);
			
			callStatement.executeQuery();
		}
		
		catch (SQLException e) 
		{
			System.out.println("Customer SSN "+ssn+" does not exist");
			e.printStackTrace();
		}
		System.out.println("You have withdrawn "+deposit);
		System.out.println("Returning to Customer Menu");
	}
	
	public void transfer(double deposit, String ssn, String ssn2) throws bankException
	{
		try(Connection connection=DriverManager.getConnection(url, username, password))
		{
			String sql="Update accounts set SB = SB-"+deposit+" where SSN="+ssn;
			String sql2="Update accounts set SB = SB+"+deposit+" where SSN="+ssn2;
			CallableStatement callStatement=connection.prepareCall(sql);
			CallableStatement callStatement2=connection.prepareCall(sql2);
			callStatement.executeQuery();
		}
		
		catch (SQLException e) 
		{
			System.out.println("Customer SSN "+ssn+" does not exist");
			e.printStackTrace();
		}
		System.out.println("You have transferred "+deposit+ " to SSN "+ssn2);
		System.out.println("Returning to Customer Menu");
	}
}