package Tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Controller.saveGame;
import Model.Competition;
import Model.Match;
import Model.DBmain;
import Model.Fieldplayer;
import Model.Goalkeeper;
import Model.Team;
import Model.XmlParser;

public class XmlParserTest {
	
	Fieldplayer p1 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 182556, true, 0, 0, 44, 56, 81, 39, 72, 58);
	Fieldplayer p2 = new Fieldplayer("Guus", "Meeuwis", "LW", 35, 19546, true, 0, 0, 33, 86, 16, 46, 71, 22);
	Goalkeeper p3 = new Goalkeeper("Frits", "Fritsmans", "GK", 21, 182556, true, 0, 0, 44, 56, 81, 39, 72, 55, 58);
	Goalkeeper p4 = new Goalkeeper("Guus", "Meeuwis", "GK", 35, 19546, true, 0, 0, 33, 86, 16, 46, 71, 46, 22);
	Fieldplayer p5 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 182556, true, 0, 0, 44, 56, 81, 39, 72, 58);
	Fieldplayer p6 = new Fieldplayer("Edward", "Stutjes", "ST", 29, 169745, true, 0, 0, 66, 52, 39, 48, 56, 87);
	Fieldplayer p7 = new Fieldplayer("Gijsje", "Truusje", "CDM", 25, 154679, true, 0, 0, 65, 49, 87, 55, 69, 47);

	Team t1 = new Team("Ajax", 100000, 150000);
	Team t2 = new Team("Feyenoord", 100000, 150000);

	DBmain d1 = new DBmain();
	DBmain d2 = new DBmain();
	
	Competition c1 = new Competition();
	Competition c2 = new Competition();
	
	Match m1 = new Match(1, t1, t2, 1, 1);
	Match m2 = new Match(1, t1, t2, 3, 2);
	Match m3 = new Match(2, t2, t1, 1, 4);
	Match m4 = new Match(2, t2, t1, 3, 3);
	
	@Test//(expected = Exception.class)
	public void DBmainParserTest() {
		assertEquals(d1.getSize(), 0);
		d1 = XmlParser.parseDB();
		assertNotEquals(d1.getSize(), 0);
		assertEquals(d2.getSize(), 0);
//		String infilewrong = "rareinfile.xml";
//		NodeList listwrong = XmlParser.parseInit(infilewrong);
		String infile = "src/Model/Resources/DB_TEST_DO_NOT_REMOVE.xml";
		NodeList list = XmlParser.parseInit(infile);
		Node db = list.item(1);
		NodeList list2 = db.getChildNodes();
		d2 = XmlParser.parseDB(list2);
	}
	
	@Test
	public void CompetitionParserTest() {
		DBmain db = XmlParser.parseDB();
		saveGame.setDB(db);
		c1.add(m1);
		c1.add(m2);
		c1.add(m3);
		c1.add(m4);
		System.out.println(c1);
		String infile = "src/Model/Resources/COMPETITION_TEST_DO_NOT_REMOVE.xml";
		NodeList list = XmlParser.parseInit(infile);
		Node comp = list.item(1);
		NodeList list2 = comp.getChildNodes();
		c2 = XmlParser.parseCompetition(list2);
		assertTrue(c1.toString().equals(c2.toString()));
	}
	
	
	/*@Test
	public void writeToXMLDBTest() {
		t1.addPlayer(p1);
		t1.addPlayer(p2);
		t1.addPlayer(p3);
		t2.addPlayer(p4);
		t2.addPlayer(p5);
		t2.addPlayer(p6);
		t2.addPlayer(p7);
		d1.addTeam(t1);
		d1.addTeam(t2);
		System.out.println("tester.xml");
//		XmlParser.writeToXML(d1);
		String infile = "src/Model/Resources/tester.xml";
		NodeList list = XmlParser.parseInit(infile);
		Node db = list.item(1);
		NodeList list2 = db.getChildNodes();
		d2 = XmlParser.parseDB(list2);
		assertEquals(d1, d2);
	}*/
	
	/*@Test
	public void writeToXMLCTest() {
		DBmain db = XmlParser.parseDB();
		saveGame.setDB(db);
		c1.add(m1);
		c1.add(m2);
		c1.add(m3);
		c1.add(m4);
//		XmlParser.writeToXML(c1);
		String infile = "src/Model/Resources/tester2.xml";
		NodeList list = XmlParser.parseInit(infile);
		Node c = list.item(1);
		NodeList list2 = c.getChildNodes();
		c2 = XmlParser.parseCompetition(list2);
	}*/
}
