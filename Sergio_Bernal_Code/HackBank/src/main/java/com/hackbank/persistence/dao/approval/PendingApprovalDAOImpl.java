package com.hackbank.persistence.dao.approval;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.dbutil.SingletonDBConnection;
import com.hackbank.persistence.models.AccountType;
import com.hackbank.persistence.models.PendingApproval;
import com.hackbank.persistence.models.Person;

public class PendingApprovalDAOImpl implements PendingApprovalDAO{

	final static Logger loggy = Logger.getLogger(PendingApprovalDAOImpl.class);
	
	@Override
	public void createApproval(PendingApproval pApproval) throws BusinessException{
		loggy.setLevel(Level.INFO);
		try(Connection conn = SingletonDBConnection.getConnection()){
			String query = "{CALL CREATE_P_APPROVAL(?,?,?,?,?)}";
			CallableStatement call = conn.prepareCall(query);
			call.setString("PERSON_ID", pApproval.getPerson().getId());
			call.setByte("ACCOUNT_TYPE_ID", pApproval.getAccountType().getId());
			call.setDouble("START_BALANCE", pApproval.getStartBalance());
			call.setString("STATUS", pApproval.getStatus());
			call.setDate("CREATED_AT", new java.sql.Date(pApproval.getCreatedAt().getTime()));
			call.execute();

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
			loggy.error("Error, please contact SYSADMIN");
			throw new BusinessException("Error, please contact SYSADMIN");
		}
		
	}

	@Override
	public List<PendingApproval> listApproval() throws BusinessException {
		List<PendingApproval> listApproval = new ArrayList<>();
		String sql = "SELECT hpa.person_id, hp.name, hp.last_name, account_type_id, hat.name, hpa.id, start_balance, hpa.status, created_at " + 
					"FROM HB_PENDING_APPROVAL hpa INNER JOIN HB_ACCOUNT_TYPE hat " + 
					"ON hpa.ACCOUNT_TYPE_ID = hat.ID INNER JOIN HB_PERSON hp " + 
					"ON hp.ID = hpa.PERSON_ID " + 
					"WHERE status  = 'Pending'";
		try(Connection conn = SingletonDBConnection.getConnection()){
			PreparedStatement prepared = conn.prepareStatement(sql);
			ResultSet result = prepared.executeQuery();
			while(result.next()) {
				Person person = new Person();
				person.setId(result.getString(1));
				person.setName(result.getString(2));
				person.setLastName(result.getString(3));
				AccountType accountType = new AccountType(result.getByte(4), result.getString(5));
				PendingApproval pApproval = new PendingApproval(result.getInt(6), result.getDouble(7), result.getString(8), result.getDate(9), person, accountType);
				listApproval.add(pApproval);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("SQL Error, please contact SYSADMIN");
		}
		return listApproval;
	}

	@Override
	public boolean updateApproval(PendingApproval pApproval) throws BusinessException {
		boolean flag = false;
		String sql = "UPDATE HB_PENDING_APPROVAL SET status = ? WHERE id = ?";
		try(Connection conn = SingletonDBConnection.getConnection()){
			PreparedStatement prepared = conn.prepareStatement(sql);
			prepared.setString(1, pApproval.getStatus());
			prepared.setInt(2, pApproval.getId());
			
			int result = prepared.executeUpdate();
			if(result > 0) {
				loggy.info("Status change success.");
				flag = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("SQL Error, please contact SYSADMIN");
		}
		return flag;
	}

}
