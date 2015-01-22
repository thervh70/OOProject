package Model;

import Controller.saveGame;

public class Match {


	private Team teamHome, teamAway;
	private String teamHomeName, teamAwayName, score, goalsHomeS = "", goalsAwayS = "";
	private int goalsHome = -1, goalsAway = -1, day, homeIndex,awayIndex;
	
	/**
	 * Constructor, create a Match, this match is not played yet
	 * @param day		Day at which a match is played
	 * @param teamHome	The Home team
	 * @param teamAway	The Away team
	 */
	public Match(int day, Team teamHome, Team teamAway){
		this.setTeamHome(teamHome);
		this.setTeamAway(teamAway);
		this.day = day;

		this.setTeamHomeName(teamHome.getNm());
		this.setTeamAwayName(teamAway.getNm());
		this.setScore(goalsHome + " - " + goalsAway);
	}
	
	/**
	 * Constructor for a match that has been played
	 * @param day		Day of the match
	 * @param teamHome	Home team
	 * @param teamAway	Away team
	 * @param goalsHome	Amount of goals the home team has scored
	 * @param goalsAway	Amount of goals the away team has scored
	 */
	public Match(int day, Team teamHome, Team teamAway, Integer goalsHome, Integer goalsAway){
		this.setTeamHome(teamHome);
		this.setTeamAway(teamAway);
		this.day = day;
		this.goalsHome = goalsHome;
		this.goalsAway = goalsAway;
		
		if(day < saveGame.getDay()){
			this.goalsHomeS = goalsHome.toString();
			this.goalsAwayS = goalsAway.toString();
		}
		
		this.setTeamHomeName(teamHome.getNm());
		this.setTeamAwayName(teamAway.getNm());
		this.setScore(goalsHome + " - " + goalsAway);
	}
	
	/**
	 * Gives a String-representation of a Match
	 * @return the String representing a Match
	 */
	
	public String toString() {
		String txt = "Match( Day: "+ this.getDay() + ", Home: " + this.getTeamHome().getNm()+" "+ this.getGoalsHome()+ ", Away: " + this.getTeamAway().getNm()+" "+ this.getGoalsAway()+")";  
		//String txt= Integer.toString(homeIndex) + " " + Integer.toString(awayIndex) + " "+ Integer.toString(day);
		
		return txt;
	}
	
	/**
	 * Checks if two Matches are equals to each other.
	 * Team home/away names, goals and indexes, as well as the day are taken into account
	 * @return a boolean 
	 */
	
	public boolean equals(Object obj){
		if(!(obj instanceof Match)){
			return false;
		}
		Match that = (Match)obj;
		return this.getTeamHome().equals(that.getTeamHome()) &
				this.getTeamAway().equals(that.getTeamAway()) &
				this.getGoalsHome() == that.getGoalsHome() &
				this.getGoalsAway() == that.getGoalsAway() &
				this.getDay() == that.getDay() &
				this.getAwayIndex() == that.getAwayIndex() &
				this.getHomeIndex() == that.getHomeIndex(); 
	}
	
	/**
	 * @return	A string representation of a match that can be written to the XML file
	 */
	public String toWrite() {
		String write = "         <Match>\r\n"
				+ "            <Home>" + getTeamHome().getNm() + "</Home>\r\n"
				+ "            <Away>" + getTeamAway().getNm() + "</Away>\r\n"
				+ "            <Homescore>" + getGoalsHome() + "</Homescore>\r\n"
				+ "            <Awayscore>" + getGoalsAway() + "</Awayscore>\r\n"
				+ "         </Match>\r\n";
		return write;
	}

	/**
	 * Getters
	 */
	
	/**@return the homeIndex of a Team*/
	public int getHomeIndex() {return homeIndex; }
	/**@return the awayIndex of a Team*/
	public int getAwayIndex() {return awayIndex; }
	/**@return the homeTeam*/
	public Team getTeamHome() {return teamHome; }
	/**@return the awayTeam*/
	public Team getTeamAway() {return teamAway; }
	/**@return the day of a Match*/
	public int getDay(){return this.day; }
	/**@return the goals of the homeTeam*/
	public int getGoalsHome() {return goalsHome; }
	/**@return the goals of the awayTeam*/
	public int getGoalsAway() {return goalsAway; }
	/**@return the name of the homeTeam*/
	public String getTeamHomeName() {return teamHomeName; }
	/**@return the name of the awayTeam*/
	public String getTeamAwayName() {return teamAwayName; }
	/**@return the score of a Match*/
	public String getScore() {return score; }
	/**@return the goals of the homeTeam in String format*/
	public String getGoalsHomeS() {return goalsHomeS; }
	/**@return the goals of the awayTeam in String format*/
	public String getGoalsAwayS() {return goalsAwayS; }
	
	/**
	 * Setters
	 */
	
	/**Sets the homeIndex of a Team*/
	public void setHomeIndex(int homeIndex) {this.homeIndex = homeIndex; }
	/**Sets the awayIndex of a Team*/
	public void setAwayIndex(int awayIndex) {this.awayIndex = awayIndex; }
	/**Sets the homeTeam*/
	public void setTeamHome(Team team){this.teamHome = team; }
	/**Sets the awayTeam*/
	public void setTeamAway(Team team){this.teamAway = team; }
	/**Sets the goals of the homeTeam*/
	public void setGoalsHome(Integer goalsHome) {
		this.goalsHome = goalsHome;
		this.setGoalsHomeS(goalsHome.toString());
	}
	/**Sets the goals of the awayTeam*/
	public void setGoalsAway(Integer goalsAway) {
		this.goalsAway = goalsAway;
		this.setGoalsAwayS(goalsAway.toString());
	}
	
	/**Sets the name of the homeTeam*/
	public void setTeamHomeName(String teamHomeName) {this.teamHomeName = teamHomeName;	}
	/**Sets the name of the awayTeam*/
	public void setTeamAwayName(String teamAwayName) {this.teamAwayName = teamAwayName; }
	/**Sets the score of a Match*/
	public void setScore(String score) {this.score = score;	}
	/**Sets the goals of the homeTeam in String format*/
	public void setGoalsHomeS(String goalsHomeS) {this.goalsHomeS = goalsHomeS;	}
	/**Sets the goals of the awayTeam in String format*/
	public void setGoalsAwayS(String goalsAwayS) {this.goalsAwayS = goalsAwayS;	}
}
