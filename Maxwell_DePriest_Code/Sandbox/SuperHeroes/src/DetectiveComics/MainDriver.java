package DetectiveComics;

public class MainDriver {
	
	public MainDriver() {
		
	}
	
	public static void main(String[] args) {
		
		
		Batman b = new Batman("Batman", "Male", true, "Bruce Wayne", "Gotham");
		Hero sg = new SuperGirl("SuperGirl", "Female", false, "Linda Lee", "Metropolis");
		Superman s = new Superman("Superman", "Male", true, "Clark Kent", "Metropolis");
		Hero r = new Robin("Robin", "Male", false, "Tim Drake", "Gotham");
		GreenArrow gA = new GreenArrow("Green Arrow", "Male", true, "Oliver Queen", "Star City");
		Hero speedy = new Speedy("Speedy", "Male", false, "Roy Harper", "Star City");
		
		s.setSideKick(sg);
		b.setSideKick(r);
		gA.setSideKick(speedy);
		
		s.printSuperman();
		b.printBatman();
		gA.printHero();

		
		
		
		
	}

}
