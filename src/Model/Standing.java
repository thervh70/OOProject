package Model;

/**
 * @author Marcel
 *
 */
public class Standing {
	private int points = 0;
	private String teamName;
	private int goalsFor = 0;
	private int goalsAgainst = 0;
	private int goalDifference = 0;
	
	/**
	 * Constructor; keeps track each teams' statistic i.e. their points, goals for, goals against and the difference between goals for and goals against
	 * @param p
	 * @param t
	 * @param gf
	 * @param ga
	 * @param gd
	 */
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
	
	@Override
	public String toString() {
		return "Standing [points=" + points + ", teamName=" + teamName
				+ ", goalsFor=" + goalsFor + ", goalsAgainst=" + goalsAgainst
				+ ", goalDifference=" + goalDifference + "]";
	}

	/**
	 * Getters
	 */
	public int getPoints() {return points;}
	public String getTeamName() {return teamName;}
	public int getGoalsFor() {return goalsFor;}
	public int getGoalsAgainst() {return goalsAgainst;}
	public int getGoalDifference() {return goalDifference;}
	
	/**
	 * Setters
	 */
	public void setPoints(int p) {this.points = p;}
	public void setTeamName(String name) {this.teamName = name;}
	public void setGoalsFor(int GF) {this.goalsFor = GF;}
	public void setGoalsAgainst(int GA) {this.goalsAgainst = GA;}
	public void setGoalDifference(int GD) {this.goalDifference = GD;}

	
}
