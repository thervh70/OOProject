package Model;

/**
 * @author Marcel
 *
 */
import java.util.Comparator;

public class Standing implements Comparator<Standing>, Comparable<Standing>{
	private int rank = 0;
	private int points = 0;
	private String teamName;
	private int goalsFor = 0;
	private int goalsAgainst = 0;
	private int goalDifference = 0;
	public Standing(){}
	
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
	
	/**
	 * Checks if two Standings are equal to each other
	 * Based on the points, name, goalsFor, goalsAgainst and goalDif of both Standings 
	 */
	
	public boolean equals(Object obj){
		if(obj instanceof Standing){
			Standing that = (Standing)obj;
			if(this.points == that.points &
					this.teamName.equals(that.teamName) &
					this.goalsFor == that.goalsFor &
					this.goalsAgainst == that.goalsAgainst &
					this.goalDifference == that.goalDifference) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gives a String-representation of a Standing
	 * @return a String representing a Standing
	 */
	
	public String toString() {
		return "Standing [points=" + points + ", teamName=" + teamName
				+ ", goalsFor=" + goalsFor + ", goalsAgainst=" + goalsAgainst
				+ ", goalDifference=" + goalDifference + "]";
	}

	/**
	 * Getters
	 */
	
	/** @return the rank of a Team*/
	public int getRank() {return this.rank;}
	/** @return the points of a Team*/
	public int getPoints() {return points;}
	/** @return the name of a Team*/
	public String getTeamName() {return teamName;}
	/** @return the goalsFor of a Team*/
	public int getGoalsFor() {return goalsFor;}
	/** @return the goalsAgainst of a Team*/
	public int getGoalsAgainst() {return goalsAgainst;}
	/** @return the goalDifference of a Team*/
	public int getGoalDifference() {return goalDifference;}
	
	/**
	 * Setters
	 */
	
	/** Sets the rank of a Team*/
	public void setRank(int r) {this.rank = r;}
	/** Sets the points of a Team*/
	public void setPoints(int p) {this.points = p;}
	/** Sets the name of a Team*/
	public void setTeamName(String name) {this.teamName = name;}
	/** Sets the goalsFor of a Team*/
	public void setGoalsFor(int GF) {this.goalsFor = GF;}
	/** Sets the goalsAgainst of a Team*/
	public void setGoalsAgainst(int GA) {this.goalsAgainst = GA;}
	/** Sets the goalDifference of a Team*/
	public void setGoalDifference(int GD) {this.goalDifference = GD;}

	/**
	 * Compares two Standings with each other, based on points, goalsDif, goalsFor and goalsAgainst, in that order
	 * Needed for the Comparator
	 */
	
	@Override
	public int compare(Standing St1, Standing St2) {
		if(St1.points == St2.points){
			if(St1.goalDifference == St2.goalDifference){
				if(St1.goalsFor == St2.goalsFor){
					return St1.teamName.toLowerCase().compareTo(St2.teamName.toLowerCase());
				}
				return St2.goalsFor - St1.goalsFor;
			}
			return St2.goalDifference - St1.goalDifference;
		}
		return St2.points - St1.points;
	}
	
	/**
	 * Compares a Standing with another one. Needed for the comparator.
	 */
	
	@Override
	public int compareTo(Standing St2) {
		return this.teamName.toLowerCase().compareTo(St2.getTeamName().toLowerCase());
	}

	
}
