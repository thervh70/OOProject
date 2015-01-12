package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Competition;
import Model.DBmain;
import Model.Match;
import Model.Team;
import Model.XmlParser;

public class CompetitionTest {

	@Test
	public void testToString() {
		DBmain db = XmlParser.parseDB();
		Team team = db.getTeam(0);
		Team team2 = db.getTeam(1);
		Match match = new Match(1,team, team2);
		Competition comp = new Competition();
		comp.add(match);
		comp.add(match);
		comp.add(match);
		System.out.println(comp.toString());
		assertTrue(comp.toString().equals("Competition[Match( Day: 1, Home: ADO Den Haag, Away: Ajax), Match( Day: 1, Home: ADO Den Haag, Away: Ajax), Match( Day: 1, Home: ADO Den Haag, Away: Ajax)]"));
	}

}
