package com.pzero.v1.persistence.dao.user;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.persistence.dbutil.SingletonDBConnection;
import com.pzero.v1.persistence.models.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public User createUser(User user) throws BusinessException {
		User iUser = null;
		String sql = "{CALL CREATE_USER(?,?,?,?,?)}";
		try(Connection conn = SingletonDBConnection.getConnection()){
			CallableStatement call = conn.prepareCall(sql);
			call.setString(2, user.getEmail());
			call.setString(3, user.getPassword());
			call.setString(4, user.getPersonId());
			call.setString(5, user.getUserType());
			
			call.registerOutParameter(1, java.sql.Types.VARCHAR);
			
			call.execute();
			iUser = user;
			iUser.setId(call.getString(1));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iUser;
	}

}
