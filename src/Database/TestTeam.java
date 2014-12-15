package Database;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTeam {
	
	Fieldplayer p1 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 182556, 44, 56, 81, 39, 72, 58);
	Fieldplayer p2 = new Fieldplayer("Guus", "Meeuwis", "LW", 35, 19546, 33, 86, 16, 46, 71, 22);
	Goalkeeper p3 = new Goalkeeper("Frits", "Fritsmans", "GK", 21, 182556, 44, 56, 81, 39, 72, 55, 58);
	Goalkeeper p4 = new Goalkeeper("Guus", "Meeuwis", "GK", 35, 19546, 33, 86, 16, 46, 71, 46, 22);
	Fieldplayer p5 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 182556, 44, 56, 81, 39, 72, 58);

	Team t1 = new Team("Ajax", 100000, 150000);
	
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
	public void toStringTest() {
		assertEquals(t1.toString(), "Team: Ajax(0), Virtual budget: 100000, Budget: 150000\n  ");
		t1.addPlayer(p1);
		assertEquals(t1.toString(), "Team: Ajax(1), Virtual budget: 100000, Budget: 150000\n  Name: Frits Fritsmans Position: RB age: 21 price: 182556\n    "
				+ "Pace: 44 Shooting: 56 Passing: 81 Dribbling: 39 Defending: 72 Physical: 58\n  ");
	}
	
	@Test
	public void toWriteTest() {
		
	}
	
	@Test
	public void equalsTest() {
		assertTrue(p1.equals(p1));
		assertTrue(p1.equals(p5));
		assertFalse(p2.equals(p3));
	}
}
