package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Goalkeeper;

public class GoalkeeperTest {
	
	Goalkeeper fp1 = new Goalkeeper("Frits", "Fritsmans", "GK", 21, 182556, true, 0, 0, 44, 56, 81, 39, 72, 55, 58);
	Goalkeeper fp2 = new Goalkeeper("Frits", "Fritsmans", "GK", 21, 182556, true, 1, 0, 44, 56, 81, 39, 72, 55, 58);
	Goalkeeper fp3 = new Goalkeeper("Guus", "Meeuwis", "GK", 35, 19546, false, 2, 0, 33, 86, 16, 46, 71, 46, 22);
	String res ="            <Keeper>\r\n"
			+ "               <Firstname>Frits</Firstname>\r\n"
			+ "               <Lastname>Fritsmans</Lastname>\r\n"
			+ "               <Age>21</Age>\r\n"
			+ "               <Price>182556</Price>\r\n"
			+ "               <Diving>44</Diving>\r\n"
			+ "               <Handling>56</Handling>\r\n"
			+ "               <Kicking>81</Kicking>\r\n"
			+ "               <Reflexes>39</Reflexes>\r\n"
			+ "               <Speed>72</Speed>\r\n"
			+ "               <Positioning>55</Positioning>\r\n"
			+ "               <Height>58</Height>\r\n"
			+ "               <Type>GK</Type>\r\n"
			+ "               <Available>true</Available>\r\n"
			+ "               <Card>0</Card>\r\n"
			+ "               <Duration>0</Duration>\r\n"
			+ "            </Keeper>\r\n";
	@Test
	public void toStringTest() {
		assertEquals(fp1.toString(),"  Name: Frits Fritsmans Position: GK age: 21 price: 182556\n    Card: None Available: Yes Duration: 0\n    Diving: 44 Handling: 56 Kicking: 81 Reflexes: 39 Speed: 72 Positioning: 55 Height: 58");
		assertNotEquals(fp2.toString(),"  Name: Frits Fritsmans Position: GK age: 35 price: 182556\n    Card: Yellow Available: Yes Duration: 0\n    Diving: 44 Handling: 56 Kicking: 81 Reflexes: 39 Speed: 72 Positioning: 55 Height: 58");
		assertEquals(fp2.toString(),"  Name: Frits Fritsmans Position: GK age: 21 price: 182556\n    Card: Yellow Available: Yes Duration: 0\n    Diving: 44 Handling: 56 Kicking: 81 Reflexes: 39 Speed: 72 Positioning: 55 Height: 58");
		assertEquals(fp3.toString(),"  Name: Guus Meeuwis Position: GK age: 35 price: 19546\n    Card: Red Available: No Duration: 0\n    Diving: 33 Handling: 86 Kicking: 16 Reflexes: 46 Speed: 71 Positioning: 46 Height: 22");
	}
	
	@Test
	public void calcDefScoreTest() {
		assertEquals(55.0, fp1.calcScore(), 0.001);
		assertEquals(fp1.calcScore(), fp2.calcScore(), 0.001);
		assertEquals(47.0, fp3.calcScore(), 0.001);
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
		assertFalse(fp1.equals(res));
	}
	
	@Test
	public void setDivTest() {
		assertEquals(fp1.getDiv(), 44);
		fp1.setDiv(55);
		assertEquals(fp1.getDiv(), 55);
	}
	
	@Test
	public void setHanTest() {
		assertEquals(fp1.getHan(), 56);
		fp1.setHan(55);
		assertEquals(fp1.getHan(), 55);
	}
	
	@Test
	public void setKickTest() {
		assertEquals(fp1.getKick(), 81);
		fp1.setKick(55);
		assertEquals(fp1.getKick(), 55);
	}
	
	@Test
	public void setRefTest() {
		assertEquals(fp1.getRef(), 39);
		fp1.setRef(55);
		assertEquals(fp1.getRef(), 55);
	}
	
	@Test
	public void setSpdTest() {
		assertEquals(fp1.getSpd(), 72);
		fp1.setSpd(55);
		assertEquals(fp1.getSpd(), 55);
	}
	
	@Test
	public void setPingTest() {
		assertEquals(fp1.getPing(), 55);
		fp1.setPing(58);
		assertEquals(fp1.getPing(), 58);
	}
	
	@Test
	public void setHeiTest() {
		assertEquals(fp1.getHei(), 58);
		fp1.setHei(55);
		assertEquals(fp1.getHei(), 55);
	}

	@Test
	public void cardTest(){
		assertTrue(fp1.card() >= 0 && fp1.card() <= 2);
		int number = fp1.card();
		while(number != 1){
			number = fp1.card();
		}
		assertTrue(number == 1);
		while(number != 2){
			number = fp1.card();
		}
		assertTrue(number == 2);
	}
	
	@Test
	public void injuryTest(){
		
		int number = fp1.injury();
		while(number != 0){
			number = fp1.injury();
		}
		assertTrue(number  == 0);
		while(number != 5){
			number = fp1.injury();
		}

		assertTrue(number  == 5);
	}
	
	@Test
	public void clearCardInjuryTest(){
		fp1.setPlay(false);
		fp1.setDur(1);
		fp1.clearCardInjury();
		assertTrue(fp1.getPlay());
		fp1.setDur(0);
		fp1.clearCardInjury();
		assertTrue(fp1.getPlay());
		fp1.setPlay(false);
		fp1.setDur(3);
		fp1.clearCardInjury();
		assertFalse(fp1.getPlay());
	}
	
	@Test
	public void checkRedCardTest(){
		fp1.setCard(2);
		assertTrue(fp1.checkRedCard());
		fp1.setCard(1);
		assertFalse(fp1.checkRedCard());
	}
	
	@Test
	public void checkYellowCardTest(){
		fp1.setCard(1);
		assertTrue(fp1.checkYellowCard());
		fp1.setCard(2);
		assertFalse(fp1.checkYellowCard());
	}
}
