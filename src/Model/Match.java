package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Match {

	SimpleStringProperty tHome;
	SimpleStringProperty tAway;
	SimpleIntegerProperty gHome;
	SimpleIntegerProperty gAway;
	
	private Team teamHome, teamAway;
	private int goalsHome, goalsAway, day;
	boolean played;
	
	public Match(Team teamHome, Team teamAway, int day){
		this.teamHome = teamHome;
		this.teamAway = teamAway;
		this.played = false;
		this.day = day;
	}
	
}
