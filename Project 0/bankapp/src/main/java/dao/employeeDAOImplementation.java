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

import com.trainee.exception.BusinessException;
import com.trainee.model.Trainee;

import BankException.bankException;
import bankapp.UserTemplate;
import connection.OracleConnection;

public class employeeDAOImplementation implements employeeDAO
{
	private static String url = "jdbc:oracle:thin:@database-1.c7cy1w0cmkrg.us-east-2.rds.amazonaws.com:1521:ORCL";
	private static String username = "admin";
	private static String password = "password";
	
	@Override
	public void createEmployee() 
	{
		UserTemplate employee = new UserTemplate();
		try(Connection connection=DriverManager.getConnection(url, username, password))
		{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO employee VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			
			System.out.println("Enter Username");
			ps.setString(1,employee.getUn());
			System.out.println("Enter Password");
			ps.setString(2,employee.getPw());
			System.out.println("Enter First Name");
			ps.setString(3,employee.getFn());
			System.out.println("Enter Last Name");
			ps.setString(4,employee.getLn());
			System.out.println("Enter Date of Birth");
			ps.setString(5,employee.getDob());
			System.out.println("Enter Phone Number");
			ps.setString(6,employee.getPn());
			System.out.println("Enter Social Security Number");
			ps.setString(7,employee.getSsn());
			System.out.println("Enter Credit Score");
			ps.setInt(8,employee.getCs());
			System.out.println("Enter Starting Balance");
			ps.setDouble(9,employee.getSb());
			System.out.println("Enter Email");
			ps.setString(10,employee.getEmail());
			System.out.println("Enter User Type (Customer or Employee)");
			ps.setString(11,employee.getUt());
			
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
	public List<UserTemplate> getCustomerbySSN(String ssn) throws bankException 
	{
		List<UserTemplate> customer = new ArrayList<UserTemplate>();
		try(Connection connection=DriverManager.getConnection(url, username, password))
		{
			
			String sql="Select * from accounts where SSN="+ssn;
			
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
	public List<UserTemplate> getAllPendingApps() throws bankException
	{
		List<UserTemplate> pendingList=new ArrayList<>();
		try(Connection connection=OracleConnection.getConnection())
		{
			String sql="Select * from pending";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) 
			{
				UserTemplate t=new UserTemplate();
				t.setUn(resultSet.getString("UN"));
				t.setPw(resultSet.getString("PW"));
				t.setFn(resultSet.getString("FN"));
				t.setLn(resultSet.getString("LN")); 
				t.setDob(resultSet.getString("DOB")); 
				t.setPn(resultSet.getString("PN"));
				t.setSsn(resultSet.getString("SSN"));
				t.setCs(resultSet.getInt("CS"));
				t.setSb(resultSet.getDouble("SB"));
				t.setEmail(resultSet.getString("EMAIL"));
				t.setUt(resultSet.getString("UT"));
				pendingList.add(t);
			}
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			throw new bankException("Internal Error");
		}
		return pendingList;
	}

	@Override
	public void approveApp(String ssn) throws bankException {
		try(Connection connection=DriverManager.getConnection(url, username, password))
		{
			String sql="Insert into accounts select * from pending where SSN="+ssn;
			String sql2="Delete from pending where SSN="+ssn;
			
			CallableStatement callStatement=connection.prepareCall(sql);
			CallableStatement callStatement2=connection.prepareCall(sql2);
			callStatement.executeQuery();
			callStatement2.executeQuery();
		}
		
		catch (SQLException e) 
		{
			System.out.println("Customer SSN "+ssn+" does not exist");
			e.printStackTrace();
		}
		System.out.println("You have approved "+ssn);
		System.out.println("Returning to Employee Menu");
	}

	@Override
	public void rejectApp(String ssn) throws bankException {
		try(Connection connection=DriverManager.getConnection(url, username, password))
		{
			System.out.println("Please enter the ssn of the person you wish to reject:");
			String sql="Delete from pending where SSN="+ssn;
			
			CallableStatement callStatement=connection.prepareCall(sql);
			
			callStatement.executeQuery();
		}
		
		catch (SQLException e) 
		{
			System.out.println("Customer SSN "+ssn+" does not exist");
			e.printStackTrace();
		}
		System.out.println("You have rejected "+ssn);
		System.out.println("Returning to Employee Menu");
	}

	@Override
	public List<UserTemplate> getAllAccounts() throws bankException 
	{
		List<UserTemplate> accountsList=new ArrayList<>();
		try(Connection connection=OracleConnection.getConnection()){
			String sql="Select * from accounts";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				UserTemplate t=new UserTemplate();
				t.setUn(resultSet.getString("UN"));
				t.setPw(resultSet.getString("PW"));
				t.setFn(resultSet.getString("FN"));
				t.setLn(resultSet.getString("LN")); 
				t.setDob(resultSet.getString("DOB")); 
				t.setPn(resultSet.getString("PN"));
				t.setSsn(resultSet.getString("SSN"));
				t.setCs(resultSet.getInt("CS"));
				t.setSb(resultSet.getDouble("SB"));
				t.setEmail(resultSet.getString("EMAIL"));
				t.setUt(resultSet.getString("UT"));
				accountsList.add(t);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new bankException("Internal Error");
		}
		
		return accountsList;
	}
}

