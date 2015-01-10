package Controller;

import Model.Competition;
import Model.DBmain;
import Model.Scheduler;
import Model.Team;
import Model.XmlParser;

public class saveGame {

	private static DBmain DB;
	private static Team myteam;
	private static Competition competition;
	private static int day = 0;


	public static Competition getCompetition() {
		return competition;
	}

	public static void setCompetition(Competition competition) {
		saveGame.competition = competition;
	}

	public static DBmain getDB() {
		return DB;
	}

	public static void setDB(DBmain db) {
		saveGame.DB = db;
	}
	
	public static void clearDBcards(){
		DB.clearAllCards();
	}

	public static void read(String infile) throws NullPointerException{
		DB = XmlParser.parseDB(infile);
		myteam = DB.getTeam(8);
		day = 1;
		competition = Scheduler.generate();
		
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
	
	public static void nextDay(){
		if(day < 34){
			day++;
		}
	}
	
	public static void setDefaults(){
		DB = null;
		myteam = null;
		competition = null;
		day = 1;
	}
	
	public static void newSave(Team t){
		Competition competition = Scheduler.generate();
		
		saveGame.DB = XmlParser.parseDB();
		saveGame.competition = competition;
		saveGame.day = 1;
		saveGame.myteam = t;
	}

	public static void write(String infile){
		
	}
	
}
