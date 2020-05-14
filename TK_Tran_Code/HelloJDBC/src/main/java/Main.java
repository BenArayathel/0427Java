import java.util.List;

public class Main {
	public static void main(String[] args) {

		System.out.println("Hello JDBC");

		DAOImp planetDAOImp = new DAOImp();

		// Creates a new Pluto Planet obj locally and insert it into the DB as a record.
		Planet p = new Planet(9, "Pluto", false, 1, "I am a planet!");
		planetDAOImp.insertPlanet(p);
		Planet g = new Planet(10, "Gargantuan", true, 69, "I'm yuuuge..");
		planetDAOImp.insertPlanet(g);

		// Deletes a Planet record from DB.
		planetDAOImp.deletePlanet(p);
		planetDAOImp.deletePlanet(g);

		// Updates existing Pluto record in DB.
		planetDAOImp.updatePlanet(p);

		// Grabs a single planet record from the DB.
		Planet x = planetDAOImp.selectPlanetByName("Earth");
		System.out.println(x);

		// Grabs all planet records from DB and store them as objects in a List.
		List<Planet> planetList = planetDAOImp.selectAllPlanets();
		for (Planet i : planetList) { // Iterates through List and prints every object.
			System.out.println(i);
		}
	}
}
