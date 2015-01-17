package Model;

public class Match {

	private Team teamHome, teamAway;
	private String teamHomeName, teamAwayName, score;
	private int goalsHome = -1, goalsAway = -1, day, homeIndex,awayIndex;
	boolean played;
	
	public Match(int day, Team teamHome, Team teamAway){
		this.setTeamHome(teamHome);
		this.setTeamAway(teamAway);
		this.day = day;
		this.played = false;

		this.setTeamHomeName(teamHome.getNm());
		this.setTeamAwayName(teamAway.getNm());
		this.setScore(goalsHome + " - " + goalsAway);
	}
	
	public Match(int day, Team teamHome, Team teamAway, int goalsHome, int goalsAway){
		this.setTeamHome(teamHome);
		this.setTeamAway(teamAway);
		this.day = day;
		this.goalsHome = goalsHome;
		this.goalsAway = goalsAway;
		this.played = false;
		
		this.setTeamHomeName(teamHome.getNm());
		this.setTeamAwayName(teamAway.getNm());
		this.setScore(goalsHome + " - " + goalsAway);
	}
	
	public String toString() {
		String txt = "Match( Day: "+ this.getDay() + ", Home: " + this.getTeamHome().getNm()+ ", Away: " + this.getTeamAway().getNm()+ ")";  
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

	public String getTeamHomeName() {
		return teamHomeName;
	}

	public void setTeamHomeName(String teamHomeName) {
		this.teamHomeName = teamHomeName;
	}

	public String getTeamAwayName() {
		return teamAwayName;
	}

	public void setTeamAwayName(String teamAwayName) {
		this.teamAwayName = teamAwayName;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
}
