package Controller;

import Model.DBmain;
import Model.Team;
import Model.XmlParser;

public class saveGame {

	public static DBmain teams;
	public static Team myteam;

	public static void read(String infile) throws NullPointerException{
		teams = XmlParser.parseDB(infile);
		myteam = teams.getTeam(8);
		
	}
	
	public static String getMyTeamName(){
		return myteam.getNm();
		
	}
	
	public static Team getMyTeam(){
		return myteam;
	}
	
	public static void write(String infile){
		
	}
	
}
