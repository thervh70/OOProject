package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controller.saveGame;
import Model.DBmain;
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
}
