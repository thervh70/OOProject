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
		teams = XmlParser.parseDB(infile);
		myteam = teams.getTeam(0);
		
	}
	
	public static String getMyTeamName(){
		return myteam.getNm();
		
	}
	
	public static Team getMyTeam(){
		return myteam;
	}
	
}
