package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controller.saveGame;
import Model.Fieldplayer;
import Model.Goalkeeper;
import Model.Standing;
import Model.Team;

public class TeamTest{
	
	Fieldplayer p1 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 182556, true, 0, 0, 44, 56, 81, 39, 72, 58);
	Fieldplayer p2 = new Fieldplayer("Guus", "Meeuwis", "LW", 35, 19546, true, 0, 0, 33, 86, 16, 46, 71, 22);
	Goalkeeper p3 = new Goalkeeper("Frits", "Fritsmans", "GK", 21, 182556, true, 0, 0, 44, 56, 81, 39, 72, 55, 58);
	Goalkeeper p4 = new Goalkeeper("Guus", "Meeuwis", "GK", 35, 19546, true, 0, 0, 33, 86, 16, 46, 71, 46, 22);
	Fieldplayer p5 = new Fieldplayer("Frits", "Fritsmans", "LB", 21, 182556, true, 0, 0, 44, 56, 81, 39, 72, 58);
	Fieldplayer p6 = new Fieldplayer("Edward", "Stutjes", "ST", 29, 169745, true, 0, 0, 66, 52, 39, 48, 56, 87);
	Fieldplayer p7 = new Fieldplayer("Gijsje", "Truusje", "CDM", 25, 154679, true, 0, 0, 65, 49, 87, 55, 69, 47);
	Fieldplayer p8 = new Fieldplayer("Gijsje", "Truusje", "CB", 25, 154679, true, 1, 0, 65, 49, 87, 55, 69, 47);
	Fieldplayer p9 = new Fieldplayer("Gijsje", "Truusje", "CM", 25, 154679, false, 2, 0, 65, 49, 87, 55, 69, 47);
	Fieldplayer p10 = new Fieldplayer("Gijsje", "Truusje", "CAM", 25, 154679, false, 2, 2, 65, 49, 87, 55, 69, 47);
	Fieldplayer p11 = new Fieldplayer("Gijsje", "Truusje", "RW", 25, 154679, true, 0, 0, 65, 49, 87, 55, 69, 47);

	Team t1 = new Team("Ajax", 100000, 150000);
	Team t2 = new Team("Feyenoord", 100000, 150000);
	Team t3 = new Team("Ajax", 100000, 150000);
	String res = "      <Team>\r\n"
			+ "         <Teamname>Ajax</Teamname>\r\n"
			+ "         <Virtual_budget>100000</Virtual_budget>\r\n"
			+ "         <Budget>150000</Budget>\r\n"
			+ "         <Points>0</Points>\r\n"
			+ "         <Goalsfor>0</Goalsfor>\r\n"
			+ "         <Goalsagainst>0</Goalsagainst>\r\n"
			+ "         <Selection>\r\n"
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
			+ "            <Keeper>\r\n"
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
			+ "            </Keeper>\r\n"
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
			+ "            <Keeper>\r\n"
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
			+ "            </Keeper>\r\n"
			+ "         </Teammembers>\r\n"
			+ "      </Team>\r\n";
	
	@Test
	public void NewStandingTest(){
		t1.newStanding();
		assertTrue(t1.getStanding().equals(new Standing(0, t1.getNm(), 0, 0, 0)));
		
	}
	
	@Test
	public void removeSelectionTest() {
		t1.addPlayer(p2);t1.toSelection(p2);
		t1.addPlayer(p3);t1.toSelection(p3);
		t1.addPlayer(p4);t1.toSelection(p4);
		assertTrue(t1.getSelection() != null);
		t1.removeSelection();
		assertTrue(t1.getSelection().size() == 0);
	}
	
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
		t1.addPlayer(p1);t1.toSelection(p1);
		t1.addPlayer(p2);
		t1.addPlayer(p3);
		t1.addPlayer(p4);
		assertEquals(t1.getSize(), 4);
		t1.removePlayer(p1);
		t1.removePlayer(p3);
		t1.removePlayer(p5);
		assertEquals(t1.getSize(), 2);
	}
	
	@Test
	public void toSelectionTest() {
		t1.addPlayer(p1);
		t1.toSelection(p1);
		assertEquals(t1.getSize(), 1);
		t1.toSelection(p1);
		assertEquals(t1.getSize(), 1);
	}
	
	@Test
	public void fromSelectionTest() {
		t1.addPlayer(p1);
		t1.toSelection(p1);
		assertEquals(t1.getSize(), 1);
		t1.fromSelection(p1);
		t1.fromSelection(p2);
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
		assertEquals(t1.toString(), "Team: Ajax(0), Virtual budget: 100000, Budget: 150000\n"
				+ "Points: 0 GoalsFor: 0 GoalsAgainst: 0 GoalsDifference: 0\nSelection:\nSubstitutes:");
		t1.addPlayer(p1);
		assertEquals(t1.toString(), "Team: Ajax(1), Virtual budget: 100000, Budget: 150000\n"
				+ "Points: 0 GoalsFor: 0 GoalsAgainst: 0 GoalsDifference: 0\n"
				+ "Selection:\n"
				+ "Substitutes:\n  Name: Frits Fritsmans Position: RB age: 21 price: 182556\n    "
				+ "Card: None Available: Yes Duration: 0\n    "
				+ "Pace: 44 Shooting: 56 Passing: 81 Dribbling: 39 Defending: 72 Physical: 58");
		t1.toSelection(p1);
		assertEquals(t1.toString(), "Team: Ajax(1), Virtual budget: 100000, Budget: 150000\n"
				+ "Points: 0 GoalsFor: 0 GoalsAgainst: 0 GoalsDifference: 0\n"
				+ "Selection:\n  Name: Frits Fritsmans Position: RB age: 21 price: 182556\n    "
				+ "Card: None Available: Yes Duration: 0\n    "
				+ "Pace: 44 Shooting: 56 Passing: 81 Dribbling: 39 Defending: 72 Physical: 58\n"
				+ "Substitutes:");
	}
	
	@Test
	public void toWriteTest() {
		t1.addPlayer(p1);t1.toSelection(p1);
		t1.addPlayer(p3);
		assertNotEquals(t1.toWrite(), res);
		t1.toSelection(p3);
		assertEquals(t1.toWrite(), res);
	}
	
	@Test
	public void calcAttScoreTest() {
		assertEquals(t1.calcAttScore(), 0, 0.001);
		t1.addPlayer(p2);t1.toSelection(p2);
		t1.addPlayer(p3);t1.toSelection(p3);
		t1.addPlayer(p4);t1.toSelection(p4);
		t1.addPlayer(p5);t1.toSelection(p5);
		t1.addPlayer(p6);t1.toSelection(p6);
		t1.addPlayer(p7);t1.toSelection(p7);
		assertEquals(t1.calcAttScore(), 103.0, 0.001);
		t1.removePlayer(p2);
		assertNotEquals(t1.calcAttScore(), 100.0, 0.001);
	}

	
	@Test
	public void calcDefScoreTest() {
		assertEquals(t1.calcDefScore(), 0, 0.001);
		t1.addPlayer(p2);t1.toSelection(p2);
		t1.addPlayer(p3);t1.toSelection(p3);
		t1.addPlayer(p4);t1.toSelection(p4);
		t1.addPlayer(p5);t1.toSelection(p5);
		t1.addPlayer(p6);t1.toSelection(p6);
		t1.addPlayer(p7);t1.toSelection(p7);
		assertEquals(t1.calcDefScore(), 182.0, 0.001);
		t1.removePlayer(p7);
		assertNotEquals(t1.calcDefScore(), 64.0, 0.001);
	}
	
	@Test
	public void countTest() {
		t1.addPlayer(p1);	
		t1.addPlayer(p2);	
		t1.addPlayer(p3);	
		t1.addPlayer(p4);	
		t1.addPlayer(p5);	
		t1.addPlayer(p6);	
		t1.addPlayer(p7);
		t1.addPlayer(p8);
		t1.addPlayer(p9);
		t1.addPlayer(p10);
		t1.addPlayer(p11);
		assertEquals(t1.count(0), 2);
		assertEquals(t1.count(1), 3);
		assertEquals(t1.count(2), 2);
		assertEquals(t1.count(3), 3);
		assertEquals(t1.count(4), 1);
	}
	
	@Test
	public void countSelectionTest() {
		t1.addPlayer(p1);t1.toSelection(p1);
		t1.addPlayer(p2);t1.toSelection(p2);
		t1.addPlayer(p3);t1.toSelection(p3);
		t1.addPlayer(p4);t1.toSelection(p4);
		t1.addPlayer(p5);t1.toSelection(p5);
		t1.addPlayer(p6);t1.toSelection(p6);
		t1.addPlayer(p7);t1.toSelection(p7);
		t1.addPlayer(p8);t1.toSelection(p8);
		t1.addPlayer(p9);t1.toSelection(p9);
		t1.addPlayer(p10);t1.toSelection(p10);
		t1.addPlayer(p11);t1.toSelection(p11);
		assertEquals(t1.countSelection(0), 2);
		assertEquals(t1.countSelection(1), 3);
		assertEquals(t1.countSelection(2), 2);
		assertEquals(t1.countSelection(3), 3);
		assertEquals(t1.countSelection(4), 1);
	}	
	
	@Test
	public void clearCardsInjuriesTest() {
		t1.addPlayer(p8);
		t1.addPlayer(p9);
		saveGame.setDay(1);
		assertEquals(p8.getCard(), 1);
		
	}
	
	@Test
	public void checkAvailTest() {
		t1.addPlayer(p8);
		t1.addPlayer(p9);
		t1.addPlayer(p10);
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
		t2.setBdgt_rel(1000000);
		t2.setBdgt_vir(1000000);
		assertFalse(t1.equals(t2));
	}
	
	@Test
	public void getTeamTest() {
		assertEquals(t1.getTeam().size(), 0);
		t1.addPlayer(p1);
		assertEquals(t1.getTeam().size(), 1);
	}
	
	@Test
	public void getSelectionTest() {
		assertEquals(t1.getSelection().size(), 0);
		t1.addPlayer(p1);
		assertEquals(t1.getSelection().size(), 0);
		t1.toSelection(p1);
		assertEquals(t1.getSelection().size(), 1);
	}
	
	@Test
	public void getNmTest() {
		assertEquals(t1.getNm(), "Ajax");
		assertNotEquals(t2.getNm(), "Ajax");
	}
	
	@Test
	public void getBdgt_virTest() {
		assertEquals(t1.getBdgt_vir(), 100000);
		assertNotEquals(t2.getBdgt_vir(), 1500000);
	}
	
	@Test
	public void getBdgt_relTest() {
		assertEquals(t1.getBdgt_rel(), 150000);
		assertNotEquals(t2.getBdgt_rel(), 1000000);
	}
	
	@Test
	public void getSizeTest() {
		assertEquals(t1.getSize(), 0);
		t1.addPlayer(p1);
		assertEquals(t1.getSize(), 1);
	}
	
	@Test
	public void getSelectionSizeTest() {
		assertEquals(t1.getSelectionSize(), 0);
		t1.addPlayer(p1);
		assertEquals(t1.getSelectionSize(), 0);
		t1.toSelection(p1);
		assertEquals(t1.getSelectionSize(), 1);
	}
	
	@Test
	public void getPlayerTest() {
		t1.addPlayer(p1);
		assertEquals(p1, t1.getPlayer(0));
	}
	
	@Test
	public void getSelectionPlayerTest() {
		t1.addPlayer(p1);t1.toSelection(p1);
		assertEquals(p1, t1.getSelectionPlayer(0));
		t1.addPlayer(p2);t1.toSelection(p2);
		assertNotEquals(p1, t1.getSelectionPlayer(1));
	}
	
	@Test
	public void getSelectionKeeper() {
		t1.addPlayer(p1);t1.toSelection(p1);
		t1.addPlayer(p2);t1.toSelection(p2);
		t1.addPlayer(p3);
		assertEquals(t1.getSelectionKeeper(), null);
		t1.toSelection(p3);
		assertEquals(t1.getSelectionKeeper(), p3);
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
	
	@Test
	public void incPointsTest() {
		assertEquals(t1.getPoints(), 0);
		t1.incPoints(2);
		assertEquals(t1.getPoints(), 2);
	}
	
	@Test
	public void incGoalsForTest() {
		assertEquals(t1.getGoalsFor(), 0);
		t1.incGoalsFor(3);
		assertEquals(t1.getGoalsFor(), 3);
	}
	
	@Test
	public void incGoalsAgainstTest() {
		assertEquals(t1.getGoalsAgainst(), 0);
		t1.incGoalsAgainst(1);
		assertEquals(t1.getGoalsAgainst(), 1);
	}
	
	@Test
	public void calcGoalDifferenceTest() {
		t1.incGoalsFor(4);
		t1.incGoalsAgainst(2);
		t1.calcGoalDifference();
		assertEquals(t1.getGoalDifference(), 2);
	}
	
	@Test
	public void addPointsTest() {
		assertEquals(t1.getPoints(), 0);
		assertEquals(t1.getGoalsFor(), 0);
		assertEquals(t1.getGoalsAgainst(), 0);
		t1.addPoints(3, 5, 2);
		assertEquals(t1.getPoints(), 3);
		assertEquals(t1.getGoalsFor(), 5);
		assertEquals(t1.getGoalsAgainst(), 2);
	}
}
