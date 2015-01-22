package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.DBmain;
import Model.Standing;
import Model.Team;
import Model.XmlParser;

public class StandingTest {

	@Test
	public void EqualsTest(){
		Team t1 = new Team("Ajax", 100000, 150000);
		Team t2 = new Team("hoi", 100000, 150000);
		Standing s1 = new Standing(0,t1.getNm(),0,0,0);
		Standing s2 = new Standing(0,t1.getNm(),0,0,0);
		Standing s3 = new Standing(1,t1.getNm(),0,0,0);
		Standing s4 = new Standing(0,t2.getNm(),0,0,0);
		Standing s5 = new Standing(0,t1.getNm(),1,0,0);
		Standing s6 = new Standing(0,t1.getNm(),0,1,0);
		Standing s7 = new Standing(0,t1.getNm(),0,0,1);
		assertTrue(s1.equals(s2));
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(t1));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
	}
	
	@Test
	public void toStringTest() {
		DBmain db = XmlParser.parseDB();
		Team t1 = db.getTeam(0);
		Standing s1 = new Standing(2, t1.getNm(), 5, 8, -3);
		Standing s2 = new Standing(4, t1.getNm(), 3, 1, 2);
		assertEquals(s1.toString(), "Standing [points=2, teamName=ADO Den Haag, goalsFor=5, goalsAgainst=8, goalDifference=-3]");
		assertNotEquals(s2.toString(), "Standing [points=2, teamName=ADO Den Haag, goalsFor=5, goalsAgainst=8, goalDifference=-3]");
	}
	
	@Test
	public void GetterTest() {

		DBmain db = XmlParser.parseDB();
		Team team = db.getTeam(0);
		Standing st = new Standing(1, team.getNm(),2,3,4);
		assertEquals(st.getPoints(),1); 
		assertNotEquals(st.getPoints(),2);

		assertEquals(st.getGoalsFor(),2);
		assertNotEquals(st.getGoalsFor(),3);

		assertEquals(st.getGoalsAgainst(),3);
		assertNotEquals(st.getGoalsAgainst(),4);
		
		assertEquals(st.getGoalDifference(),4);
		assertNotEquals(st.getGoalDifference(),5);
		
		assertEquals(st.getTeamName(), "ADO Den Haag");
		assertNotEquals(st.getTeamName(), "Ajax");
		
		assertEquals(st.getRank(),0);
		assertNotEquals(st.getRank(),3);
	}

	@Test
	public void SetterTest() {

		DBmain db = XmlParser.parseDB();
		Team team = db.getTeam(0);
		Team team2 = db.getTeam(1);
		Standing st = new Standing(0, team.getNm(),0,0,0);
		st.setPoints(1);
		st.setGoalsFor(2);
		st.setGoalsAgainst(3);
		st.setGoalDifference(4);
		st.setTeamName("Ajax");
		st.setRank(5);
		assertEquals(st.getPoints(), 1);
		assertNotEquals(st.getPoints(), 2);

		assertEquals(st.getGoalsFor(), 2);
		assertNotEquals(st.getGoalsFor(), 3);

		assertEquals(st.getGoalsAgainst(), 3);
		assertNotEquals(st.getGoalsAgainst(), 4);
		
		assertEquals(st.getGoalDifference(), 4);
		assertNotEquals(st.getGoalDifference(), 5);
		
		assertEquals(st.getTeamName(), "Ajax");
		assertNotEquals(st.getTeamName(), "ADO Den Haag");
		
		assertEquals(st.getRank(),5);
		assertNotEquals(st.getRank(),0);
	}
	
}
