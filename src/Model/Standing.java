package Model;

public class Standing {
	private int points = 0;
	private String teamName;
	private int goalsFor = 0;
	private int goalsAgainst = 0;
	private int goalDifference = 0;
	
	public Standing(int p, Team t, int gf, int ga, int gd){
		this.points = p;
		this.teamName = t.getNm();
		this.goalsFor = gf;
		this.goalsAgainst = ga;
		this.goalDifference = gd;
	}
	
	public int getPoints() {return points;}
	public String getTeamName() {return teamName;}
	public int getGoalsFor() {return goalsFor;}
	public int getGoalsAgainst() {return goalsAgainst;}
	public int getGoalDifference() {return goalDifference;}
	
	public void setPoints(int p) {this.points = p;}
	public void setTeamName(String name) {this.teamName = name;}
	public void setGoalsFor(int GF) {this.goalsFor = GF;}
	public void setGoalsAgainst(int GA) {this.goalsAgainst = GA;}
	public void setGoalDifference(int GD) {this.goalDifference = GD;}

	
}