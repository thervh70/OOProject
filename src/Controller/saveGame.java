package Controller;

import java.io.File;
import java.io.PrintWriter;

import Model.Competition;
import Model.DBmain;
import Model.Match;
import Model.Scheduler;
import Model.Team;
import Model.XmlParser;

import org.w3c.dom.*;

public class saveGame {

	private static DBmain DB;
	private static Team myteam;
	private static Competition competition;
	private static int day,buyc,sellc = 0;

	private static String file = "";


	/**@return the ingame Competition*/
	public static Competition getCompetition() {
		return competition;
	}

	/**Sets the ingame Competition*/
	public static void setCompetition(Competition competition) {
		saveGame.competition = competition;
	}

	/**@return the ingame Database*/
	public static DBmain getDB() {
		return DB;
	}

	/**Sets the ingame Database*/
	public static void setDB(DBmain db) {
		saveGame.DB = db;
	}
	
	/**checks if cards and injuries have to be removed. If so, cards and injuries are removed*/
	public static void clearDBcardsInjuries(){
		DB.clearAllCardsInjuries();
	}
	
	/**@return MyTeamName*/
	public static String getMyTeamName(){
		return myteam.getNm();
	}
	
	/**Sets MyTeamName*/
	public static void setMyteam(Team myteam) {
		saveGame.myteam = myteam;
	}
	
	/**@return MyTeam*/
	public static Team getMyTeam(){
		return myteam;
	}

	/**Sets MyTeam*/
	public static int getDay() {
		return day;
	}
	
	/**Sets day*/
	public static void setDay(int day) {
		saveGame.day = day;
	}
	
	/**@return Buycounter*/
	public static int getBuyc() {
		return buyc;
	}

	/**Sets the Buycounter*/
	public static void setBuyc(int buyc) {
		saveGame.buyc = buyc;
	}

	/**@return Sellcounter*/
	public static int getSellc() {
		return sellc;
	}

	/**Sets Sellcounter*/
	public static void setSellc(int sellc) {
		saveGame.sellc = sellc;
	}

	/**@return the filename of a game*/
	public static String getFile() {
		return file;
	}

	/**Sets the filename of a game*/
	public static void setFile(String file) {
		saveGame.file = file;
	}
	
	/**Raises the buycounter by 1*/
	public static void cbuyUp(){
		buyc++;
	}
	
	/**Raises the sellcounter by 1*/
	public static void csellUp(){
		sellc++;
	}
	
	/**Raises the day by 1 if day is smaller than 34*/
	public static void nextDay(){
		if(day < 34){
			day++;
		}
		clearDBcardsInjuries();
		sellc=0;
		buyc=0;
	}
	
	/**Sets the saveGame to all its default settings*/
	public static void setDefaults(){
		DB = null;
		myteam = null;
		competition = null;
		day = 1;
		file = "";
	}	
	
	/**Creates a new save in saveGame*/
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
	
	/**Loads an existing game from Xml by using the XmlParser*/
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
		NodeList mine = team.getChildNodes();
		Node name = mine.item(1);
		Node rank = mine.item(3);
		myteam = DB.findTeam(name.getTextContent());
		myteam.getStanding().setRank(Integer.parseInt(rank.getTextContent()));
		day = Integer.parseInt(current.getTextContent());
		setBuyc(Integer.parseInt(buycounter.getTextContent()));
		setSellc(Integer.parseInt(sellcounter.getTextContent()));
		competition = XmlParser.parseCompetition(comp.getChildNodes());
	}

	/**Writes a game to Xml*/
	public static void write(String infile) {
		File file = new File("src/Controller/Saves/" + infile);
		PrintWriter wr;
		try {
			wr = new PrintWriter(file);
			wr.println("<Save>");
			wr.print(DB.toWrite());
			wr.print("   <Myteam>\r\n"
					+ "      <Name>"+myteam.getNm()+"</Name>\r\n"
					+ "      <Rank>"+myteam.getStanding().getRank()+"</Rank>\r\n"
					+ "   </Myteam>\r\n");
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
	
	/**Refreshes a Team, replaces an old Team with a new Team*/
	public static void refreshTeam(Team old, Team fresh){
		DB.removeTeam(old);
		DB.addTeam(fresh);
	}
	
	/**Sets the Matchresults for a day*/
	public static void setMatchResult(Match m){
		competition.setMatchesForDay(day, m);
	}
}
