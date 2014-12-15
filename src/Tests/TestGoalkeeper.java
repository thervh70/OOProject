package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Database.Goalkeeper;

public class TestGoalkeeper {
	
	Goalkeeper fp1 = new Goalkeeper("Frits", "Fritsmans", "GK", 21, 182556, 44, 56, 81, 39, 72, 55, 58);
	Goalkeeper fp2 = new Goalkeeper("Frits", "Fritsmans", "GK", 21, 182556, 44, 56, 81, 39, 72, 55, 58);
	Goalkeeper fp3 = new Goalkeeper("Guus", "Meeuwis", "GK", 35, 19546, 33, 86, 16, 46, 71, 46, 22);
	String res="      <KEEPER>\r\n"
			+ "         <FIRSTNAME>Frits</FIRSTNAME>\r\n"
			+ "         <LASTNAME>Fritsmans</LASTNAME>\r\n"
			+ "         <AGE>21</AGE>\r\n"
			+ "         <PRICE>182556</PRICE>\r\n"
			+ "         <DIVING>44</DIVING>\r\n"
			+ "         <HANDLING>56</HANDLING>\r\n"
			+ "         <KICKING>81</KICKING>\r\n"
			+ "         <REFLEXES>39</REFLEXES>\r\n"
			+ "         <SPEED>72</SPEED>\r\n"
			+ "         <POSITIONING>55</POSITIONING>\r\n"
			+ "         <HEIGHT>58</HEIGHT>\r\n"
			+ "         <TYPE>GK</TYPE>\r\n"
			+ "      </KEEPER>\r\n";
	@Test
	public void toStringTest() {
		assertEquals(fp1.toString(),"Name: Frits Fritsmans Position: GK age: 21 price: 182556\n    Diving: 44 Handling: 56 Kicking: 81 Reflexes: 39 Speed: 72 Positioning: 55 Height: 58");
		assertNotEquals(fp3.toString(),"Name: Frits Fritsmans Position: GK age: 21 price: 182556\n    Diving: 44 Handling: 56 Kicking: 81 Reflexes: 39 Speed: 72 Positioning: 55 Height: 58");
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
