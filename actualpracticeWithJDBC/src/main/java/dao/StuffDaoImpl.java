package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Stuff;

public class StuffDaoImpl implements Dao {

	//"jdbc:oracle:thin:@<endpoint>:1521:orcl"
	
	private static String url = 
			"jdbc:oracle:thin:@database-1.cb5vpvii5jvy.us-west-2.rds.amazonaws.com:1521:orcl";
	private static String username = "admin";
	private static String password = "gunderodd";
	
	
	
	@Override
	public void insertStuff(Stuff thing) {
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
//			PreparedStatement ps = conn.prepareStatement("insert into mystuff values(?,?,?,?)");
//			
//			ps.setInt(1, thing.getStuff_id());
//			ps.setString(2, thing.getStuff_name());
//			ps.setBoolean(3, thing.isStuff_iscool());
//			ps.setInt(4, thing.getStuff_amount());
//			
//			ps.executeUpdate();
			
			
			// above we have the first approach that works if it doesn't require a 
			// stored procedure. to access the procedure (and sequence which creates
			// the id) we use callablestatement
			
			CallableStatement cs = conn.prepareCall("{call create_new_stuff(?,?,?,?)}");
			
//			cs.setInt(1, thing.getStuff_id());
			cs.registerOutParameter(1, java.sql.Types.VARCHAR);
//			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);

			cs.setString(2, thing.getStuff_name());
			cs.setBoolean(3, thing.isStuff_iscool());
			cs.setInt(4, thing.getStuff_amount());
			
			cs.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateStuffAmount(String name, int amount) {
		// change value of a single column in a single row...
		
		//add a single quotes so sql can understand the string
		name = "'" + name + "'";
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("update mystuff set stuff_amount = " + amount + " where stuff_name = " + name);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Stuff> selectAllStuff() {
		
		List<Stuff> allStuff = new ArrayList<Stuff>();
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("select * from mystuff order by stuff_id");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				allStuff.add(
						new Stuff(rs.getInt("stuff_id"), rs.getString(2), rs.getBoolean(3), rs.getInt(4)));
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//loggy.warn("Not connected",Exception e);
			e.printStackTrace();
		}
		return allStuff;
		
	}


	@Override
	public void deleteStuff(String thingName) {
		//try to delete by item name
		thingName = "'" + thingName + "'";
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("delete from mystuff where stuff_name = " + thingName);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	
	@Override
	public Stuff selectStuffByName(String thing) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStuff(Stuff thing) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteStuff(Stuff thing) {
		// TODO Auto-generated method stub
		
	}

}
