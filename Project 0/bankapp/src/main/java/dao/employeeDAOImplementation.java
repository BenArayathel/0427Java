package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import BankException.bankException;
import bankapp.NewCustomerTemplate;
import connection.OracleConnection;

public class employeeDAOImplementation
{
	public static NewCustomerTemplate createEmployee() throws bankException 
	{
		NewCustomerTemplate employee = new NewCustomerTemplate();
		try(Connection connection=OracleConnection.getConnection())
		{
			String sql="{call MAKEEMPLOYEE(?,?,?,?,?,?,?,?,?,?,?)};";
			CallableStatement callableStatement=connection.prepareCall(sql);
			callableStatement.setString(1, employee.getUn());
			callableStatement.setString(2, employee.getPw());
			callableStatement.setString(3, employee.getFn());
			callableStatement.setString(4, employee.getLn());
			callableStatement.setString(5, employee.getDob());
			callableStatement.setString(6, employee.getPn());
			callableStatement.setInt(8, employee.getCs());
			callableStatement.setDouble(9, employee.getSb());
			callableStatement.setString(10, employee.getEmail());
			callableStatement.setString(11, employee.getUt());
			
			callableStatement.registerOutParameter(7, java.sql.Types.VARCHAR);
			
			callableStatement.execute();
			
			employee.setSsn(callableStatement.getString(7));
			
			
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			throw new bankException("Internal error");
		}
		
		return employee;
	}
	
	public static List<NewCustomerTemplate> getAllCustomers() throws bankException 
	{
		List<NewCustomerTemplate> customerList=new ArrayList<>();
		try(Connection connection=OracleConnection.getConnection()){
			String sql="Select UN,PW,FN,LN,DOB,PN,CS,SB,EMAIL from ACCOUNTS";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) 
			{
				NewCustomerTemplate t=new NewCustomerTemplate();
				t.setSsn(resultSet.getString("SSN"));
				t.setUn(resultSet.getString("UN"));
				t.setPw(resultSet.getString("PW"));
				t.setFn(resultSet.getString("FN"));
				t.setLn(resultSet.getString("LN"));
				t.setDob(resultSet.getString("DOB"));
				t.setPn(resultSet.getString("PN"));
				t.setCs(resultSet.getInt("CS"));
				t.setSb(resultSet.getDouble("SB"));
				t.setEmail(resultSet.getString("EMAIL"));
				customerList.add(t);
			}
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			throw new bankException("Internal Error");
		}
		return customerList;
	}
//---------------------------------------------------------------------------------------------
	public static NewCustomerTemplate getCustomerbySSN(String ssn) throws bankException 
	{
		NewCustomerTemplate t=null;
		try(Connection connection=OracleConnection.getConnection())
		{
			String sql="Select UN,PW,FN,LN,DOB,PN,CS,SB,EMAIL from ACCOUNTS where SSN=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, ssn);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) 
			{
				t=new NewCustomerTemplate();
				t.setSsn(ssn);
				t.setUn(resultSet.getString("UN"));
				t.setPw(resultSet.getString("PW"));
				t.setFn(resultSet.getString("FN"));
				t.setLn(resultSet.getString("LN"));
				t.setDob(resultSet.getString("DOB"));
				t.setPn(resultSet.getString("PN"));
				t.setCs(resultSet.getInt("CS"));
				t.setSb(resultSet.getDouble("SB"));
				t.setEmail(resultSet.getString("EMAIL"));
			}
			else 
			{
				throw new bankException("Customer SSN "+ssn+" does not exist");
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			throw new bankException("Internal Error");
		}
		return t;
	}
//---------------------------------------------------------------------------------------------
	public static List<NewCustomerTemplate> getAllPendingApps() throws bankException 
	{
		List<NewCustomerTemplate> customerList=new ArrayList<>();
		try(Connection connection=OracleConnection.getConnection())
		{
			String sql="Select UN,PW,FN,LN,DOB,PN,SSN,CS,SB,EMAIL from PENDING";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) 
			{
				NewCustomerTemplate t=new NewCustomerTemplate();
				t.setSsn(resultSet.getString("SSN"));
				t.setUn(resultSet.getString("UN"));
				t.setPw(resultSet.getString("PW"));
				t.setFn(resultSet.getString("FN"));
				t.setLn(resultSet.getString("LN"));
				t.setDob(resultSet.getString("DOB"));
				t.setPn(resultSet.getString("PN"));
				t.setCs(resultSet.getInt("CS"));
				t.setSb(resultSet.getDouble("SB"));
				t.setEmail(resultSet.getString("EMAIL"));
				customerList.add(t);
			}
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			throw new bankException("Internal Error");
		}
		
		return customerList;
	}
//---------------------------------------------------------------------------------------------
	public static NewCustomerTemplate approveApp(String ssn) throws bankException
	{
		NewCustomerTemplate t=null;
		try(Connection connection=OracleConnection.getConnection())
		{
			String sql="insert into ACCOUNTS (UN,PW,FN,LN,DOB,PN,SSN,CS,SB,EMAIL) select * from PENDING where ssn=2; DELETE from PENDING where ssn=?;";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, ssn);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) 
			{
				t=new NewCustomerTemplate();
				t.setSsn(ssn);
				t.setUn(resultSet.getString("UN"));
				t.setPw(resultSet.getString("PW"));
				t.setFn(resultSet.getString("FN"));
				t.setLn(resultSet.getString("LN"));
				t.setDob(resultSet.getString("DOB"));
				t.setPn(resultSet.getString("PN"));
				t.setCs(resultSet.getInt("CS"));
				t.setSb(resultSet.getDouble("SB"));
				t.setEmail(resultSet.getString("EMAIL"));
			}
			else 
			{
				throw new bankException("Customer SSN "+ssn+" does not exist");
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			throw new bankException("Internal Error");
		}
		return t;
	}
//---------------------------------------------------------------------------------------------
	public static NewCustomerTemplate rejectApp(String ssn) throws bankException
	{
		NewCustomerTemplate t=null;
		try(Connection connection=OracleConnection.getConnection())
		{
			String sql="DELETE from PENDING where ssn=?;";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, ssn);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) 
			{
				t=new NewCustomerTemplate();
				t.setSsn(ssn);
				t.setUn(resultSet.getString("UN"));
				t.setPw(resultSet.getString("PW"));
				t.setFn(resultSet.getString("FN"));
				t.setLn(resultSet.getString("LN"));
				t.setDob(resultSet.getString("DOB"));
				t.setPn(resultSet.getString("PN"));
				t.setCs(resultSet.getInt("CS"));
				t.setSb(resultSet.getDouble("SB"));
				t.setEmail(resultSet.getString("EMAIL"));
			}
			else 
			{
				throw new bankException("Customer SSN "+ssn+" does not exist");
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			throw new bankException("Internal Error");
		}
		return t;
	}

}

