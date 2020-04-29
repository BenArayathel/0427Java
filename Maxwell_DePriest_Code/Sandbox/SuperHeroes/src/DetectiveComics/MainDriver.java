package DetectiveComics;

public class MainDriver {
	
	public MainDriver() {
		
	}
	
	public static void main(String[] args) {
		
		
		Batman b = new Batman("Batman", "Male", true, "Bruce Wayne", "Gotham");
		Hero sg = new SuperGirl("SuperGirl", "Female", false, "Linda Lee", "Metropolis");
		Superman s = new Superman("Superman", "Male", true, "Clark Kent", "Metropolis");
		Hero r = new Robin("Robin", "Male", false, "Tim Drake", "Gotham");
		
		s.setSideKick(sg);
		b.setSideKick(r);
		
		s.printSuperman();
		b.printBatman();
		
		
		
	}

}
