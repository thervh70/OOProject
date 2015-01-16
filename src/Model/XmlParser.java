package Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import Controller.saveGame;

public class XmlParser {

	/**
	 * Method main executes stuff (only for testing purposes)
	 * @param args
	 * @throws Exception
	 */
	
	public static void main(String[] args) throws Exception {
		saveGame.loadSave("TestSave.xml");
	}
	
	/**
	 * Method parseDB takes NodeList division and parses a DBmain from an xml-file. Methods parseTeam, 
	 * parserPlayer and parseKeeper are called during this process.
	 * 
	 * @param division NodeList of all teams
	 * @return DBmain
	 */
	
	public static NodeList parseInit(String infile) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		NodeList init = null;
		try {
			builder = factory.newDocumentBuilder();
			Document document;
			document = builder.parse("src/Model/Resources/" + infile);
			init = document.getDocumentElement().getChildNodes();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return init;
	}
	
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
	
	private static Match parseMatch(NodeList matchattr, int day) {
		String homename = null, awayname = null;
		int homescore = -1, awayscore = -1;
		for(int i=1;i<matchattr.getLength();i+=2) {
			String content = matchattr.item(i).getTextContent();
			switch(matchattr.item(i).getNodeName()) {
			case "HOME": homename = content; break;
			case "AWAY": awayname = content; break;
			case "HOMESCORE": homescore = Integer.parseInt(content); break;
			case "AWAYSCORE": awayscore = Integer.parseInt(content); break;
			}
		}
		Team t1 = saveGame.getDB().findTeam(homename);
		Team t2 = saveGame.getDB().findTeam(awayname);
		Match m = new Match(day, t1, t2, homescore, awayscore);
		return m;
	}
	
	public static DBmain parseDB() {
		NodeList database = XmlParser.parseInit("src/Model/Resources/Database_v9.xml");
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
	 * Only difference is the ability to take a file-name as origin for parsing the data
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
	    int budget_vir = Integer.parseInt(bdgtString);
	    int budget_rel = Integer.parseInt(bdgtString_rel);
	    Team t = new Team(teamname, budget_vir, budget_rel);
	    NodeList selection = teamattributes.item(7).getChildNodes();
	    NodeList team = teamattributes.item(9).getChildNodes();
	   
	    for(int i=1;i<selection.getLength();i+=2) {
	    	Node player = selection.item(i);
	    	NodeList playerattributes = player.getChildNodes();
	    	if(player.getNodeName().equals("PLAYER")) {
	    		Fieldplayer p = parsePlayer(playerattributes);
	    		t.addPlayer(p);
	    		t.toSelection(p);
	    	}
	    	else if(player.getNodeName().equals("KEEPER")) {
	    		Goalkeeper p = parseKeeper(playerattributes);
	    		t.addPlayer(p);
	    		t.toSelection(p);
	    	}
	    }
	    
	    for(int i=1;i<team.getLength();i+=2) {
	    	Node player = team.item(i);
	    	NodeList playerattributes = player.getChildNodes();
	    	if(player.getNodeName().equals("PLAYER")) {
	    		Fieldplayer p = parsePlayer(playerattributes);
	    		t.addPlayer(p);
	    	}
	    	else if(player.getNodeName().equals("KEEPER")) {
	    		Goalkeeper p = parseKeeper(playerattributes);
	    		t.addPlayer(p);
	    	}
	    }
	    return t;
	  }
	
	/**
	  * Method parsePlayer is called by parseTeam and parses a Fielplayer from xml to an object.
	  * @param playerattributes
	  * @return
	  */
	
	private static Fieldplayer parsePlayer(NodeList playerattributes) {
		String fname = null, lname = null, type = null;
		int age = 0, pri = 0, pac = 0, sho = 0, pas = 0, dri = 0, def = 0, phy = 0, card = 0, dur = 0;
		boolean play = false;
	  	for(int j=0;j<playerattributes.getLength();j++) {
	  		String content = playerattributes.item(j).getTextContent();
	  		switch(playerattributes.item(j).getNodeName()) {
	  		case "FIRSTNAME": fname = content; break;
	  		case "LASTNAME": lname = content; break;
	  		case "AGE": age = Integer.parseInt(content); break;
	  		case "TYPE": type = content; break;
	  		case "PRICE": pri = Integer.parseInt(content); break;
	  		case "PACE": pac = Integer.parseInt(content); break;
	  		case "SHOOTING": sho = 	Integer.parseInt(content); break;
	  		case "PASSING": pas = Integer.parseInt(content); break;
	  		case "DRIBBLING": dri = Integer.parseInt(content); break;
	  		case "DEFENDING": def = Integer.parseInt(content); break;
	  		case "PHYSICAL": phy = Integer.parseInt(content); break;
	  		case "AVAILABLE": play = Boolean.parseBoolean(content); break;
	  		case "CARD": card = Integer.parseInt(content); break;
	  		case "DURATION": dur = Integer.parseInt(content); break;
	  		}
	  	}
	  	Fieldplayer p = new Fieldplayer(fname, lname, type, age, pri, play, card, dur, pac, sho, pas, dri, def, phy);
	  	return p;
	}
	
	/**
	  * Method parseKeeper is called by parseTeam and parses a Goalkeeper from xml to an object.
	  * @param playerattributes
	  * @return
	  */
	
	private static Goalkeeper parseKeeper(NodeList playerattributes) {
		String fname = null, lname = null;
		int age = 0, pri = 0, div = 0, han = 0, kick = 0, ref = 0, spd = 0, ping = 0, hei = 0, card = 0, dur = 0;
		boolean play = false;
	  	for(int j=0;j<playerattributes.getLength();j++) {
	  		String content = playerattributes.item(j).getTextContent();
	  		switch(playerattributes.item(j).getNodeName()) {
	  		case "FIRSTNAME": fname = content; break;
	  		case "LASTNAME": lname = content; break;
	  		case "AGE": age = Integer.parseInt(content); break;
	  		case "PRICE": pri = Integer.parseInt(content); break;
	  		case "DIVING": div = Integer.parseInt(content); break;
	  		case "HANDLING": han = 	Integer.parseInt(content); break;
	  		case "KICKING": kick = Integer.parseInt(content); break;
	  		case "REFLEXES": ref = Integer.parseInt(content); break;
	  		case "SPEED": spd = Integer.parseInt(content); break;
	  		case "POSITIONING": ping = Integer.parseInt(content); break;
	  		case "HEIGHT": hei = Integer.parseInt(content); break;
	  		case "AVAILABLE": play = Boolean.parseBoolean(content); break;
	  		case "CARD": card = Integer.parseInt(content); break;
	  		case "DURATION": dur = Integer.parseInt(content); break;
	  		}
	  	}
	  	Goalkeeper p = new Goalkeeper(fname, lname, "GK", age, pri, play, card, dur, div, han, kick, ref, spd, ping, hei);
	  	return p;
	}
	
	/**
	 * Method writeToXML writes a Database to a xml-file
	 * @param db
	 * @throws FileNotFoundException
	 */
	
	public static void writeToXML(DBmain db) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Bestandsnaam?");
		String file = "src/Model/Resources/" + sc.next();
		sc.close();
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String wrt = db.toWrite();
		pw.print(wrt);
		pw.close();
	}
	
	public static void writeToXML(Competition c) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Bestandsnaam?");
		String file = "src/Model/Resources/" + sc.next();
		sc.close();
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String wrt = c.toWrite();
		pw.print(wrt);
		pw.close();
	}
	
	/**
	 * This methods only purpose was to fix a mistake in the xml-file!
	 */
	
	/*public static DBmain reparser(DBmain d) {
		DBmain res = new DBmain();
		for(int i=0;i<d.getSize();i++) {
			Team t = d.getT(i);
			String teamname = t.getNm();
			int budget = t.getBdgt();
			Team resT = new Team(teamname, budget);
			for(int j=0;j<t.getSize();j++) {
				Player p = t.getPlayer(j);
				String fnm = p.getFnm();
				String lnm = p.getLnm();
				String pos = p.getPos();
				int pac = p.getAge();
				int sho = p.getPac();
				int pas = p.getSho();
				int dri = p.getPas();
				int def = p.getDri();
				int phy = p.getDef();
				int age = p.getPhy();
				Player resP = new Player(fnm, lnm, pos, age, pac, sho, pas, dri, def, phy);
				resT.addPlayer(resP);
			}
			res.addTeam(resT);
		}
		return res;
	}*/	
	
	/**
	 * This methods only purpose was to fix a mistake in the xml-file!
	 */
	
	/*public static DBmain reparser2(DBmain d) {
		DBmain res = new DBmain();
		for(int i=0;i<d.getSize();i++) {
			Team t = d.getTeam(i);
			String teamname = t.getNm();
			int budget_vir = t.getBdgt_vir();
			int budget_rel = t.getBdgt_rel();
			Team resT = new Team(teamname, budget_vir, budget_rel);
			for(int j=0;j<t.getSize();j++) {
				Player p = t.getPlayer(j);
				String pos = p.getPos();
				if(pos.equals("RM")) {p.setPos("RW");}
				else if(pos.equals("LM")) {p.setPos("LW");}
				resT.addPlayer(p);
			}
			res.addTeam(resT);
		}
		return res;
	}*/
}