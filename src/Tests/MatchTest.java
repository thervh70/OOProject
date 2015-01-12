package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.DBmain;
import Model.Match;
import Model.Team;
import Model.XmlParser;

public class MatchTest {

	@Test
	public void testEquals(){

		DBmain db = XmlParser.parseDB();
		Team team = db.getTeam(0);
		Team team2 = db.getTeam(1);
		Match match = new Match(1,team, team2);
		Match match2 = new Match(1,team, team2);
		Match match3 = new Match(2,team, team2);
		assertTrue(match.equals(match2));
		assertFalse(match.equals(match3));
		
	}
	
	@Test
	public void testToString() {
		DBmain db = XmlParser.parseDB();
		Team team = db.getTeam(0);
		Team team2 = db.getTeam(1);
		Match match = new Match(1,team, team2);
		assertTrue(match.toString().equals("Match( Day: 1, Home: ADO Den Haag, Away: Ajax)"));
		assertFalse(match.toString().equals("Match( Day: 2, Home: ADO Den Haag, Away: Ajax)"));
	}

	@Test
	public void testToWrite(){
		DBmain db = XmlParser.parseDB();
		Team team = db.getTeam(0);
		Team team2 = db.getTeam(1);
		Match match = new Match(1,team, team2);
		System.out.println(match.toWrite());
		
	}
	
}
