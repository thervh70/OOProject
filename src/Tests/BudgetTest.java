package Tests;
import static org.junit.Assert.*;

import org.junit.Test;

import Database.DBmain;
import Database.Player;
import Database.Team;
import Database.XmlParser;
import Engine.*;


public class BudgetTest {

	@Test
	public void test() {
		DBmain test = XmlParser.parseDB();
		Team frits = test.getTeam(1);
		Team frits2 = test.getTeam(2);
		Player testP = frits.getPlayer(0);
		assertTrue(frits.containsPlayer(testP));
		assertFalse(frits2.containsPlayer(testP));
		if(Budget.bid(testP,frits2,frits)==true){
			assertFalse(frits.containsPlayer(testP));
			assertTrue(frits2.containsPlayer(testP));
		}
		else{
			assertTrue(frits.containsPlayer(testP));
			assertFalse(frits2.containsPlayer(testP));
		}
	}

}
