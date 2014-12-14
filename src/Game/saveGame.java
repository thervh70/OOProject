package Game;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Database.DBmain;
import Database.Team;
import Database.XmlParser;

public class saveGame {

	public static DBmain teams;
	public static Team myteam;
	
	public static void read(String infile){
		try {
			teams = XmlParser.parseDB(infile);
			myteam = teams.getTeam(0);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String getMyTeamName(){
		return myteam.getNm();
		
	}
	
	public static Team getMyTeam(){
		return myteam;
	}
	
}
