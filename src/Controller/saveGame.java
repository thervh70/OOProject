package Controller;

import java.io.File;
import java.io.PrintWriter;

import Model.Competition;
import Model.DBmain;
import Model.Match;
import Model.Scheduler;
import Model.Team;
import Model.XmlParser;
import View.Results;

import org.w3c.dom.*;

public class saveGame {

	private static DBmain DB;
	private static Team myteam;
	private static Competition competition;
	private static int day = 0;
	private static String file = null;


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
	
	public static void clearDBcardsInjuries(){
		DB.clearAllCardsInjuries();
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

	public static String getFile() {
		return file;
	}

	public static void setFile(String file) {
		saveGame.file = file;
	}
	
	public static void nextDay(){
		if(day < 34){
			day++;
		}
		clearDBcardsInjuries();
	}
	
	public static void setDefaults(){
		DB = null;
		myteam = null;
		competition = null;
		day = 1;
	}
	
	public static void newSave(Team t){
		saveGame.DB = XmlParser.parseDB();
		Competition competition = Scheduler.generate();
		saveGame.competition = competition;
		saveGame.day = 1;
		saveGame.myteam = t;
		
		Results.initialCompetitionTable();
		
	}
	
	public static void loadSave(String infile){
		NodeList saveElements = XmlParser.parseInit("src/Controller/Saves/" +  infile);
		saveGame.setFile(infile);
		Node database = saveElements.item(1);
		Node team = saveElements.item(3);
		Node current = saveElements.item(5);
		Node comp = saveElements.item(7);
		
		DB = XmlParser.parseDB(database.getChildNodes());
		myteam = DB.findTeam(team.getTextContent());
		day = Integer.parseInt(current.getTextContent());
		competition = XmlParser.parseCompetition(comp.getChildNodes());
//		System.out.println(DB);
//		System.out.println(myteam.getNm());
//		System.out.println(day);
//		System.out.println(competition);
	}

	public static void write(String infile) {
		File file = new File("src/Controller/Saves/" + infile);
		PrintWriter wr;
		try {
			wr = new PrintWriter(file);
			wr.println("<Save>");
			wr.print(DB.toWrite());
			wr.print("   <Myteam>"+myteam.getNm()+"</Myteam>\r\n");
			wr.print("   <Currentday>"+day+"</Currentday>\r\n");
			wr.print(competition.toWrite());
			wr.print("</Save>");
			wr.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void refreshTeam(Team old, Team fresh){
		int i = DB.getIndex(old.getNm());
		if(!(i == -1)) {
			DB.removeTeam(old);
			DB.addTeam(i, fresh);
		}
	}
	
	public static void refreshMatch(Match old, Match fresh) {
		int i = competition.getIndex(old);
		if(!(i == -1)) {
			competition.remove(old);
			competition.add(i, fresh);
		}
	}	
}
