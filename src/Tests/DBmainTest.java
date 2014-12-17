package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Database.Fieldplayer;
import Database.Goalkeeper;
import Database.Team;
import Database.DBmain;

public class DBmainTest {
	
	Fieldplayer p1 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 182556, 44, 56, 81, 39, 72, 58);
	Fieldplayer p2 = new Fieldplayer("Guus", "Meeuwis", "LW", 35, 19546, 33, 86, 16, 46, 71, 22);
	Goalkeeper p3 = new Goalkeeper("Frits", "Fritsmans", "GK", 21, 182556, 44, 56, 81, 39, 72, 55, 58);
	Goalkeeper p4 = new Goalkeeper("Guus", "Meeuwis", "GK", 35, 19546, 33, 86, 16, 46, 71, 46, 22);
	Fieldplayer p5 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 182556, 44, 56, 81, 39, 72, 58);
	Fieldplayer p6 = new Fieldplayer("Edward", "Stutjes", "ST", 29, 169745, 66, 52, 39, 48, 56, 87);
	Fieldplayer p7 = new Fieldplayer("Gijsje", "Truusje", "CDM", 25, 154679, 65, 49, 87, 55, 69, 47);

	Team t1 = new Team("Ajax", 100000, 150000);
	Team t2 = new Team("Feyenoord", 100000, 150000);
	Team t3 = new Team("Ajax", 100000, 150000);
	
	DBmain d1 = new DBmain();
	DBmain d2 = new DBmain();
	
	String write = "<DATABASE>\r\n"
			+ "   <TEAM>\r\n"
			+ "      <TEAMNAME>Ajax</TEAMNAME>\r\n"
			+ "      <VIRTUAL_BUDGET>100000</VIRTUAL_BUDGET>\r\n"
			+ "      <BUDGET>150000</BUDGET>\r\n"
			+ "      <PLAYER>\r\n"
			+ "         <FIRSTNAME>Frits</FIRSTNAME>\r\n"
			+ "         <LASTNAME>Fritsmans</LASTNAME>\r\n"
			+ "         <AGE>21</AGE>\r\n"
			+ "         <PRICE>182556</PRICE>\r\n"
			+ "         <PACE>44</PACE>\r\n"
			+ "         <SHOOTING>56</SHOOTING>\r\n"
			+ "         <PASSING>81</PASSING>\r\n"
			+ "         <DRIBBLING>39</DRIBBLING>\r\n"
			+ "         <DEFENDING>72</DEFENDING>\r\n"
			+ "         <PHYSICAL>58</PHYSICAL>\r\n"
			+ "         <TYPE>RB</TYPE>\r\n"
			+ "      </PLAYER>\r\n"
			+ "   </TEAM>\r\n"
			+ "</DATABASE>\r\n";

	@Test
	public void getSizeTest() {
		assertEquals(d1.getSize(), 0);
		d1.addTeam(t1);
		assertEquals(d1.getSize(), 1);
	}
	
	@Test
	public void addTeamTest() {
		assertEquals(d1.getSize(), 0);
		d1.addTeam(t1);
		assertEquals(d1.getSize(), 1);
	}
	
	@Test
	public void toStringTest() {
		t1.addPlayer(p1);
		d1.addTeam(t1);
		String string = "Divisie: (1)\n"
				+ "Team: Ajax(1), Virtual budget: 100000, Budget: 150000\n  Name: Frits Fritsmans Position: RB age: 21 price: 182556\n    "
				+ "Pace: 44 Shooting: 56 Passing: 81 Dribbling: 39 Defending: 72 Physical: 58\n\n";
		assertEquals(d1.toString(), string);
	}
	
	@Test
	public void toWriteTest() {
		t1.addPlayer(p1);
		d1.addTeam(t1);
		assertEquals(d1.toWrite(), write);
	}
	
	@Test
	public void getTeamTest() {
		t1.addPlayer(p1);
		t1.addPlayer(p2);
		d1.addTeam(t1);
		assertEquals(d1.getTeam(0), t1);
	}
	
	@Test
	public void equalsTest() {
		t1.addPlayer(p1);
		t1.addPlayer(p2);
		t2.addPlayer(p1);
		t2.addPlayer(p1);
		d1.addTeam(t1);
		d1.addTeam(t2);
		d2.addTeam(t1);
		d2.addTeam(t2);
		assertTrue(d1.equals(d2));
		assertFalse(d1.equals(write));
	}

}
