package DetectiveComics;

public class Hero implements Powers{
	
	private String name;
	private String gender;
	
	public Hero(String name, String gender) {
		this.name = name;
		this.gender = gender;
		
	}

	@Override
	public boolean canFly() {
		return false;
	}

	@Override
	public boolean impenetrableSkin() {
		return false;
	}

	@Override
	public boolean superStrength() {
		return false;
	}

	@Override
	public boolean isMutant() {
		return false;
	}

	@Override
	public boolean isGenius() {
		return false;
	}

	@Override
	public boolean masterFighter() {
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void printHero() {
		System.out.println("Name: " + name);
		System.out.println("Gender: " + gender);
		
	}
	
	

}
