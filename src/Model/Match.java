package Model;

import Controller.saveGame;

public class Match {

	private Team teamHome, teamAway;
	private String teamHomeName, teamAwayName, score, goalsHomeS = "", goalsAwayS = "";
	private int goalsHome, goalsAway, day, homeIndex,awayIndex;
	
	public Match(int day, Team teamHome, Team teamAway){
		this.setTeamHome(teamHome);
		this.setTeamAway(teamAway);
		this.day = day;

		this.setTeamHomeName(teamHome.getNm());
		this.setTeamAwayName(teamAway.getNm());
		this.setScore(goalsHome + " - " + goalsAway);
	}
	
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
	
	public String toString() {
		String txt = "Match( Day: "+ this.getDay() + ", Home: " + this.getTeamHome().getNm()+" "+ this.getGoalsHome()+ ", Away: " + this.getTeamAway().getNm()+" "+ this.getGoalsAway()+")";  
		//String txt= Integer.toString(homeIndex) + " " + Integer.toString(awayIndex) + " "+ Integer.toString(day);
		
		return txt;
	}
	
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
	
	public int getHomeIndex() {return homeIndex; }
	public int getAwayIndex() {return awayIndex; }
	public Team getTeamHome() {return teamHome; }
	public Team getTeamAway() {return teamAway; }
	public int getDay(){return this.day; }
	public int getGoalsHome() {return goalsHome; }
	public int getGoalsAway() {return goalsAway; }
	public String getTeamHomeName() {return teamHomeName; }
	public String getTeamAwayName() {return teamAwayName; }
	public String getScore() {return score; }
	public String getGoalsHomeS() {return goalsHomeS; }
	public String getGoalsAwayS() {return goalsAwayS; }
	
	/**
	 * Setters
	 */
	
	public void setHomeIndex(int homeIndex) {this.homeIndex = homeIndex; }
	public void setAwayIndex(int awayIndex) {this.awayIndex = awayIndex; }
	public void setTeamHome(Team team){this.teamHome = team; }
	public void setTeamAway(Team team){this.teamAway = team; }
	public void setGoalsHome(Integer goalsHome) {
		this.goalsHome = goalsHome;
		this.setGoalsHomeS(goalsHome.toString());
	}
	public void setGoalsAway(Integer goalsAway) {
		this.goalsAway = goalsAway;
		this.setGoalsAwayS(goalsAway.toString());
	}

	public void setTeamHomeName(String teamHomeName) {this.teamHomeName = teamHomeName;	}
	public void setTeamAwayName(String teamAwayName) {this.teamAwayName = teamAwayName; }
	public void setScore(String score) {this.score = score;	}
	public void setGoalsHomeS(String goalsHomeS) {this.goalsHomeS = goalsHomeS;	}
	public void setGoalsAwayS(String goalsAwayS) {this.goalsAwayS = goalsAwayS;	}
}
