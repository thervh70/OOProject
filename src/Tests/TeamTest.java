package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Database.Fieldplayer;
import Database.Goalkeeper;
import Database.Team;

public class TeamTest{
	
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
	String res = "   <TEAM>\r\n"
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
			+ "   </TEAM>\r\n";

	
	
	@Test
	public void addPlayerTest() {
		assertEquals(t1.getSize(), 0);
		t1.addPlayer(p1);
		t1.addPlayer(p4);
		assertEquals(t1.getSize(), 2);
		t1.addPlayer(p1);
		assertEquals(t1.getSize(), 2);
	}
	
	@Test
	public void containsPlayer() {
		assertFalse(t1.containsPlayer(p1));
		t1.addPlayer(p1);
		assertTrue(t1.containsPlayer(p1));
	}
	
	@Test
	public void removePlayer() {
		t1.addPlayer(p1);
		t1.addPlayer(p2);
		t1.addPlayer(p3);
		t1.addPlayer(p4);
		assertEquals(t1.getSize(), 4);
		t1.removePlayer(p3);
		assertEquals(t1.getSize(), 3);
	}
	
	@Test
	public void addBudget_virTest() {
		assertEquals(t1.getBdgt_vir(), 100000);
		t1.addBudget_vir(50000);
		assertEquals(t1.getBdgt_vir(), 150000);
	}
	
	@Test
	public void subtractBudget_virTest() {
		assertEquals(t1.getBdgt_vir(), 100000);
		t1.subtractBudget_vir(50000);
		assertEquals(t1.getBdgt_vir(), 50000);
	}
	
	@Test
	public void addBudget_relTest() {
		assertEquals(t1.getBdgt_rel(), 150000);
		t1.addBudget_rel(50000);
		assertEquals(t1.getBdgt_rel(), 200000);
	}
	
	@Test
	public void substractBudget_relTest() {
		assertEquals(t1.getBdgt_rel(), 150000);
		t1.subtractBudget_rel(50000);
		assertEquals(t1.getBdgt_rel(), 100000);
	}
	
	@Test
	public void toStringTest() {
		assertEquals(t1.toString(), "Team: Ajax(0), Virtual budget: 100000, Budget: 150000");
		t1.addPlayer(p1);
		assertEquals(t1.toString(), "Team: Ajax(1), Virtual budget: 100000, Budget: 150000\n  Name: Frits Fritsmans Position: RB age: 21 price: 182556\n    "
				+ "Pace: 44 Shooting: 56 Passing: 81 Dribbling: 39 Defending: 72 Physical: 58");
	}
	
	@Test
	public void toWriteTest() {
		t1.addPlayer(p1);
		assertEquals(t1.toWrite(), res);
	}
	
	@Test
	public void calcAttScoreTest() {
		assertEquals(t1.calcAttScore(), 0, 0.001);
		t1.addPlayer(p2);
		t1.addPlayer(p3);
		t1.addPlayer(p4);
		t1.addPlayer(p5);
		t1.addPlayer(p6);
		t1.addPlayer(p7);
		assertEquals(t1.calcAttScore(), 52.0, 0.001);
		t1.removePlayer(p2);
		assertNotEquals(t1.calcAttScore(), 52.0, 0.001);
	}
	
	@Test
	public void calcDefScoreTest() {
		assertEquals(t1.calcDefScore(), 0, 0.001);
		t1.addPlayer(p2);
		t1.addPlayer(p3);
		t1.addPlayer(p4);
		t1.addPlayer(p5);
		t1.addPlayer(p6);
		t1.addPlayer(p7);
		assertEquals(t1.calcDefScore(), 64.0, 0.001);
		t1.removePlayer(p7);
		assertNotEquals(t1.calcDefScore(), 64.0, 0.001);
	}
	
	@Test
	public void countTest() {
		assertEquals(t1.count(0), 0);
		t1.addPlayer(p3);
		t1.addPlayer(p4);
		assertEquals(t1.count(0), 2);
		assertEquals(t1.count(1), 0);
		t1.addPlayer(p5);
		assertEquals(t1.count(1), 1);
		t1.addPlayer(p2);
		t1.addPlayer(p6);
		t1.addPlayer(p7);
		assertEquals(t1.count(3), 1);
		assertEquals(t1.count(2), 1);
		
	}
	
	@Test
	public void equalsTest() {
		t1.addPlayer(p1);
		t1.addPlayer(p2);
		t2.addPlayer(p2);
		t2.addPlayer(p1);
		assertTrue(t1.equals(t1));
		assertFalse(t1.equals(t3));
		assertFalse(t2.equals(t3));
		assertFalse(t1.equals(res));
		t1.removePlayer(p1);
		t1.addPlayer(p4);
		t2.setNm("Ajax");
		assertFalse(t1.equals(t2));
	}
	
	@Test
	public void setNmTest() {
		assertEquals(t1.getNm(), "Ajax");
		t1.setNm("Go Ahead Eagles");
		assertNotEquals(t1.getNm(), "Ajax");
		assertEquals(t1.getNm(), "Go Ahead Eagles");
	}
	
	@Test
	public void setBdgt_virTest() {
		assertEquals(t1.getBdgt_vir(), 100000);
		t1.setBdgt_vir(200000);
		assertEquals(t1.getBdgt_vir(), 200000);
	}
	
	@Test
	public void setBdgt_relTest() {
		assertEquals(t1.getBdgt_rel(), 150000);
		t1.setBdgt_rel(200000);
		assertEquals(t1.getBdgt_rel(), 200000);
	}
}
