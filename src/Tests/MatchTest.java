package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controller.saveGame;
import Model.DBmain;
import Model.Match;
import Model.Team;
import Model.XmlParser;

public class MatchTest {
	
	DBmain db = XmlParser.parseDB();
	Team t1 = db.getTeam(0);
	Team t2 = db.getTeam(1);
	Team t3 = db.getTeam(2);
	Match m1 = new Match(1, t1, t2, 1, 2);
	Match m2 = new Match(2, t2, t3, 3, 2);
	Match m3 = new Match(1, t1, t2, 1, 2);
	Match m4 = new Match(2, t2, t3, 3, 4);
	
	Match m5 = new Match(4, t1, t2);
	Match m6 = new Match(4, t1, t2);
	
	String write = "         <Match>\r\n"
			+ "            <Home>ADO Den Haag</Home>\r\n"
			+ "            <Away>Ajax</Away>\r\n"
			+ "            <Homescore>1</Homescore>\r\n"
			+ "            <Awayscore>2</Awayscore>\r\n"
			+ "         </Match>\r\n";

	@Test
	public void testEquals(){
		assertTrue(m1.equals(m3));
		assertFalse(m1.equals(m2));
		assertFalse(m1.equals(write));
		assertTrue(m6.equals(m5));
		assertFalse(m4.equals(m2));
	}
	
	@Test
	public void testToString() {
		DBmain db = XmlParser.parseDB();
		Team team = db.getTeam(0);
		Team team2 = db.getTeam(1);
		Match match = new Match(1,team, team2, 0, 0);
		assertEquals(match.toString(), "Match( Day: 1, Home: ADO Den Haag 0, Away: Ajax 0)");
		assertNotEquals(match.toString(), "Match( Day: 2, Home: ADO Den Haag, Away: Ajax)");
	}

	@Test
	public void testToWrite(){
		assertEquals(m1.toWrite(), write);
		assertNotEquals(m2.toWrite(), write);
	}
	
	@Test
	public void getHomeIndexTest() {
		assertEquals(m1.getHomeIndex(), 0);
		m1.setHomeIndex(2);
		assertEquals(m1.getHomeIndex(), 2);
	}
	
	@Test
	public void getAwayIndexTest() {
		assertEquals(m1.getAwayIndex(), 0);
		m1.setAwayIndex(2);
		assertEquals(m1.getAwayIndex(), 2);
	}
	
	@Test
	public void getTeamHomeTest() {
		assertEquals(m1.getTeamHome(), t1);
		m1.setTeamHome(t3);
		assertEquals(m1.getTeamHome(), t3);
	}
	
	@Test
	public void getTeamAwayTest() {
		assertEquals(m1.getTeamAway(), t2);
		m1.setTeamAway(t3);
		assertEquals(m1.getTeamAway(), t3);
	}
	
	@Test
	public void getDayTest() {
		assertEquals(m1.getDay(), 1);
		assertEquals(m2.getDay(), 2);
	}
	
	@Test
	public void getGoalsHomeTest() {
		assertEquals(m1.getGoalsHome(), 1);
		m1.setGoalsHome(4);
		assertEquals(m1.getGoalsHome(), 4);
	}
	
	@Test
	public void getGoalsAwayTest() {
		assertEquals(m1.getGoalsAway(), 2);
		m1.setGoalsAway(4);
		assertEquals(m1.getGoalsAway(), 4);
	}
	
	@Test
	public void getTeamHomeNameTest() {
		assertEquals(m1.getTeamHomeName(), "ADO Den Haag");
		m1.setTeamHomeName("PEC");
		assertEquals(m1.getTeamHomeName(), "PEC");
	}
	
	@Test
	public void getTeamAwayNameTest() {
		assertEquals(m1.getTeamAwayName(), "Ajax");
		m1.setTeamAwayName("PEC");
		assertEquals(m1.getTeamAwayName(), "PEC");
	}
	
	@Test
	public void getScoreTest() {
		assertEquals(m1.getScore(), "1 - 2");
		m1.setScore("3 - 2");
		assertEquals(m1.getScore(), "3 - 2");
	}
	
	@Test
	public void getGoalsHomeSTest() {
		assertEquals(m1.getGoalsHomeS(), "");
		m1.setGoalsHomeS("2");
		assertEquals(m1.getGoalsHomeS(), "2");
	}
	
	@Test
	public void getGoalsAwaySTest() {
		assertEquals(m1.getGoalsAwayS(), "");
		m1.setGoalsAwayS("2");
		assertEquals(m1.getGoalsAwayS(), "2");
	}
	
}
