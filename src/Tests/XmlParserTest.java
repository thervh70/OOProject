package Tests;

import static org.junit.Assert.*;


import org.junit.Test;

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
	
	@Test
	public void test() {
		assertEquals(d1.getSize(), 0);
		d1 = XmlParser.parseDB();
		assertNotEquals(d1.getSize(), 0);
//		d2 = XmlParser.parseDB("Database_v8.xml");
	}
	
	@Test
	public void writeToXMLTest() {
		t1.addPlayer(p1);
		t1.addPlayer(p2);
		t1.addPlayer(p3);
		t2.addPlayer(p4);
		t2.addPlayer(p5);
		t2.addPlayer(p6);
		t2.addPlayer(p7);
		d1.addTeam(t1);
		d1.addTeam(t2);
		XmlParser.writeToXML(d1);
		String infile = "Test.xml";
//		d2 = XmlParser.parseDB(infile);
		assertEquals(d1, d2);
	}
}
