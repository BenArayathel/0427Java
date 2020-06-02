package com.pzero.v1.persistence.dao.enrollment;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.persistence.dao.account.AccountDAOImpl;
import com.pzero.v1.persistence.dbutil.SingletonDBConnection;
import com.pzero.v1.persistence.models.BankAccountEnrollment;

public class EnrollmentDAOImpl implements EnrollmentDAO{

	final static Logger loggy = Logger.getLogger(AccountDAOImpl.class);

	public static void main(String[] args) {
		loggy.setLevel(Level.INFO);
	}
	
	@Override
	public boolean createEnrollment(BankAccountEnrollment enrollment) throws BusinessException {
		boolean flag = false;
		try(Connection conn = SingletonDBConnection.getConnection()){
			String sql = "{CALL CREATE_ENROLLMENT(?,?,?,?,?,?)}";
			CallableStatement call = conn.prepareCall(sql);
			call.setString(1, enrollment.getToName());
			call.setString(2, enrollment.getToLastName());
			call.setString(3, enrollment.getAccountNumber());
			call.setString(4, enrollment.getRoutingNumber());
			call.setDate(5, new java.sql.Date(enrollment.getCreatedAt().getTime()));
			call.setString(6, enrollment.getPersonId());
			
			call.execute();
			flag = true;
			
		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("SQL Error, please contact SYSADMIN");
			throw new BusinessException("SQL Error, please contact SYSADMIN");
		}
		return flag;
	}

	@Override
	public List<BankAccountEnrollment> getEnrollmentsByCustomer(String id) throws BusinessException {
		List<BankAccountEnrollment> listEnrollment = new ArrayList<>();
		String sql = "SELECT ID, TO_NAME, TO_LAST_NAME, ACCOUNT_NUMBER, ROUTING_NUMBER " + 
					"FROM HB_BANK_ACCOUNT_ENROLLMENT WHERE PERSON_ID = ?";
		try(Connection conn = SingletonDBConnection.getConnection()){
			PreparedStatement prepared = conn.prepareStatement(sql);
			prepared.setString(1, id);
			
			ResultSet result = prepared.executeQuery();
			while(result.next()) {
				BankAccountEnrollment enrollment = new BankAccountEnrollment();
				enrollment.setId(result.getInt(1));
				enrollment.setToName(result.getString(2));
				enrollment.setToLastName(result.getString(3));
				enrollment.setAccountNumber(result.getString(4));
				enrollment.setRoutingNumber(result.getString(5));
				listEnrollment.add(enrollment);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			loggy.error("SQL Error, please contact SYSADMIN");
			throw new BusinessException("SQL Error, please contact SYSADMIN");
		}
		
		return listEnrollment;
	}

}
