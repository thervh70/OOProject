package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Match {

	private Team teamHome, teamAway;
	private int goalsHome, goalsAway, day, homeIndex,awayIndex;
	boolean played;
	
	public Match(int day, Team teamHome, Team teamAway){
		this.setTeamHome(teamHome);
		this.setTeamAway(teamAway);
		this.day = day;
		this.played = false;
	}
	
	public String toString(){
		String txt = "Match( Day: "+ day + ", Home: " + teamHome.getNm()+ ", Away: " + teamAway.getNm()+ ")";  
		//String txt= Integer.toString(homeIndex) + " " + Integer.toString(awayIndex) + " "+ Integer.toString(day);
		
		return txt;
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
	
	public int getDay(){
		return this.day;
	}
}
