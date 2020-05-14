import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
	The DAO Concrete Class
		Provides implementation for interface's abstract methods (fetching, updating, removing, etc.)
		Handles all calculations and implementations; the business layer.
		Keeps persistent layer (DB) hidden from business logic.
 */
public class DAOImp implements DAO {

	/*
	 * 	JDBC - Java Database Connectivity
	 *
	 * 	Important Interfaces:
	 * 		Connection - allows connection to DB.
	 * 		Statements - raw SQL query.
	 * 		PreparedStatements - pre-compiles the SQL string w/o parameters.
	 * 			Once params added, they're only treated as values, never keywords.
	 * 		CallableStatement - same idea as PreparedStatement, but used for stored procedures.
	 *
	 * 	Both PreparedStatement and CallableStatement prevents SQL injections.
	 *
	 * 	What do we need to connect to DB?
	 * 		1. URL (endpoint (with port))
	 * 		2. Username
	 * 		3. Password
	 * 		4. Driver (a jar that implements JDBC)
	 * */

	// Opens a connection using JDBC driver name and database URL
	private static final String url = "jdbc:oracle:thin:@myfirstorcl.cgbk8ajkyybd.us-east-2.rds.amazonaws.com:1521:orcl";
	// DB credentials
	private static final String username = "puser";
	private static final String password = "p4ssw0rd";

	@Override
	public void insertPlanet(Planet p) { // Add a new planet to DB
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			System.out.println("\nInserting record into DB table..");
			PreparedStatement ps = conn.prepareStatement("INSERT INTO planets VALUES(?, ?, ?, ?, ?)");
			ps.setInt(1, p.getPlanetID());
			ps.setString(2, p.getPlanetName());
			ps.setBoolean(3, p.isHasRings());
			ps.setInt(4, p.getNumberOfMoons());
			ps.setString(5, p.getSlogan());
			ps.executeUpdate(); // Important; executes SQL statements
			System.out.println("Insertion successful!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deletePlanet(Planet p) { // Delete a planet from the DB
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			System.out.println("\nDeleting record from DB table..");
			PreparedStatement ps = conn.prepareStatement("DELETE FROM planets WHERE planet_name='" + p.getPlanetName() + "'");
			ps.executeUpdate();
			System.out.println("Deletion successful!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatePlanet(Planet p) { // Update existing planet in DB
		// ?
	}

	@Override
	public Planet selectPlanetByName(String name) { // Prints individual planet by name
		Planet planet = new Planet(); // Creates a new Planet reference
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			System.out.println("\nFetching a single record from DB table..");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM planets WHERE planet_name='" + name + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				planet.setPlanetID(rs.getInt("planet_id"));
				planet.setPlanetName(rs.getString(2));
				planet.setHasRings(rs.getBoolean(3));
				planet.setNumberOfMoons(rs.getInt(4));
				planet.setSlogan(rs.getString(5));
			}
			System.out.println("Fetch successful!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return planet; // Returns the constructed Planet reference
	}

	@Override
	public List<Planet> selectAllPlanets() { // Prints all planets from DB
		// loggy.info("Trying to connect.."); // How to write to log file.
		List<Planet> planetList = new ArrayList<>(); // Creates an ArrayList to be spit out
		try (Connection conn = DriverManager.getConnection(url, username, password)) { // Connect to DB with URL and credentials
			System.out.println("\nFetching all records from DB table..");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM planets"); // Creates a SQL Statement to DB from Java by using the PreparedStatement interface
			ResultSet rs = ps.executeQuery(); // Executes the PreparedStatement; expects ResultSet back from DB, so assign it to a ResultSet reference
			while (rs.next()) { // While the ResultSet contains tokens..
				Planet planet = new Planet(); // creates a single object from those returned values
				planet.setPlanetID(rs.getInt("planet_id"));
				planet.setPlanetName(rs.getString(2));
				planet.setHasRings(rs.getBoolean(3));
				planet.setNumberOfMoons(rs.getInt(4));
				planet.setSlogan(rs.getString(5));
				planetList.add(planet); // continually adds those single objects to the ArrayList
			}
			System.out.println("Fetch successful!");
		} catch (SQLException e) {
			// loggy.warn("Unable to connect.", Exception e);
			e.printStackTrace();
		}
		return planetList; // Spits out the ArrayList
	}
}
