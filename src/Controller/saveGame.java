package Controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

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
	private static int day,buyc,sellc = 0;

	private static String file = "";


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
	
	public static int getBuyc() {
		return buyc;
	}

	public static void setBuyc(int buyc) {
		saveGame.buyc = buyc;
	}

	public static int getSellc() {
		return sellc;
	}

	public static void setSellc(int sellc) {
		saveGame.sellc = sellc;
	}

	public static String getFile() {
		return file;
	}

	public static void setFile(String file) {
		saveGame.file = file;
	}
	
	public static void cbuyUp(){
		buyc++;
	}
	
	public static void csellUp(){
		sellc++;
	}
	
	public static void nextDay(){
		if(day < 34){
			day++;
		}
		clearDBcardsInjuries();
		sellc=0;
		buyc=0;
	}

	public static void setDefaults(){
		DB = null;
		myteam = null;
		competition = null;
		day = 1;
		file = "";
	}	
	
	public static void newSave(Team t){
		saveGame.DB = XmlParser.parseDB();
		Competition competition = Scheduler.generate();
		saveGame.competition = competition;
		saveGame.day = 1;
		saveGame.myteam = t;
		saveGame.file = "";
		for(int i = 0; i < 18; i++){
			saveGame.getDB().getTeam(i).newStanding();
		}
	}
	
	public static void loadSave(String infile){
		NodeList saveElements = XmlParser.parseInit("src/Controller/Saves/" +  infile);
		saveGame.setFile(infile);
		Node database = saveElements.item(1);
		Node team = saveElements.item(3);
		Node current = saveElements.item(5);
		Node buycounter = saveElements.item(7);
		Node sellcounter = saveElements.item(9);
		Node comp = saveElements.item(11);
		
		file = infile;
		DB = XmlParser.parseDB(database.getChildNodes());	
		myteam = DB.findTeam(team.getTextContent());
		day = Integer.parseInt(current.getTextContent());
		setBuyc(Integer.parseInt(buycounter.getTextContent()));
		setSellc(Integer.parseInt(sellcounter.getTextContent()));
		competition = XmlParser.parseCompetition(comp.getChildNodes());
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
			wr.print("   <Buycounter>"+getBuyc()+"</Buycounter>\r\n");
			wr.print("   <Sellcounter>"+getSellc()+"</Sellcounter>\r\n");
			wr.print(competition.toWrite());
			wr.print("</Save>");
			wr.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void refreshTeam(Team old, Team fresh){
		DB.removeTeam(old);
		DB.addTeam(fresh);
	}
	
	public static void setMatchResult(Match m){
		competition.setMatchesForDay(day, m);
	}
}
