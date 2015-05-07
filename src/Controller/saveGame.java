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

/**Our class that handles saving a game
 * @author D18.1
 */
public class saveGame {

	private static DBmain DB;
	private static Team myteam;
	private static Competition competition;
	private static int day,buyc,sellc = 0;

	private static String file = "";


	/**Method that gets the savegame competition
	 * @return - competition
	 */
	public static Competition getCompetition() {
		return competition;
	}

	/**Method that sets the savegame competition
	 * @param competition
	 */
	public static void setCompetition(Competition competition) {
		saveGame.competition = competition;
	}

	/**Method that returns the savegame database
	 * @return - DB
	 */
	public static DBmain getDB() {
		return DB;
	}

	/**Method that sets the savegame database
	 * @param db
	 */
	public static void setDB(DBmain db) {
		saveGame.DB = db;
	}
	
	/**Method that checks if cards and injuries have to be removed. If so, cards and injuries are removed
	 */
	public static void clearDBcardsInjuries(){
		DB.clearAllCardsInjuries();
	}
	
	/**Method that gets myteamname
	 * @return - String
	 */
	public static String getMyTeamName(){
		return myteam.getNm();
	}
	
	/**Method that sets myteamname
	 * @param myteam
	 */
	public static void setMyteam(Team myteam) {
		saveGame.myteam = myteam;
	}
	
	/**Method that gets myteam
	 * @return - myteam
	 */
	public static Team getMyTeam(){
		return myteam;
	}

	/**Method that gets the current day
	 * @return - day
	 */
	public static int getDay() {
		return day;
	}
	
	/**Method to set the current day
	 * @param day
	 */
	public static void setDay(int day) {
		saveGame.day = day;
	}
	
	/**Method that gets the buycounter
	 * @return - buyc
	 */
	public static int getBuyc() {
		return buyc;
	}

	/**Method that sets the buycounter
	 * @param buyc
	 */
	public static void setBuyc(int buyc) {
		saveGame.buyc = buyc;
	}

	/**Method that gets the sellcounter
	 * @return - sellc
	 */
	public static int getSellc() {
		return sellc;
	}

	/**Method that sets the sellcounter
	 * @param sellc
	 */
	public static void setSellc(int sellc) {
		saveGame.sellc = sellc;
	}

	/**Method that returns the filename of the savegame
	 * @return - file
	 */
	public static String getFile() {
		return file;
	}

	/**Method that sets the filename of the savegame
	 * @param file
	 */
	public static void setFile(String file) {
		saveGame.file = file;
	}
	
	/**Method that raises the buycounter by one
	 */
	public static void cbuyUp(){
		buyc++;
	}
	
	/**Method that raises the sellcounter by one
	 */
	public static void csellUp(){
		sellc++;
	}
	
	/**Method that raises the current day by one if the current day is less than 34
	 */
	public static void nextDay(){
		if(day < 34){
			day++;
		}
		clearDBcardsInjuries();
		sellc=0;
		buyc=0;
	}
	
	/**Method that sets the savegame to all it's default settings
	 */
	public static void setDefaults(){
		DB = null;
		myteam = null;
		competition = null;
		day = 1;
		file = "";
		buyc = 0;
		sellc = 0;
	}	
	
	/**Method that creates a new save
	 * @param t
	 */
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
	
	/**Method that loads an excisting savegame
	 * @param infile
	 */
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

	/**Method that creates a printable version of the save, so we can print it to xml
	 * @param infile
	 */
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
	
	/**Methods that replaces an old team with a new team
	 * @param old
	 * @param fresh
	 */
	public static void refreshTeam(Team old, Team fresh){
		DB.removeTeam(old);
		DB.addTeam(fresh);
	}
	
	/**Method that sets the machresult for a day
	 * @param m
	 */
	public static void setMatchResult(Match m){
		competition.setMatchesForDay(day, m);
	}
}
