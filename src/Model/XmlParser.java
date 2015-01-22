package Model;

import java.io.IOException;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import Controller.saveGame;

public class XmlParser {
	
	/**
	 * parseInit takes a String, uses this filename to get the childElements of the overhead-Node (<Save></Save> in this case)
	 * @param infile the filename of the file that has to be read
	 * @return a NodeList of all childNodes of the overhead-Node
	 */
	
	public static NodeList parseInit(String infile) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		NodeList init = null;
		try {
			builder = factory.newDocumentBuilder();
			Document document;
			document = builder.parse(infile);
			init = document.getDocumentElement().getChildNodes();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return init;
	}
	
	/**
	 * Parse a competition from a XML file
	 * @param Part of the XML file that contains all matches in the competition
	 * @return The competition
	 */
	public static Competition parseCompetition(NodeList competition) {
		NodeList comp = competition;
		Competition c = new Competition();
		for(int i=1;i<comp.getLength();i+=2) {
			Node round = comp.item(i);
			NodeList matches = round.getChildNodes();
			int day = Integer.parseInt(matches.item(1).getTextContent());
			for(int j=3;j<matches.getLength();j+=2) {
				Node match = matches.item(j);
				NodeList matchattr = match.getChildNodes();
				Match m = parseMatch(matchattr, day);
				c.add(m);
			}
		}
		return c;
	}
	
	/**
	 * Parse a match from an XML file
	 * @param matchattr
	 * @param day
	 * @return Match object with the attributes that are written in the XMl file
	 */
	private static Match parseMatch(NodeList matchattr, int day) {
		String homename = null, awayname = null;
		int homescore = -1, awayscore = -1;
		for(int i=1;i<matchattr.getLength();i+=2) {
			String content = matchattr.item(i).getTextContent();
			switch(matchattr.item(i).getNodeName()) {
			case "Home": homename = content; break;
			case "Away": awayname = content; break;
			case "Homescore": {homescore = Integer.parseInt(content); }break;
			case "Awayscore": {awayscore = Integer.parseInt(content); }break;
			}
		}
		Team t1 = saveGame.getDB().findTeam(homename);
		Team t2 = saveGame.getDB().findTeam(awayname);
		Match m = new Match(day, t1, t2, homescore, awayscore);
		return m;
	}

	/**
	 * Uses parseInit to get a NodeList of all childNodes. Then parses a Database to an object DBmain
	 * 
	 * @param division NodeList of all teams
	 * @return a DBmain containing all Teams in the Database
	 */
	
	public static DBmain parseDB() {
		NodeList database = XmlParser.parseInit("src/Model/Resources/Database_v11.xml");
		DBmain d = new DBmain();
		for(int i=1;i<database.getLength();i+=2) {
			Node team = database.item(i);
			NodeList teamattrs = team.getChildNodes();
			Team t = parseTeam(teamattrs);
			d.addTeam(t);
		}
		return d;
	}
	
	/**
	 * Method parseDB takes NodeList division and parses a DBmain from an xml-file. Methods parseTeam, 
	 * parserPlayer and parseKeeper are called during this process.
	 * 
	 * Only difference with parseBD() is the ability to take a file-name as origin for parsing the data
	 * 
	 * @param division NodeList of all teams
	 * @return DBmain
	 *
	 * @param infile
	 */
	
	public static DBmain parseDB(NodeList division) throws NullPointerException {
		DBmain d = new DBmain();
	    for(int i=1;i<division.getLength();i+=2) {
	    	Node team = division.item(i);
	    	NodeList teamattrs = team.getChildNodes();
	    	Team t = parseTeam(teamattrs);
	    	d.addTeam(t);
	    }
		return d;
	}
	
	/**
	 * Method parseTeam is called from parseDB and is given a NodeList teamattrs.
	 * It parses a Team from a xml-file to an object, using Method parsePlayer and parseKeeper.
	 * @param teamattrs
	 * @return
	 */
	
	private static Team parseTeam(NodeList teamattrs) throws NullPointerException {
		//Iterating through the nodes and extracting the data. Because of I don't know, only the odd items contain players
	    NodeList teamattributes = teamattrs;
	    String teamname = teamattributes.item(1).getTextContent();
	    String bdgtString = teamattributes.item(3).getTextContent();
	    String bdgtString_rel = teamattributes.item(5).getTextContent();
	    String pointsString = teamattributes.item(7).getTextContent();
	    String goalsForString = teamattributes.item(9).getTextContent();
	    String goalsAgainstString = teamattributes.item(11).getTextContent();
	    int budget_vir = Integer.parseInt(bdgtString);
	    int budget_rel = Integer.parseInt(bdgtString_rel);
	    int points = Integer.parseInt(pointsString);
	    int goalsFor = Integer.parseInt(goalsForString);
	    int goalsAgainst = Integer.parseInt(goalsAgainstString);
	    Team t = new Team(teamname, budget_vir, budget_rel);
	    t.addPoints(points, goalsFor, goalsAgainst);
	    NodeList selection = teamattributes.item(13).getChildNodes();
	    NodeList team = teamattributes.item(15).getChildNodes();
	   
	    for(int i=1;i<selection.getLength();i+=2) {
	    	Node player = selection.item(i);
	    	NodeList playerattributes = player.getChildNodes();
	    	if(player.getNodeName().equals("Player")) {
	    		Fieldplayer p = parsePlayer(playerattributes);
	    		t.addPlayer(p);
	    		t.toSelection(p);
	    	}
	    	if(player.getNodeName().equals("Keeper")) {
	    		Goalkeeper p = parseKeeper(playerattributes);
	    		t.addPlayer(p);
	    		t.toSelection(p);
	    	}
	    }
	    
	    for(int i=1;i<team.getLength();i+=2) {
	    	Node player = team.item(i);
	    	NodeList playerattributes = player.getChildNodes();
	    	if(player.getNodeName().equals("Player")) {
	    		Fieldplayer p = parsePlayer(playerattributes);
	    		t.addPlayer(p);
	    	}
	    	if(player.getNodeName().equals("Keeper")) {
	    		Goalkeeper p = parseKeeper(playerattributes);
	    		t.addPlayer(p);
	    	}
	    }
	    return t;
	  }
	
	/**
	  * Method parsePlayer is called by parseTeam and parses a Fielplayer from xml to an object.
	  * @param playerattributes
	  * @return an object Fieldplayer
	  */
	
	private static Fieldplayer parsePlayer(NodeList playerattributes) {
		String fname = null, lname = null, type = null;
		int age = 0, pri = 0, pac = 0, sho = 0, pas = 0, dri = 0, def = 0, phy = 0, card = 0, dur = 0;
		boolean play = false;
	  	for(int j=0;j<playerattributes.getLength();j++) {
	  		String content = playerattributes.item(j).getTextContent();
	  		switch(playerattributes.item(j).getNodeName()) {
	  		case "Firstname": fname = content; break;
	  		case "Lastname": lname = content; break;
	  		case "Age": age = Integer.parseInt(content); break;
	  		case "Type": type = content; break;
	  		case "Price": pri = Integer.parseInt(content); break;
	  		case "Pace": pac = Integer.parseInt(content); break;
	  		case "Shooting": sho = 	Integer.parseInt(content); break;
	  		case "Passing": pas = Integer.parseInt(content); break;
	  		case "Dribbling": dri = Integer.parseInt(content); break;
	  		case "Defending": def = Integer.parseInt(content); break;
	  		case "Physical": phy = Integer.parseInt(content); break;
	  		case "Available": play = Boolean.parseBoolean(content); break;
	  		case "Card": card = Integer.parseInt(content); break;
	  		case "Duration": dur = Integer.parseInt(content); break;
	  		}
	  	}
	  	Fieldplayer p = new Fieldplayer(fname, lname, type, age, pri, play, card, dur, pac, sho, pas, dri, def, phy);
	  	return p;
	}
	
	/**
	  * Method parseKeeper is called by parseTeam and parses a Goalkeeper from xml to an object.
	  * @param playerattributes
	  * @return an object Goalkeeper
	  */
	
	private static Goalkeeper parseKeeper(NodeList playerattributes) {
		String fname = null, lname = null;
		int age = 0, pri = 0, div = 0, han = 0, kick = 0, ref = 0, spd = 0, ping = 0, hei = 0, card = 0, dur = 0;
		boolean play = false;
	  	for(int j=0;j<playerattributes.getLength();j++) {
	  		String content = playerattributes.item(j).getTextContent();
	  		switch(playerattributes.item(j).getNodeName()) {
	  		case "Firstname": fname = content; break;
	  		case "Lastname": lname = content; break;
	  		case "Age": age = Integer.parseInt(content); break;
	  		case "Price": pri = Integer.parseInt(content); break;
	  		case "Diving": div = Integer.parseInt(content); break;
	  		case "Handling": han = 	Integer.parseInt(content); break;
	  		case "Kicking": kick = Integer.parseInt(content); break;
	  		case "Reflexes": ref = Integer.parseInt(content); break;
	  		case "Speed": spd = Integer.parseInt(content); break;
	  		case "Positioning": ping = Integer.parseInt(content); break;
	  		case "Height": hei = Integer.parseInt(content); break;
	  		case "Available": play = Boolean.parseBoolean(content); break;
	  		case "Card": card = Integer.parseInt(content); break;
	  		case "Duration": dur = Integer.parseInt(content); break;
	  		}
	  	}
	  	Goalkeeper p = new Goalkeeper(fname, lname, "GK", age, pri, play, card, dur, div, han, kick, ref, spd, ping, hei);
	  	return p;
	}	
}