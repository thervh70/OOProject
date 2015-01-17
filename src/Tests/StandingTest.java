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
	public void GetterTest() {

		DBmain db = XmlParser.parseDB();
		Team team = db.getTeam(0);
		Standing st = new Standing(1, team.getNm(),2,3,4);
		assertTrue(st.getPoints() == 1);
		assertFalse(st.getPoints() == 2);

		assertTrue(st.getGoalsFor() == 2);
		assertFalse(st.getGoalsFor() == 3);

		assertTrue(st.getGoalsAgainst() == 3);
		assertFalse(st.getGoalsAgainst() == 4);
		
		assertTrue(st.getGoalDifference() == 4);
		assertFalse(st.getGoalDifference() == 5);
		
		assertTrue(st.getTeamName().equals("ADO Den Haag"));
		assertFalse(st.getTeamName().equals("Ajax"));
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
		assertTrue(st.getPoints() == 1);
		assertFalse(st.getPoints() == 2);

		assertTrue(st.getGoalsFor() == 2);
		assertFalse(st.getGoalsFor() == 3);

		assertTrue(st.getGoalsAgainst() == 3);
		assertFalse(st.getGoalsAgainst() == 4);
		
		assertTrue(st.getGoalDifference() == 4);
		assertFalse(st.getGoalDifference() == 5);
		
		assertTrue(st.getTeamName().equals("Ajax"));
		assertFalse(st.getTeamName().equals("ADO Den Haag"));
	}
	
}
