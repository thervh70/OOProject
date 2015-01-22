package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.DBmain;
import Model.Fieldplayer;
import Model.Goalkeeper;
import Model.Team;

public class DBmainTest {
	
	Fieldplayer p1 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 182556, true, 0, 0, 44, 56, 81, 39, 72, 58);
	Fieldplayer p2 = new Fieldplayer("Guus", "Meeuwis", "LW", 35, 19546, true, 0, 0, 33, 86, 16, 46, 71, 22);
	Goalkeeper p3 = new Goalkeeper("Frits", "Fritsmans", "GK", 21, 182556, true, 0, 0, 44, 56, 81, 39, 72, 55, 58);
	Goalkeeper p4 = new Goalkeeper("Guus", "Meeuwis", "GK", 35, 19546, true, 0, 0, 33, 86, 16, 46, 71, 46, 22);
	Fieldplayer p5 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 182556, true, 0, 0, 44, 56, 81, 39, 72, 58);
	Fieldplayer p6 = new Fieldplayer("Edward", "Stutjes", "ST", 29, 169745, true, 0, 0, 66, 52, 39, 48, 56, 87);
	Fieldplayer p7 = new Fieldplayer("Gijsje", "Truusje", "CDM", 25, 154679, true, 0, 0, 65, 49, 87, 55, 69, 47); 
	Fieldplayer p8 = new Fieldplayer("Gijsje", "Truusje", "CDM", 25, 154679, false, 2, 0, 65, 49, 87, 55, 69, 47);
	Fieldplayer p9 = new Fieldplayer("Edward", "Stutjes", "ST", 29, 169745, true, 1, 0, 66, 52, 39, 48, 56, 87);

	Team t1 = new Team("Ajax", 100000, 150000);
	Team t2 = new Team("Feyenoord", 100000, 150000);
	Team t3 = new Team("Ajax", 100000, 150000);
	Team t4 = new Team("Sparta", 100000, 150000);
	
	DBmain d1 = new DBmain();
	DBmain d2 = new DBmain();
	DBmain d3 = new DBmain();
	
	String write = "   <Database>\r\n"
			+ "      <Team>\r\n"
			+ "         <Teamname>Ajax</Teamname>\r\n"
			+ "         <Virtual_budget>100000</Virtual_budget>\r\n"
			+ "         <Budget>150000</Budget>\r\n"
			+ "         <Points>0</Points>\r\n"
			+ "         <Goalsfor>0</Goalsfor>\r\n"
			+ "         <Goalsagainst>0</Goalsagainst>\r\n"
			+ "         <Selection>\r\n"
			+ "         </Selection>\r\n"
			+ "         <Teammembers>\r\n"
			+ "            <Player>\r\n"
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
			+ "            </Player>\r\n"
			+ "         </Teammembers>\r\n"
			+ "      </Team>\r\n"
			+ "   </Database>\r\n";

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
		d1.addTeam(t1);
		assertEquals(d1.getSize(), 1);
	}
	
	@Test
	public void addTeamTest2() {
		assertEquals(d1.getSize(), 0);
		d1.addTeam(t1);
		assertEquals(d1.getSize(), 1);
		d1.addTeam(t1);
		assertEquals(d1.getSize(), 1);
		d1.addTeam(0, t1);
		assertEquals(d1.getSize(), 1);
	}
	
	@Test
	public void clearAllCardsInjuriesTest() {
		assertEquals(p8.getPlay(), false);
		assertEquals(p9.getCard(), 1);
		t1.addPlayer(p8);
		t2.addPlayer(p9);
		t1.addPlayer(p1);
		t1.addPlayer(p2);
		d1.addTeam(t1);
		d1.addTeam(t2);
		d1.clearAllCardsInjuries();
		assertEquals(p8.getPlay(), false);
		assertEquals(p9.getCard(), 1);
	}
	
	@Test
	public void toStringTest() {
		t1.addPlayer(p1);
		d1.addTeam(t1);
		String string = "Divisie: (1)\n----------\n"
				+ "Team: Ajax(1), Virtual budget: 100000, Budget: 150000\nPoints: 0 GoalsFor: 0 GoalsAgainst: 0 GoalsDifference: 0\nSelection:\nSubstitutes:\n  Name: Frits Fritsmans Position: RB age: 21 price: 182556\n    "
				+ "Card: None Available: Yes Duration: 0\n    "
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
		t2.addPlayer(p5);
		t3.addPlayer(p3);
		t3.addPlayer(p4);
		d1.addTeam(t1);
		d1.addTeam(t2);
		d2.addTeam(t1);
		d2.addTeam(t2);
		assertTrue(d1.equals(d2));
		d1.addTeam(t1);
		d3.addTeam(t2);
		d3.addTeam(t3);
		assertFalse(d1.equals(d3));
		d3.removeTeam(t3);
		assertFalse(d1.equals(d3));
		assertFalse(d1.equals(write));
	}
	
	@Test
	public void removeTeamTest() {
		d1.addTeam(t1);
		assertEquals(d1.getSize(), 1);
		d1.removeTeam(t2);
		assertEquals(d1.getSize(), 1);
		d1.removeTeam(t1);
		assertEquals(d1.getSize(), 0);
	}
	
	@Test
	public void lookForPlayer() {
		t1.addPlayer(p1);
		t1.addPlayer(p2);
		t2.addPlayer(p5);
		t2.addPlayer(p6);
		d1.addTeam(t1);
		d1.addTeam(t2);
		assertEquals(d1.lookForPlayer("Guus Meeuwis"), p2);
		assertNotEquals(d1.lookForPlayer("Frits Fritsmans"), p2);
		assertEquals(d1.lookForPlayer("Gijs Kopjes"), null);
	}
	
	@Test
	public void findTeam() {
		d1.addTeam(t1);
		d1.addTeam(t2);
		d1.addTeam(t4);
		assertEquals(d1.findTeam("Ajax"), t1);
		assertNotEquals(d1.findTeam("Feyenoord"), t1);
		assertEquals(d1.findTeam("ADO Den Haag"), null);
	}

}
