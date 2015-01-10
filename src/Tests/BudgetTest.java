package Tests;
import static org.junit.Assert.*;

import org.junit.Test;

import Controller.Budget;
import Model.DBmain;
import Model.Player;
import Model.Team;
import Model.XmlParser;


public class BudgetTest {

	/**	
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
	**/

}
