package Model;

public class Match {

	private Team teamHome, teamAway;
	private int goalsHome = -1, goalsAway = -1, day, homeIndex,awayIndex;
	boolean played;
	
	public Match(int day, Team teamHome, Team teamAway){
		this.setTeamHome(teamHome);
		this.setTeamAway(teamAway);
		this.day = day;
		this.played = false;
	}
	
	public String toString() {
		String txt = "Match( Day: "+ day + ", Home: " + teamHome.getNm()+ ", Away: " + teamAway.getNm()+ ")";  
		//String txt= Integer.toString(homeIndex) + " " + Integer.toString(awayIndex) + " "+ Integer.toString(day);
		
		return txt;
	}
	
	public boolean equals(Object obj){
		if(!(obj instanceof Match)){
			return false;
		}
		Match that = (Match)obj;
		if(this.getTeamHome().equals(that.getTeamHome()) && this.getTeamAway().equals(that.getTeamAway()) && this.getGoalsHome() == that.getGoalsHome() && this.getGoalsAway() == that.getGoalsAway() && this.getDay() == that.getDay() && this.getAwayIndex() == that.getAwayIndex() && this.getHomeIndex() == that.getHomeIndex()){
			return true;
		}
		return false;
	}
	
	public String toWrite() {
		String write = "      <MATCH>\r\n"
				+ "         <HOME>" + getTeamHome().getNm() + "</HOME>\r\n"
				+ "         <AWAY>" + getTeamAway().getNm() + "</AWAY>\r\n"
				+ "         <HOMESCORE>" + getGoalsHome() + "</HOMESCORE>\r\n"
				+ "         <AWAYSCORE>" + getGoalsAway() + "</AWAYSCORE>\r\n"
				+ "      </MATCH>\r\n";
		return write;
	}

	public int getHomeIndex() {
		return homeIndex;
	}

	public void setHomeIndex(int homeIndex) {
		this.homeIndex = homeIndex;
	}

	public int getAwayIndex() {
		return awayIndex;
	}

	public void setAwayIndex(int awayIndex) {
		this.awayIndex = awayIndex;
	}
	
	public void setTeamHome(Team team){
		this.teamHome = team; 
	}
	
	public void setTeamAway(Team team){
		this.teamAway = team; 
	}
	
	public Team getTeamHome() {
		return teamHome;
	}

	public Team getTeamAway() {
		return teamAway;
	}

	public int getDay(){
		return this.day;
	}

	public int getGoalsHome() {
		return goalsHome;
	}

	public int getGoalsAway() {
		return goalsAway;
	}

	public void setGoalsHome(int goalsHome) {
		this.goalsHome = goalsHome;
	}

	public void setGoalsAway(int goalsAway) {
		this.goalsAway = goalsAway;
	}
}
