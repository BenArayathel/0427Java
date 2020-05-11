package com.hackbank.persistence.dao.person;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.dbutil.SingletonDBConnection;
import com.hackbank.persistence.models.Person;

public class PersonDAOImplementation implements PersonDAO {
	
	final static Logger loggy = Logger.getLogger(PersonDAOImplementation.class);
	
	public static void main(String[] args) {
		loggy.setLevel(Level.INFO);
	}

	public Person createPerson(Person person) throws BusinessException {
		String sql = "{call CREATE_PERSON(?,?,?,?,?,?,?)}";
		try(Connection conn = SingletonDBConnection.getConnection()){
			CallableStatement callableStatement = conn.prepareCall(sql);
			callableStatement.setLong(2,person.getSsn());
			callableStatement.setString(3,person.getName());
			callableStatement.setString(4,person.getLastName());
			callableStatement.setDate(5, new java.sql.Date(person.getDob().getTime()));
			callableStatement.setLong(6,person.getPhoneNumber());
			callableStatement.setString(7,person.getCity());
			
			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
			callableStatement.execute();
			
			person.setId(callableStatement.getString(1));
		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("Fatal Error contact SYSADMIN");
			throw new BusinessException("Connection to DB unsuccessful.");
		}
		return person;
		
	}

	public Person getPersonById(String id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getPersonBySSN(int ssn) throws BusinessException {
		boolean flag = false;
		String sql = "SELECT id FROM HB_PERSON WHERE ssn = ?";
		try(Connection conn = SingletonDBConnection.getConnection()){
			PreparedStatement prepared = conn.prepareStatement(sql);
			prepared.setInt(1, ssn);
			ResultSet result = prepared.executeQuery();
			if (result.next()) {
				flag = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("Fatal Error contact SYSADMIN");
		}

		return flag;
	}

	
	
}
