package Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XmlParser {

	/**
	 * Method main executes stuff (only for testing purposes)
	 * @param args
	 * @throws Exception
	 */
	
	public static void main(String[] args) throws Exception {
		DBmain d = parseDB();
		System.out.println(d);
		writeToXML(d);
	}
	
	/**
	 * Method parseDB takes NodeList division and parses a DBmain from an xml-file. Methods parseTeam, 
	 * parserPlayer and parseKeeper are called during this process.
	 * 
	 * @param division NodeList of all teams
	 * @return DBmain
	 */
	
	public static DBmain parseDB() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		DBmain d = new DBmain();
		try {
			builder = factory.newDocumentBuilder(); 
			Document document = builder.parse("src/Model/Resources/Database_v9.xml");   
			NodeList division = document.getDocumentElement().getChildNodes();
		
		    for(int i=1;i<division.getLength();i+=2) {
		    	Node team = division.item(i);
		    	NodeList teamattrs = team.getChildNodes();
		    	Team t = parseTeam(teamattrs);
		    	d.addTeam(t);
		    }
		}
		catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
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
	
	public static DBmain parseDB(String infile) throws NullPointerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		DBmain d = new DBmain();
		try {
			builder = factory.newDocumentBuilder(); 
			Document document = builder.parse("src/Model/Resources/" + infile);   
			NodeList division = document.getDocumentElement().getChildNodes();
			
		    for(int i=1;i<division.getLength();i+=2) {
		    	Node team = division.item(i);
		    	NodeList teamattrs = team.getChildNodes();
		    	Team t = parseTeam(teamattrs);
		    	d.addTeam(t);
		    }
		}
		catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * Method parseTeam is called from parseDB and is given a NodeList teamattrs.
	 * It parses a Team from a xml-file to an object, using Method parsePlayer and parseKeeper.
	 * @param teamattrs
	 * @return
	 */
	
	public static Team parseTeam(NodeList teamattrs) throws NullPointerException {
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
	
	public static Fieldplayer parsePlayer(NodeList playerattributes) {
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
	  	Fieldplayer p = new Fieldplayer(fname, lname, type, age, pri, play, card, 0, pac, sho, pas, dri, def, phy);
	  	return p;
	}
	
	/**
	  * Method parseKeeper is called by parseTeam and parses a Goalkeeper from xml to an object.
	  * @param playerattributes
	  * @return
	  */
	
	public static Goalkeeper parseKeeper(NodeList playerattributes) {
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
	  	Goalkeeper p = new Goalkeeper(fname, lname, "GK", age, pri, play, card, 0, div, han, kick, ref, spd, ping, hei);
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
	

