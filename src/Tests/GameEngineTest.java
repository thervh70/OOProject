package Tests;

import static org.junit.Assert.*;

import java.util.Arrays;
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
	DBmain db = XmlParser.parseDB();
	Team alpha = XmlParser.parseDB().getTeam(0);
	Team beta = XmlParser.parseDB().getTeam(1);
	
	Set<Integer> min = new HashSet<>(); {
	    for (int i = 0; i <= 1; i++) {
	        min.add(i);
	    }
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
		assertEquals(alpha,g.getTeamA());
		assertEquals(beta,g.getTeamB());
	}
	
	@Test
	public void getAttemptsTest(){
		assertEquals(0,g.getAttemptsA());
		assertEquals(0,g.getAttemptsB());
	}
	
	@Test
	public void getGoalsTest(){
		assertNotEquals(null,g.getGoalsA());
		assertNotEquals(null,g.getGoalsB());
	}
	
	@Test
	public void getGoalMinutes(){
		assertEquals(null,g.getGoalminutesA());
	}
	

}
