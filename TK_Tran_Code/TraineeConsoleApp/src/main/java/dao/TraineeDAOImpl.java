package dao;

import dbutil.OracleConnection;
import exception.BusinessException;
import model.Trainee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
	Concrete class that implements TraineeDAO's abstract methods.
	This class utilizes OracleConnection's static getConnection() to open a connection with DB.
	Also makes queries to send to the DB.
 */
public class TraineeDAOImpl implements TraineeDAO {

	@Override
	public Trainee createTrainee(Trainee trainee) throws BusinessException {
		try (Connection conn = OracleConnection.getConnection()) { // Opens a connection using OracleConnection's static getConnection()
			String sql = "{CALL CREATETRAINEE(?, ?, ?, ?, ?, ?)}";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(2, trainee.getName());
			cs.setLong(3, trainee.getContact());
			cs.setString(4, trainee.getEmail());
			cs.setDate(5, new java.sql.Date(trainee.getDob().getTime())); // Converts util date to sql date
			cs.setString(6, trainee.getCity());
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.execute();
			trainee.setId(cs.getString(1));
		} catch (SQLException | ClassNotFoundException e) {
			throw new BusinessException("Internal error, please contact SYSADMIN.");
		}
		return trainee;
	}

	@Override
	public Trainee getTraineeByID(String id) throws BusinessException {
		Trainee t = null;
		try (Connection conn = OracleConnection.getConnection()) {
			String sql = "SELECT name, contact, email, dob, city FROM trainee WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				t = new Trainee();
				t.setId(id);
				t.setName(rs.getString("name"));
				t.setContact(rs.getLong("contact"));
				t.setEmail(rs.getString("email"));
				t.setDob(rs.getDate("dob"));
				t.setCity(rs.getString("city"));
			} else {
				throw new BusinessException("Trainee ID " + id + " doesn't exist.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error, please contact SYSADMIN.");
		}
		return t;
	}

	@Override
	public Trainee updateTraineeContact(String id, long newContact) throws BusinessException {
		return null;
	}

	@Override
	public void deleteTrainee(String id) throws BusinessException { // Note: use PreparedStatement

	}

	@Override
	public List<Trainee> getTraineesByCity(String city) throws BusinessException { // Note: use PreparedStatement
		return null;
	}

	@Override
	public List<Trainee> getAllTrainees() throws BusinessException {
		List<Trainee> traineeList = new ArrayList<>();
		try (Connection conn = OracleConnection.getConnection()) {
			String sql = "SELECT id, name, contact, email, dob, city FROM trainee";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Trainee t = new Trainee();
				t.setId(rs.getString("id"));
				t.setName(rs.getString("name"));
				t.setContact(rs.getLong("contact"));
				t.setEmail(rs.getString("email"));
				t.setDob(rs.getDate("dob"));
				t.setCity(rs.getString("city"));
				traineeList.add(t);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error, please contact SYSADMIN.");
		}
		return traineeList;
	}
}
