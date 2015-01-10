package Controller;
/**Our budget class, handles bidding on players
 * @author Bram
 */

import Model.Player;
import Model.Team;
public class Budget {
	
	private static void buy(Player p, Team buyTeam, int bid, Team myTeam){
		myTeam.subtractBudget_rel(bid);
		myTeam.addPlayer(p);
		buyTeam.removePlayer(p);
		buyTeam.addBudget_rel(bid);
		buyTeam.addBudget_vir(bid);
	}
	
	public static boolean bid(Player p, Team buyTeam, int bid){
		//Checks Team t1 can afford the player
		Team myTeam = saveGame.getMyTeam();
		if(!(bid>myTeam.getBdgt_vir())){
			//subtracts the price of the player from the virtual budget, this will be used in the main product
			myTeam.subtractBudget_vir(bid);
			if(!(bid<(0.8*p.getPri()))){
				if(bid<=(0.9*p.getPri())){
					if(Math.random()>0.6){
						buy(p,buyTeam,bid,myTeam);
						return true;
					}
				}
				else if((0.9*p.getPri())<bid & bid<=p.getPri()){
					if(Math.random()>0.5){
						buy(p,buyTeam,bid,myTeam);
						return true;
					}
				}
				else if((p.getPri()<bid & bid<=(1.5*p.getPri()))){
					if(Math.random()>0.3){
						buy(p,buyTeam,bid,myTeam);
						return true;
					}
				}
				else if((1.5*p.getPri())<bid & bid<=(1.9*p.getPri())){
					if(Math.random()>0.2){
						buy(p,buyTeam,bid,myTeam);
						return true;
					}
				}
				else if((1.9*p.getPri())<bid & bid<=(2*p.getPri())){
					if(Math.random()>0.1){
						buy(p,buyTeam,bid,myTeam);
						return true;
					}
				}
				else{
					myTeam.addBudget_vir(bid);
					return false;
				}	
			}
		}
		return false;
	}	
}