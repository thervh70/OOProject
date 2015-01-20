package Tests;
import static org.junit.Assert.*;

import org.junit.Test;

import Controller.Budget;
import Model.DBmain;
import Model.Fieldplayer;
import Model.Goalkeeper;
import Model.Player;
import Model.Team;
import Model.XmlParser;
import Controller.saveGame;


public class BudgetTest {
	
	saveGame s = new saveGame();
	DBmain db = XmlParser.parseDB();
	Team t1 = db.getTeam(1);
	Team t2 = db.getTeam(2);
	Player p1 = t1.getPlayer(1);
	Player p2 = t2.getPlayer(1);
	
	
	@Test
	public void tosellTest() throws Exception{
		s.setMyteam(t1);
		s.setDB(db);
		assertTrue(t1.containsPlayer(p1));
		if(Budget.tosell(p1)==null){
			assertTrue(t1.containsPlayer(p1));
		}
		else{
			assertFalse(t1.containsPlayer(p1));
		}
		
	}
	
	@Test
	public void bidTest(){
		s.setBuyc(0);
		assertEquals(s.getBuyc(),0);
		s.setMyteam(t1);
		assertFalse(t1.containsPlayer(p2));
		assertTrue(t2.containsPlayer(p2));
		try {
			if(Budget.bid(p2,t2,t1.getBdgt_vir())==false){
				assertFalse(t1.containsPlayer(p2));
				assertTrue(t2.containsPlayer(p2));
			}
			else{
				assertTrue(t1.containsPlayer(p2));
				assertFalse(t2.containsPlayer(p2));
			}
		} catch (Exception e) {
		}
	}
}
