package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Database.Fieldplayer;

public class FieldplayerTest {
	
	Fieldplayer fp1 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 182556, 44, 56, 81, 39, 72, 58);
	Fieldplayer fp2 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 182556, 44, 56, 81, 39, 72, 58);
	Fieldplayer fp3 = new Fieldplayer("Guus", "Meeuwis", "LW", 35, 19546, 33, 86, 16, 46, 71, 22);
	String res="      <PLAYER>\r\n"
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
			+ "      </PLAYER>\r\n";
	@Test
	public void toStringTest() {
		assertEquals(fp1.toString(),"Name: Frits Fritsmans Position: RB age: 21 price: 182556\n    Pace: 44 Shooting: 56 Passing: 81 Dribbling: 39 Defending: 72 Physical: 58");
		assertNotEquals(fp3.toString(),"Name: Frits Fritsmans Position: RB age: 21 price: 182556\n    Pace: 44 Shooting: 56 Passing: 81 Dribbling: 39 Defending: 72 Physical: 58");
	}
	
	@Test
	public void toWriteTest() {
		assertEquals(fp1.toWrite(), res);
		assertNotEquals(fp3.toWrite(), res);
	}
	
	@Test
	public void equalsTest() {
		assertTrue(fp1.equals(fp2));
		assertFalse(fp1.equals(fp3));
	}

}