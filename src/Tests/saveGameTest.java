package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controller.saveGame;
import Model.Competition;
import Model.DBmain;
import Model.Scheduler;
import Model.Team;
import Model.XmlParser;

public class saveGameTest {

	@Test
	public void nextDayTest() {
		saveGame.setDB(XmlParser.parseDB());
		saveGame.setDay(33);
		assertEquals(saveGame.getDay(), 33);
		saveGame.nextDay();
		assertEquals(saveGame.getDay(), 34);
		saveGame.nextDay();
		assertEquals(saveGame.getDay(), 34);
	}
	
	@Test
	public void newSaveTest() {
		DBmain d = XmlParser.parseDB();
		Team t = d.getTeam(0);
		saveGame.newSave(t);
		assertEquals(saveGame.getDB(), d);
		assertEquals(saveGame.getDay(), 1);
		assertEquals(saveGame.getFile(), "");
		assertEquals(saveGame.getMyTeamName(), "ADO Den Haag");
	}
	
	@Test
	public void setDBTest() {
		
	}
	
	@Test
	public void setCompetitionTest() {
		Competition c = Scheduler.generate();
		saveGame.setCompetition(c);
		assertEquals(saveGame.getCompetition(), c);
	}
	
	@Test
	public void getBuycTest() {
		assertEquals(0, saveGame.getBuyc());
	}
	
	@Test
	public void setBuycTest() {
		saveGame.setBuyc(8);
		assertEquals(8, saveGame.getBuyc());
		saveGame.setBuyc(0);
	}
	
	@Test
	public void getSellcTest() {
		assertEquals(0, saveGame.getSellc());
	}
	
	@Test
	public void setSellcTest() {
		saveGame.setSellc(8);
		assertEquals(8, saveGame.getSellc());
		saveGame.setSellc(0);
	}
	
	@Test
	public void cbuyUpTest() {
		saveGame.cbuyUp();
		assertEquals(1, saveGame.getBuyc());
		saveGame.cbuyUp();
		assertEquals(2, saveGame.getBuyc());
		saveGame.setBuyc(0);
	}
	
	@Test
	public void csellUpTest() {
		saveGame.setSellc(0);
		saveGame.csellUp();
		assertEquals(1, saveGame.getSellc());
		saveGame.csellUp();
		assertEquals(2, saveGame.getSellc());
		saveGame.setSellc(0);
	}
	
	@Test
	public void setDefaultsTest() {
		DBmain db = XmlParser.parseDB();
		saveGame.setDB(db);
		saveGame.setMyteam(db.getTeam(0));
		saveGame.setCompetition(Scheduler.generate());
		saveGame.setDay(20);
		saveGame.setFile("test");
		saveGame.setDefaults();
		assertEquals(saveGame.getDB(), null);
		assertEquals(saveGame.getMyTeam(), null);
		assertEquals(saveGame.getCompetition(), null);
		assertEquals(saveGame.getDay(), 1);
		assertEquals(saveGame.getFile(), "");
	}
	
	@Test
	public void setMatchResultTest() {
	}
}
