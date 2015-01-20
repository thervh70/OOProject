package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Fieldplayer;

public class FieldplayerTest {
	
	Fieldplayer fp1 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 182556, true, 0, 0, 44, 56, 81, 39, 72, 58);
	Fieldplayer fp2 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 182556, true, 0, 0, 44, 56, 81, 39, 72, 58);
	Fieldplayer fp3 = new Fieldplayer("Guus", "Meeuwis", "LW", 35, 19546, true, 0, 0, 33, 86, 16, 46, 71, 22);
	Fieldplayer fp4 = new Fieldplayer("Edward", "Stutjes", "ST", 29, 169745, true, 0, 0, 66, 52, 39, 48, 56, 87);
	Fieldplayer fp5 = new Fieldplayer("Gijsje", "Truusje", "CDM", 25, 154679, true, 0, 0, 65, 49, 87, 55, 69, 47);
	Fieldplayer fp6 = new Fieldplayer("Guus", "Meeuwis", "RW", 35, 19546, true, 0, 0, 33, 86, 16, 46, 71, 22);
	Fieldplayer fp7 = new Fieldplayer("Guus", "Meeuwis", "CAM", 35, 19546, true, 0, 0, 33, 86, 16, 46, 71, 22);
	Fieldplayer fp8 = new Fieldplayer("Edward", "Stutjes", "CM", 29, 169745, true, 0, 6, 66, 52, 39, 48, 56, 87);
	Fieldplayer fp9 = new Fieldplayer("Edward", "Stutjes", "LB", 29, 169745, true, 1, 0, 66, 52, 39, 48, 56, 87);
	Fieldplayer fp10 = new Fieldplayer("Edward", "Stutjes", "CB", 29, 169745, false, 2, 0, 66, 52, 39, 48, 56, 87);



	String res="            <Player>\r\n"
			+ "               <Firstname>Frits</Firstname>\r\n"
			+ "               <Lastname>Fritsmans</Lastname>\r\n"
			+ "               <Age>21</Age>\r\n"
			+ "               <Price>182556</Price>\r\n"
			+ "               <Pace>44</Pace>\r\n"
			+ "               <Shooting>56</Shooting>\r\n"
			+ "               <Passing>81</Passing>\r\n"
			+ "               <Dribbling>39</Dribbling>\r\n"
			+ "               <Defending>72</Defending>\r\n"
			+ "               <Physical>58</Physical>\r\n"
			+ "               <Type>RB</Type>\r\n"
			+ "               <Available>true</Available>\r\n"
			+ "               <Card>0</Card>\r\n"
			+ "               <Duration>0</Duration>\r\n"
			+ "            </Player>\r\n";
	@Test
	public void toStringTest() {
		assertEquals(fp1.toString(),"  Name: Frits Fritsmans Position: RB age: 21 price: 182556\n    Card: None Available: Yes Duration: 0\n    Pace: 44 Shooting: 56 Passing: 81 Dribbling: 39 Defending: 72 Physical: 58");
		assertNotEquals(fp3.toString(),"  Name: Frits Fritsmans Position: RB age: 21 price: 182556\n    Card: None Available: Yes Duration: 0\n    Pace: 44 Shooting: 56 Passing: 81 Dribbling: 39 Defending: 72 Physical: 58");
		assertEquals(fp9.toString(),"  Name: Edward Stutjes Position: LB age: 29 price: 169745\n    Card: Yellow Available: Yes Duration: 0\n    Pace: 66 Shooting: 52 Passing: 39 Dribbling: 48 Defending: 56 Physical: 87");
		assertEquals(fp10.toString(),"  Name: Edward Stutjes Position: CB age: 29 price: 169745\n    Card: Red Available: No Duration: 0\n    Pace: 66 Shooting: 52 Passing: 39 Dribbling: 48 Defending: 56 Physical: 87");

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
	public void checkRedCardTest() {
		assertEquals(fp1.checkRedCard(), false);
		assertEquals(fp10.checkRedCard(), true);
	}
	
	@Test
	public void checkYellowCardTest() {
		assertEquals(fp1.checkYellowCard(), false);
		assertEquals(fp9.checkYellowCard(), true);
	}
	
	@Test
	public void checkInjuryTest() {
		assertEquals(fp1.checkInjury(), false);
		assertEquals(fp8.checkInjury(), true);
	}
	
	@Test
	public void getInjuryTest() {
		assertEquals(fp1.getInjury(), "");
		fp1.setDur(2);
		assertEquals(fp1.getInjury(), "2");
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
	
	@Test
	public void setFnmTest() {
		assertEquals(fp1.getFnm(), "Frits");
		fp1.setFnm("Guus");
		assertEquals(fp1.getFnm(), "Guus");
	}
	
	@Test
	public void setLnmTest() {
		assertEquals(fp1.getLnm(), "Fritsmans");
		fp1.setLnm("Guus");
		assertEquals(fp1.getLnm(), "Guus");
	}
	
	@Test
	public void setPosTest() {
		assertEquals(fp1.getPos(), "RB");
		fp1.setPos("Guus");
		assertEquals(fp1.getPos(), "Guus");
	}
	
	@Test
	public void setAgeTest() {
		assertEquals(fp1.getAge(), 21);
		fp1.setAge(23);
		assertEquals(fp1.getAge(), 23);
	}
	
	@Test
	public void setPriTest() {
		assertEquals(fp1.getPri(), 182556);
		fp1.setPri(10000);
		assertEquals(fp1.getPri(), 10000);
	}
	
	@Test
	public void setPlayTest() {
		assertEquals(fp1.getPlay(), true);
		fp1.setPlay(false);
		assertEquals(fp1.getPlay(), false);
	}
	
	@Test
	public void setCardTest() {
		assertEquals(fp1.getCard(), 0);
		fp1.setCard(2);
		assertEquals(fp1.getCard(), 2);
		fp1.setAvail();
		assertEquals(fp1.getCard(), 0);
		assertEquals(fp1.getPlay(), true);
	}
	
	@Test
	public void setDurTest() {
		assertEquals(fp1.getDur(), 0);
		fp1.setDur(7);
		assertEquals(fp1.getDur(), 7);
	}
	
}