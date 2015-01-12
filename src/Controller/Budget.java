package Controller;
/**Our budget class, handles bidding on players
 * @author Bram
 */

import java.util.Random;

import Model.Player;
import Model.Team;
import Model.DBmain;
public class Budget {
	
	private static void buy(Player p, Team buyTeam, int bid, Team myTeam) throws Exception {
		Team oldBuy = buyTeam;
		Team oldMy = myTeam;
		myTeam.addPlayer(p);
		buyTeam.removePlayer(p);
		
		if(CreateSelection.checkSize(buyTeam)){
			myTeam.subtractBudget_rel(bid);
			buyTeam.addBudget_rel(bid);
			buyTeam.addBudget_vir(bid);
			
			CreateSelection.create(buyTeam);
		}
		else{
			myTeam.removePlayer(p);
			buyTeam.addPlayer(p);
			throw new Exception();
		}
		
		saveGame.refreshTeam(oldBuy, buyTeam);
		saveGame.refreshTeam(oldMy, myTeam);
	}
	
	private static void refund(int bid, Team myTeam){
		myTeam.addBudget_vir(bid);
	}
	
	public static boolean bid(Player p, Team buyTeam, int bid) throws Exception {
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
					else{
						refund(bid,myTeam);
						return false;
					}
				}
				else if((0.9*p.getPri())<bid & bid<=p.getPri()){
					if(Math.random()>0.5){
						buy(p,buyTeam,bid,myTeam);
						return true;
					}
					else{
						refund(bid,myTeam);
						return false;
					}
				}
				else if((p.getPri()<bid & bid<=(1.5*p.getPri()))){
					if(Math.random()>0.3){
						buy(p,buyTeam,bid,myTeam);
						return true;
					}
					else{
						refund(bid,myTeam);
						return false;
					}
				}
				else if((1.5*p.getPri())<bid & bid<=(1.9*p.getPri())){
					if(Math.random()>0.2){
						buy(p,buyTeam,bid,myTeam);
						return true;
					}
					else{
						refund(bid,myTeam);
						return false;
					}
				}
				else if(bid>(1.9*p.getPri())){
					if(Math.random()>0.1){
						buy(p,buyTeam,bid,myTeam);
						return true;
					}
					else{
						refund(bid,myTeam);
						return false;
					}
				}
				else{
					refund(bid,myTeam);
					return false;
				}
			}
			else{
				refund(bid,myTeam);
				return false;
			}
		}
		return false;
	}
	
	public static boolean tosell(Player p) throws Exception{
		Team myTeam = saveGame.getMyTeam();
		Team oldMy = myTeam;
		if(Math.random()>0.4){
			DBmain d = saveGame.getDB();
			Random R = new Random();
			int startr = R.nextInt(d.getSize());
			Team oldSell = d.getTeam(startr);
			Team sellTeam = oldSell;
			while(oldSell.getNm()==myTeam.getNm() | oldSell.getBdgt_rel()<p.getPri()){
				int r = R.nextInt(d.getSize());
				oldSell = d.getTeam(r);
				sellTeam = oldSell;
			}
			if(CreateSelection.checkSize(myTeam)){
				myTeam.addBudget_rel(p.getPri());
				myTeam.addBudget_vir(p.getPri());
				sellTeam.subtractBudget_rel(p.getPri());
				sellTeam.subtractBudget_vir(p.getPri());
				myTeam.removePlayer(p);
				sellTeam.addPlayer(p);
				
				CreateSelection.create(myTeam);
			}
			else{
				throw new Exception();
			}
			saveGame.refreshTeam(oldSell, sellTeam);
			saveGame.refreshTeam(oldMy, myTeam);
			return true;			
		}
		return false;
	}
}