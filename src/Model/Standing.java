package Model;

public class Standing {
	private int points = 0;
	private String teamName;
	private int goalsFor = 0;
	private int goalsAgainst = 0;
	private int goalDifference = 0;
	
	public Standing(int p, String t, int gf, int ga, int gd){
		this.points = p;
		this.teamName = t;
		this.goalsFor = gf;
		this.goalsAgainst = ga;
		this.goalDifference = gd;
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Standing){
			Standing that = (Standing)obj;
			if(this.points == that.points & this.teamName.equals(that.teamName) & this.goalsFor == that.goalsFor & this.goalsAgainst == that.goalsAgainst & this.goalDifference == that.goalDifference){
				return true;
			}
		}
		return false;
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
