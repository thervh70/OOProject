package Tests;


import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import Controller.gameEngine;
import Controller.saveGame;
import Model.DBmain;
import Model.Team;
import Model.XmlParser;

public class GameEngineTest {
	
	gameEngine g = new gameEngine();
	static DBmain db = XmlParser.parseDB();
	Team alpha = XmlParser.parseDB().getTeam(0);
	Team beta = XmlParser.parseDB().getTeam(1);
	
	Set<Integer> min = new HashSet<>(); {
	    for (int i = 0; i <= 1; i++) {
	        min.add(i);
	    }
	}	
	
	public static void setDB(){
		saveGame.setDB(db);
	}

	@Test
	public void constructorTest(){
		assertNotEquals(null,g);
	}
	
	@Test
	public void attackTestZero(){
		assertEquals(0, g.attack(0, 0));
	}
	
	@Test
	public void attackTestNonZero(){
		assertNotEquals(0,g.attack(1000, 0));
	}
	
	@Test
	public void minutesTestZero(){
		int[] minutes = g.minutes(1, min);
		assertEquals(1,minutes[0]);
	}
	
	@Test
	public void containsTestTrue(){
		int[] set = {1};
		assertTrue(gameEngine.contains(set, 1));
	}
	
	@Test
	public void containsTestFalse(){
		int[] set = {1};
		assertFalse(gameEngine.contains(set, 2));
	}
	
	@Test
	public void getTeamTest(){
		assertEquals(null,g.getTeamA());
		assertEquals(null,g.getTeamB());
	}
	
	@Test
	public void getAttemptsTest(){
		assertEquals(0,g.getAttemptsA());
		assertEquals(0,g.getAttemptsB());
	}
	
	@Test
	public void getGoalsTest(){
		assertEquals(0,g.getGoalsA());
		assertEquals(0,g.getGoalsB());
	}
	
	@Test
	public void getTotoTest(){
		assertEquals(0, g.getToto());
	}
	
	@Test
	public void getYellowCardsTest(){
		assertEquals(0,g.getYellowcardsA());
		assertEquals(0,g.getYellowcardsB());
	}
	
	@Test
	public void getRedCardsTest(){
		assertEquals(0,g.getRedcardsA());
		assertEquals(0,g.getRedcardsB());
	}
	
	@Test
	public void getInjuriesTest(){
		assertEquals(0,g.getInjuriesA());
		assertEquals(0,g.getInjuriesB());
	}
	
	@Test
	public void getAttempts(){
		assertEquals(0,g.getAttempts());
	}
	
	@Test
	public void getGoalMinutesTest(){
		assertEquals(null,g.getGoalminutesA());
		assertEquals(null,g.getGoalminutesB());
	}
	
	@Test
	public void getAttemptMinutesTest(){
		assertEquals(null,g.getAttemptminutesA());
		assertEquals(null,g.getAttemptminutesB());
	}
	
	@Test
	public void getYellowCardMinutesTest(){
		assertEquals(null,g.getYellowcardminutesA());
		assertEquals(null,g.getYellowcardminutesB());
	}
	
	@Test
	public void getRedCardMinutesTest(){
		assertEquals(null,g.getRedcardminutesA());
		assertEquals(null,g.getRedcardminutesB());
	}
	
	@Test
	public void getInjuryMinutesTest(){
		assertEquals(null,g.getInjuryminutesA());
		assertEquals(null,g.getInjuryminutesB());
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void getYellowPlayer(){
		assertNotEquals(null,g.getYellowPlayerA());
		assertNotEquals(null,g.getYellowPlayerB());
	}

	@SuppressWarnings("static-access")
	@Test
	public void getRedPlayer(){
		assertNotEquals(null,g.getRedPlayerA());
		assertNotEquals(null,g.getRedPlayerB());
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void getInjuredPlayer(){
		assertNotEquals(null,g.getInjuredPlayerA());
		assertNotEquals(null,g.getInjuredPlayerB());
	}
	
	@Test
	public void playTest(){
		setDB();
		g.play(alpha, beta);
	}
	
	@Test
	public void makeTotoTest(){
		assertEquals(0,g.makeToto());
	}
}
