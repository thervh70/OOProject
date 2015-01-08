package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Fieldplayer;

public class FieldplayerTest {
	
	Fieldplayer fp1 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 182556, true, 0, 44, 56, 81, 39, 72, 58);
	Fieldplayer fp2 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 182556, true, 0, 44, 56, 81, 39, 72, 58);
	Fieldplayer fp3 = new Fieldplayer("Guus", "Meeuwis", "LW", 35, 19546, true, 0, 33, 86, 16, 46, 71, 22);
	Fieldplayer fp4 = new Fieldplayer("Edward", "Stutjes", "ST", 29, 169745, true, 0, 66, 52, 39, 48, 56, 87);
	Fieldplayer fp5 = new Fieldplayer("Gijsje", "Truusje", "CDM", 25, 154679, true, 0, 65, 49, 87, 55, 69, 47);
	Fieldplayer fp6 = new Fieldplayer("Guus", "Meeuwis", "RW", 35, 19546, true, 0, 33, 86, 16, 46, 71, 22);
	Fieldplayer fp7 = new Fieldplayer("Guus", "Meeuwis", "CAM", 35, 19546, true, 0, 33, 86, 16, 46, 71, 22);
	Fieldplayer fp8 = new Fieldplayer("Edward", "Stutjes", "CM", 29, 169745, true, 0, 66, 52, 39, 48, 56, 87);
	Fieldplayer fp9 = new Fieldplayer("Edward", "Stutjes", "LB", 29, 169745, true, 0, 66, 52, 39, 48, 56, 87);
	Fieldplayer fp10 = new Fieldplayer("Edward", "Stutjes", "CB", 29, 169745, true, 0, 66, 52, 39, 48, 56, 87);



	String res="         <PLAYER>\r\n"
			+ "            <FIRSTNAME>Frits</FIRSTNAME>\r\n"
			+ "            <LASTNAME>Fritsmans</LASTNAME>\r\n"
			+ "            <AGE>21</AGE>\r\n"
			+ "            <PRICE>182556</PRICE>\r\n"
			+ "            <PACE>44</PACE>\r\n"
			+ "            <SHOOTING>56</SHOOTING>\r\n"
			+ "            <PASSING>81</PASSING>\r\n"
			+ "            <DRIBBLING>39</DRIBBLING>\r\n"
			+ "            <DEFENDING>72</DEFENDING>\r\n"
			+ "            <PHYSICAL>58</PHYSICAL>\r\n"
			+ "            <TYPE>RB</TYPE>\r\n"
			+ "         </PLAYER>\r\n";
	@Test
	public void toStringTest() {
		assertEquals(fp1.toString(),"  Name: Frits Fritsmans Position: RB age: 21 price: 182556\n    Pace: 44 Shooting: 56 Passing: 81 Dribbling: 39 Defending: 72 Physical: 58");
		assertNotEquals(fp3.toString(),"  Name: Frits Fritsmans Position: RB age: 21 price: 182556\n    Pace: 44 Shooting: 56 Passing: 81 Dribbling: 39 Defending: 72 Physical: 58");
	}
	
	@Test
	public void toWriteTest() {
		assertEquals(fp1.toWrite(), res);
		assertNotEquals(fp3.toWrite(), res);
	}
	
	@Test
	public void calcAttScoreTest() {
		assertEquals(fp1.calcAttScore(), 0, 0.001);
		double score = (33*15 + 86*15 + 16*15 + 46*15 + 71*15 + 22*25)/100;
		assertEquals(fp3.calcAttScore(), score, 0.001);
		assertEquals(fp6.calcAttScore(), score, 0.001);
		assertEquals(fp7.calcAttScore(), score, 0.001);
		score = (66*10 + 52*25 + 39*15 + 48*15 + 56*10 + 87*25)/100;
		assertEquals(fp4.calcAttScore(), score, 0.001);
	}
	
	@Test
	public void calcDefScoreTest() {
		assertEquals(fp3.calcDefScore(), 0, 0.001);
		double score = (44*10 + 56*10 + 81*15 + 39*10 + 72*40 + 58*15)/100;
		assertEquals(fp1.calcDefScore(), score, 0.001);
		score = (65*15 + 49*10 + 87*20 + 55*10 + 69*25 + 47*20)/100;
		assertEquals(fp5.calcDefScore(), score, 0.001);
		score = (66*15 + 52*10 + 39*20 + 48*10 + 56*25 + 87*20)/100;
		assertEquals(fp8.calcDefScore(), score, 0.001);
		score = (66*10 + 52*10 + 39*15 + 48*10 + 56*40 + 87*15)/100;
		assertEquals(fp9.calcDefScore(), score, 0.001);
		assertEquals(fp10.calcDefScore(), score, 0.001);
	}
	
	@Test
	public void equalsTest() {
		assertTrue(fp1.equals(fp2));
		assertFalse(fp1.equals(fp3));
		assertFalse(fp1.equals(res));
	}
	
	@Test
	public void setPacTest() {
		assertEquals(fp1.getPac(), 44);
		fp1.setPac(55);
		assertEquals(fp1.getPac(), 55);
	}

	@Test
	public void setShoTest() {
		assertEquals(fp1.getSho(), 56);
		fp1.setSho(55);
		assertEquals(fp1.getSho(), 55);
	}
	
	@Test
	public void setPasTest() {
		assertEquals(fp1.getPas(), 81);
		fp1.setPas(55);
		assertEquals(fp1.getPas(), 55);
	}
	
	@Test
	public void setDriTest() {
		assertEquals(fp1.getDri(), 39);
		fp1.setDri(55);
		assertEquals(fp1.getDri(), 55);
	}
	
	@Test
	public void setDefTest() {
		assertEquals(fp1.getDef(), 72);
		fp1.setDef(55);
		assertEquals(fp1.getDef(), 55);
	}
	
	@Test
	public void setPhyTest() {
		assertEquals(fp1.getPhy(), 58);
		fp1.setPhy(55);
		assertEquals(fp1.getPhy(), 55);
	}
}