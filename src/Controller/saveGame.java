package Controller;

import Model.Competition;
import Model.DBmain;
import Model.Team;
import Model.XmlParser;

public class saveGame {

	private static DBmain teams;
	private static Team myteam;
	private static Competition competition;
	private static int day = 0;


	public static Competition getCompetition() {
		return competition;
	}

	public static void setCompetition(Competition competition) {
		saveGame.competition = competition;
	}

	public static void read(String infile) throws NullPointerException{
		teams = XmlParser.parseDB(infile);
		myteam = teams.getTeam(8);
		
	}
	
	public static String getMyTeamName(){
		return myteam.getNm();
		
	}
	
	public static void setMyteam(Team myteam) {
		saveGame.myteam = myteam;
	}
	
	public static Team getMyTeam(){
		return myteam;
	}

	public static int getDay() {
		return day;
	}
	
	public static void setDay(int day) {
		saveGame.day = day;
	}

	public static void write(String infile){
		
	}
	
}
