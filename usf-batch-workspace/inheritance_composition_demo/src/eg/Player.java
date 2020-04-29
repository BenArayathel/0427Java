package eg;

public class Player extends Person{

	private int score;
//	private String teamName;
	private Team team;
	
	public Player() {
		// TODO Auto-generated constructor stub
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

//	public String getTeamName() {
//		return teamName;
//	}
//
//	public void setTeamName(String teamName) {
//		this.teamName = teamName;
//	}

//	public Player(int id, String name, int score, String teamName) {
//		super(id, name);
//		this.score = score;
//		this.teamName = teamName;
//	}
	
	public void printPlayer() {
		System.out.println("Printing player");
		super.printPerson();
		System.out.println("Score = "+score);
		this.team.printTeam();
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Player(int id, String name, int score, Team team) {
		super(id, name);
		this.score = score;
		this.team = team;
	}
}
