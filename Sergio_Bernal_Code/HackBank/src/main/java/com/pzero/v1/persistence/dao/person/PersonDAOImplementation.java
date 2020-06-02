package com.pzero.v1.persistence.dao.person;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.persistence.dbutil.SingletonDBConnection;
import com.pzero.v1.persistence.models.Person;

public class PersonDAOImplementation implements PersonDAO {
	
	final static Logger loggy = Logger.getLogger(PersonDAOImplementation.class);
	
	public static void main(String[] args) {
		loggy.setLevel(Level.INFO);
	}

	public Person createPerson(Person person) throws BusinessException {
		String sql = "{call CREATE_PERSON(?,?,?,?,?,?,?)}";
		try(Connection conn = SingletonDBConnection.getConnection()){
			CallableStatement callableStatement = conn.prepareCall(sql);
			callableStatement.setString(2,person.getSsn());
			callableStatement.setString(3,person.getName());
			callableStatement.setString(4,person.getLastName());
			callableStatement.setDate(5, new java.sql.Date(person.getDob().getTime()));
			callableStatement.setString(6,person.getPhoneNumber());
			callableStatement.setString(7,person.getCity());
			
			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
			callableStatement.execute();
			
			person.setId(callableStatement.getString(1));
		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("Error, please contact SYSADMIN");
			throw new BusinessException("Error, please contact SYSADMIN");
		}
		return person;
		
	}

	public Person getPersonById(String id) throws BusinessException {
		Person person = null;
		String sql = "SELECT ID,NAME,LAST_NAME,DOB,PHONE_NUMBER,CITY " + 
					"FROM HB_PERSON hp " + 
					"WHERE ID = ?";
		try(Connection conn = SingletonDBConnection.getConnection()){
			PreparedStatement prepared = conn.prepareStatement(sql);
			prepared.setString(1, id);
			ResultSet result = prepared.executeQuery();
			if (result.next()) {
				person = new Person();
				person.setId(result.getString(1));
				person.setName(result.getString(2));
				person.setLastName(result.getString(3));
				person.setDob(result.getDate(4));
				person.setPhoneNumber(result.getString(5));
				person.setCityd(result.getString(6));
			}
		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("Error, please contact SYSADMIN");
			throw new BusinessException("Error, please contact SYSADMIN");
		}

		return person;
	}

	@Override
	public Person getPersonIdBySSN(String ssn) throws BusinessException {
		Person person = null;
		String sql = "SELECT ID,NAME,LAST_NAME,DOB,PHONE_NUMBER,CITY " + 
					"FROM HB_PERSON hp " + 
					"WHERE SSN = ?";
		try(Connection conn = SingletonDBConnection.getConnection()){
			PreparedStatement prepared = conn.prepareStatement(sql);
			prepared.setString(1, ssn);
			ResultSet result = prepared.executeQuery();
			if (result.next()) {
				person = new Person();
				person.setId(result.getString(1));
				person.setName(result.getString(2));
				person.setLastName(result.getString(3));
				person.setDob(result.getDate(4));
				person.setPhoneNumber(result.getString(5));
				person.setCityd(result.getString(6));
			}
		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("Error, please contact SYSADMIN");
			throw new BusinessException("Error, please contact SYSADMIN");
		}

		return person;
	}
	
	
}
