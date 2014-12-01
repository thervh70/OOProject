package Database;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XmlParser {

	/**
	 * Method main executes shit
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		DBmain d = parseDB();
		System.out.println(d);
//		writeToXML(d);
	}
	/**
	 * Method parseDB takes NodeList division and parses a DBmain from an xml-file. Methods parseTeam
	 * and parserPlayer are called during this process.
	 * 
	 * @param division NodeList of all teams
	 * @return DBmain
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static DBmain parseDB() throws SAXException, IOException, ParserConfigurationException {
		//Get the DOM Builder Factory and DOM Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		//Load and Parse the XML document. Document contains the complete XML as a Tree.
		Document document = builder.parse("src/Database/Database_v4.xml");
		    
	    NodeList division = document.getDocumentElement().getChildNodes();
		DBmain d = new DBmain();
	    for(int i=1;i<division.getLength();i+=2) {
	    	Node team = division.item(i);
//	    	System.out.println("item "+i+": "+team);
	    	NodeList teamattrs = team.getChildNodes();
	    	Team t = parseTeam(teamattrs);
	    	d.addTeam(t);
	    }
	    return d;
	}
	
  /**
   * Methode parseTeam wordt aangeroepen door methode main en leest een TEAM uit een xml bestand uit. Bij het
   * uitlezen wordt methode parsePlayer aangeroepen.
   * @param teamattrs de verschillende attributen van een team (teamnaam, spelers)
   * @return Team t 
   */
	public static Team parseTeam(NodeList teamattrs) {
		//Iterating through the nodes and extracting the data. Because of I don't know, only the odd items contain players
	    NodeList teamattributes = teamattrs;
	    String teamname = teamattributes.item(1).getTextContent();
	    String bdgtString = teamattributes.item(3).getTextContent();
	    String bdgtString_rel = teamattributes.item(5).getTextContent();
	    int budget_vir = Integer.parseInt(bdgtString);
	    int budget_rel = Integer.parseInt(bdgtString_rel);
	    Team t = new Team(teamname, budget_vir, budget_rel);
	    for(int i=7;i<teamattributes.getLength();i+=2) {
	    	
	    	Node player = teamattributes.item(i);
	    	if(player.getNodeName().equals("PLAYER")) {
	    		NodeList playerattributes = player.getChildNodes();
	    		Player p = parsePlayer(playerattributes);
	    		t.addPlayer(p);
	    	}
	    }
	    return t;
	  }
	  /**
	   * Methode parsePlayer wordt aangeroepen door methode parseTeam en leest een SPELER uit een xml bestand uit.
	   * @param playerattributes de verschillende attributen van de uit te lezen SPELER
	   * @return TestPlayer p
	   */
	public static Player parsePlayer(NodeList playerattributes) {
		String fname = null, lname = null, type = null;
		int age = 0, pri = 0, pac = 0, sho = 0, pas = 0, dri = 0, def = 0, phy = 0;
	  	for(int j=0;j<playerattributes.getLength();j++) {
	  		switch(playerattributes.item(j).getNodeName()) {
	  		case "FIRSTNAME": fname = playerattributes.item(j).getTextContent(); break;
	  		case "LASTNAME": lname = playerattributes.item(j).getTextContent(); break;
	  		case "AGE": age = Integer.parseInt(playerattributes.item(j).getTextContent()); break;
	  		case "TYPE": type = playerattributes.item(j).getTextContent(); break;
	  		case "PRICE": pri = Integer.parseInt(playerattributes.item(j).getTextContent()); break;
	  		case "PACE": pac = Integer.parseInt(playerattributes.item(j).getTextContent()); break;
	  		case "SHOOTING": sho = 	Integer.parseInt(playerattributes.item(j).getTextContent()); break;
	  		case "PASSING": pas = Integer.parseInt(playerattributes.item(j).getTextContent()); break;
	  		case "DRIBBLING": dri = Integer.parseInt(playerattributes.item(j).getTextContent()); break;
	  		case "DEFENDING": def = Integer.parseInt(playerattributes.item(j).getTextContent()); break;
	  		case "PHYSICAL": phy = Integer.parseInt(playerattributes.item(j).getTextContent()); break;
	  		}
	  	}
	  	Player p = new Player(fname, lname, type, age, pri, pac, sho, pas, dri, def, phy);
	  	return p;
	}
	/**
	 * Method writeToXML writes a Database to a xml-file
	 * @param db
	 * @throws FileNotFoundException
	 */
	public static void writeToXML(DBmain db) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Bestandsnaam?");
		String file = sc.next();
		sc.close();
		PrintWriter pw = new PrintWriter(file);
		String wrt = db.toWrite();
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
}
	

