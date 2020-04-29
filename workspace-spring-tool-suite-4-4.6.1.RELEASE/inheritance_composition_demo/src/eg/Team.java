package eg;

public class Team {
	private int tid;
	private String teamName;
	private String coachName;
	private int noOfPlayers;
	
	public Team() {
		
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public int getNoOfPlayers() {
		return noOfPlayers;
	}
	public void setNoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}
	public Team(int tid, String teamName, String coachName, int noOfPlayers) {
		super();
		this.tid = tid;
		this.teamName = teamName;
		this.coachName = coachName;
		this.noOfPlayers = noOfPlayers;
	}
	
	public void printTeam() {
		System.out.println("Team Details");
		System.out.println("Team ID: " +tid);
		System.out.println("Team Name: "+teamName);
		System.out.println("Coach: "+coachName);
		System.out.println("NoOfPlayers: "+noOfPlayers);
	}
	
}
