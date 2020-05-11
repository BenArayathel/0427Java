package hellojdbc;

import java.util.List;

import hellojdbc.model.Planet;

public class PlanetDaoImpl implements PlanetDao {

	public PlanetDaoImp() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * JDBC = Java Database Connectivity
	 * 
	 * Important Interfaces:
	 * 	Connection- > Allows us to connect to our db
	 * statment -> raw sql query
	 * prepared statuemtns -> precompiles the sql string without parameters, once
	 * parameters are addded, they are only treated as values, never keywords
	 * callable statement -> same ideas a preparedStatement but is used for stroed procedures
	 * 
	 * both preparedStatement and callable prevent sql injections
	 * 
	 * what do we need to connect to our db?
	 * 1.url
	 * 2. username
	 * 3. password
	 * 4.a driver (jar that implements jdbc
	 */
 	
	//"jdbc:oracle:this:@<endpoint>:1521:orcl"
	private static String url = 
			"jdbc:oracle:this:@<endpoint>:1521:orcl";
	private String username = "madmax9242";
	private String password = "jasonbourne";

	@Override
	public void insertPlanet(Planet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePlanet(Planet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Planet> selectAllPlanets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Planet selectPlanetByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePlanet(Planet p) {
		// TODO Auto-generated method stub
		
	}

}
