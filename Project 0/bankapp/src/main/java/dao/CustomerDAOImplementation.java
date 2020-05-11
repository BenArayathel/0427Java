package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import connection.OracleConnection;
import BankException.bankException;
import bankapp.NewCustomerTemplate;


/*
 * Still left:
 *  - Account amount transfer
 */
public class CustomerDAOImplementation
{
	public static NewCustomerTemplate createCustomer() throws bankException 
	{
		NewCustomerTemplate customer = new NewCustomerTemplate();
		try(Connection connection=OracleConnection.getConnection())
		{
			String sql="{exec makecustomer(?,?,?,?,?,?,?,?,?,?,?)};";
			CallableStatement callableStatement=connection.prepareCall(sql);
			callableStatement.setString(1, customer.getUn());
			callableStatement.setString(2, customer.getPw());
			callableStatement.setString(3, customer.getFn());
			callableStatement.setString(4, customer.getLn());
			callableStatement.setString(5, customer.getDob());
			callableStatement.setString(6, customer.getPn());
			callableStatement.setInt(8, customer.getCs());
			callableStatement.setDouble(9, customer.getSb());
			callableStatement.setString(10, customer.getEmail());
			callableStatement.setString(11, customer.getUt());
			
			callableStatement.registerOutParameter(7, java.sql.Types.VARCHAR);
			
			callableStatement.execute();
			
			customer.setSsn(callableStatement.getString(7));
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();//throw new bankException("Internal error");
		}
		
		return customer;
	}

	public static NewCustomerTemplate showCustomerDetails(String ssn) throws bankException 
	{
		NewCustomerTemplate t=null;
		try(Connection connection=OracleConnection.getConnection()){
			String sql="Select UN,PW,FN,LN,DOB,PN,CS,SB,EMAIL from ACCOUNTS where SSN=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, ssn);
			ResultSet resultSet=preparedStatement.executeQuery();
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
			
			}else {
				throw new bankException("Customer SSN "+ssn+" does not exist");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new bankException("Internal Error");
		}
		
		return t;
	}
}