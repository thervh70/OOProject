package Controller;
/**Our budget class, handles bidding on players
 * @author Bram
 */

import Model.Player;
import Model.Team;
public class Budget {
	
	/**
	 * Allows a team to bid on players from a different team
	 * @param p - The player Team t1 wants to bid on
	 * @param t1 - t1
	 * @param t2 - t2
	 * @return boolean, we need this for testing purposes. Used to be void.
	 */
	public static boolean bid(Player p, Team t1, Team t2){
		//Checks Team t1 can afford the player
		if(!(t1.getBdgt_vir()<p.getPri())){
			//subtracts the price of the player from the virtual budget, this will be used in the main product
			t1.subtractBudget_vir(p.getPri());
			if (p.getPri()<t1.getBdgt_rel()){
				//Uses a random function so you can't buy every player, we decided 0.6 seems like a fair number
				if(Math.random()>0.6){
					//Fixes all the budgets and players in a team
					t1.subtractBudget_rel(p.getPri());
					t1.addPlayer(p);
					t2.removePlayer(p);
					t2.addBudget_rel(p.getPri());
					t2.addBudget_vir(p.getPri());
					return true;
				}
				//If team t1 isn't allowed to buy the player it resets the virtual budget
				else{
					t1.addBudget_vir(p.getPri());
					return false;
				}
			}
			//If team t1 isn't allowed to buy the player it resets the virtual budget
			else{
				t1.addBudget_vir(p.getPri());
				return false;
			}
		}
		return false;
	}
}