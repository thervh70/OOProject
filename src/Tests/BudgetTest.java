package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controller.Budget;
import Model.DBmain;
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
	
	
	@SuppressWarnings("static-access")
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
	public void refundTest(){
		assertEquals(1500000, t1.getBdgt_vir());
		Budget.refund(1, t1);
		assertEquals(1500001, t1.getBdgt_vir());
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void bidTest0(){
		s.setBuyc(0);
		assertEquals(s.getBuyc(), 0);
		s.setMyteam(t1);
		assertFalse(t1.containsPlayer(p2));
		assertTrue(t2.containsPlayer(p2));
		double[] bids = {0.7,0.85,0.95,1.05,1.25,1.75,3};
		for (int i = 0; i < bids.length+1; i++) {
			boolean b;
			try {
				b = Budget.bid(p2, t2, (int) (bids[i] * p2.getPri()));
				if (!b) {
					assertFalse(t1.containsPlayer(p2));
					assertTrue(t2.containsPlayer(p2));
				} else {
					assertTrue(t1.containsPlayer(p2));
					assertFalse(t2.containsPlayer(p2));
				}
			} catch (Exception e) {
				assertFalse(t1.containsPlayer(p2));
				assertTrue(t2.containsPlayer(p2));
			}
		}
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void buyTest(){
		try {
			s.setDB(db);
			Budget.buy(p1, t1, 200000, t2);	
			assertTrue(t2.containsPlayer(p1));
		} catch (Exception e) {
			assertFalse(t2.containsPlayer(p1));
		}
	}
}
