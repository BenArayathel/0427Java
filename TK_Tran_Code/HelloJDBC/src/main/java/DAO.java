import java.util.List;

/*
	The DAO Interface
		Defines an abstract API that will perform CRUD operations on objects of type <T>.
		Simply contains abstract methods which concrete class must implement.
 */
public interface DAO {
	// Create
	public void insertPlanet(Planet p);

	// Read
	public List<Planet> selectAllPlanets(); // Grabs every planet object from DB
	public Planet selectPlanetByName(String name); // Grabs single planet from DB

	// Update
	public void updatePlanet(Planet p);

	// Delete
	public void deletePlanet(Planet p);
}
