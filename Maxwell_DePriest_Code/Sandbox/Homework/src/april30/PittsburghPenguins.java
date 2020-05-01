package april30;

public class PittsburghPenguins implements java.io.Serializable{
	
	private String city = "Pittsburgh";
	private String stadiumName = "PPG Paints Arena";
	private String headCoach = "Mike Sullivan";
	private String captain = "Sidney Crosby";

	/**
	 * 
	 */
	private static final long serialVersionUID = 5425999350979701173L;
	
	
		
	public PittsburghPenguins() {
		
	}
	
	public void waveTerribleTowel() {
		System.out.println("We're waving our terrible towels!!");
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStadiumName() {
		return stadiumName;
	}

	public void setStadiumName(String stadiumName) {
		this.stadiumName = stadiumName;
	}

	public String getHeadCoach() {
		return headCoach;
	}

	public void setHeadCoach(String headCoach) {
		this.headCoach = headCoach;
	}

	public String getCaptain() {
		return captain;
	}

	public void setCaptain(String captain) {
		this.captain = captain;
	}
	
	

}
