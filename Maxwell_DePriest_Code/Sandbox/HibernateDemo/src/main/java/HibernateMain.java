import java.util.List;

import com.example.dao.JediDao;
import com.example.model.Jedi;

public class HibernateMain {
	
	private static JediDao jD = new JediDao();

	public HibernateMain() {
		
	}
	
	public static void main(String[] args) {
		
		insertInitialValues();
		
		
		List<Jedi> jList = jD.selectAll();
		
		for(Jedi j: jList) {
			System.out.println(j.getName() + " has a " + j.getLightsaberColor() + " lightsaber");
		}
	}
	
	
	public static void insertInitialValues() {
			
			jD.insertJedi(new Jedi(0, "Mace Windu", "purple", "maybe"));
			jD.insertJedi(new Jedi(2, "Luke Skywalker", "green", "yes"));
			jD.insertJedi(new Jedi(4, "Qui-gon Jinn", "green", "no"));
		}

}
