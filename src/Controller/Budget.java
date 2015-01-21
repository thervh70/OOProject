package Controller;
/**Our budget class, handles bidding on players and selling players
 * @author Bram
 */

import java.util.Random;
import Model.Player;
import Model.Team;
import Model.DBmain;
public class Budget {
	
/**Method for buying players
 * @param p - The player you are buying
 * @param buyTeam - The team you are buying from
 * @param bid - The amount of money you are bidding
 * @param myTeam - Your team
 * @throws Exception - Exception
 */
	public static void buy(Player p, Team buyTeam, int bid, Team myTeam) throws Exception {
		//Create backups of the teams in case something goes wrong
		Team oldBuy = buyTeam;
		Team oldMy = myTeam;
		//Adds the player to your team and removes it from the other team
		myTeam.addPlayer(p);
		buyTeam.removePlayer(p);
		
		//Check if the team you are buying from won't become too small
		if(CreateSelection.checkSize(buyTeam)){
			myTeam.subtractBudget_rel(bid);
			buyTeam.addBudget_rel(bid);
			buyTeam.addBudget_vir(bid);
			
			//Raises buyc in the savegame, this means you can only bid on three players on one day
			saveGame.cbuyUp();
			
			CreateSelection.create(buyTeam);
		}
		//If the team does become to small, the player gets returned to the original team and an exception is thrown
		else{
			myTeam.removePlayer(p);
			buyTeam.addPlayer(p);
			throw new Exception();
		}
		//Updates the teams in the database to the edited teams
		saveGame.refreshTeam(oldBuy, buyTeam);
		saveGame.refreshTeam(oldMy, myTeam);
	}
	/**Method to refund your virtual budget
	 * @param bid - The amount of money you are bidding
	 * @param myTeam - Your team
	 */
	public static void refund(int bid, Team myTeam){
		myTeam.addBudget_vir(bid);
	}
	/**Method to bid on players
	 * @param p - The player you are bidding on
	 * @param buyTeam - The team that now owns said player
	 * @param bid - The amount of money you are bidding
	 * @return - True or False (if the player is or isn't bought, respectively)
	 * @throws Exception
	 */
	public static boolean bid(Player p, Team buyTeam, int bid) throws Exception {
		//Gets your team from the savegame
		Team myTeam = saveGame.getMyTeam();
		//Check to see if you can afford the player
		if(!(bid>myTeam.getBdgt_vir())){
			//subtracts the price of the player from the virtual budget
			myTeam.subtractBudget_vir(bid);
			//If you bid under 80% of the player price your offer is automatically rejected
			if((bid>(0.8*p.getPri()))){
				//If the bid is between 80% and 90% of the player price this branch is executed
				if(bid<=(0.9*p.getPri())){
					//There is a 40% chance your order is accepted
					if(Math.random()>0.6){
						buy(p,buyTeam,bid,myTeam);
						return true;
					}
					else{
						refund(bid,myTeam);
						return false;
					}
				}
				//If the bid is between 90% and 100% of the player price this branch is executed
				else if((0.9*p.getPri())<bid & bid<=p.getPri()){
					//There is a 50% chance your order is accepted
					if(Math.random()>0.5){
						buy(p,buyTeam,bid,myTeam);
						return true;
					}
					else{
						refund(bid,myTeam);
						return false;
					}
				}
				//If the bid is between 100% and 150% of the player price this branch is executed
				else if((p.getPri()<bid & bid<=(1.5*p.getPri()))){
					//There is a 70% chance your order is accepted
					if(Math.random()>0.3){
						buy(p,buyTeam,bid,myTeam);
						return true;
					}
					else{
						refund(bid,myTeam);
						return false;
					}
				}
				//If the bid is between 150% and 190% of the player price this branch is executed
				else if((1.5*p.getPri())<bid & bid<=(1.9*p.getPri())){
					//There is an 80% chance your order is accepted
					if(Math.random()>0.2){
						buy(p,buyTeam,bid,myTeam);
						return true;
					}
					else{
						refund(bid,myTeam);
						return false;
					}
				}
				//If the bid is higher than 190% of the player price this branch is executed
				else if(bid>(1.9*p.getPri())){
					//There is a 90% chance your order is accepted
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

	/**Method to sell players
	 * @param p - The player you want to sell
	 * @return - True or False (if the player is or isn't sold, respectively)
	 * @throws Exception
	 */
	public static String tosell(Player p) throws Exception{
		//Gets your team from the savegame
		Team myTeam = saveGame.getMyTeam();
		//Makes a backup of your team in case something goes wrong
		Team oldMy = myTeam;
		//There is a 60% chance a team will buy the player you are selling
		if(Math.random()>0.4){
			//Get the list of teams from the savegame
			DBmain d = saveGame.getDB();
			//Create a random integer between 0 and 17
			Random R = new Random();
			int startr = R.nextInt(d.getSize());
			//Selects the team which is on the randomly selected place in the list
			Team oldSell = d.getTeam(startr);
			//Creates a backup of said team
			Team sellTeam = oldSell;
			//Checks to see if the randomly selected team isn't your own team
			//Checks to see if the randomly selected team can afford the player you are selling
			//If either of these checks fail, a new team will be randomly selected
			while(oldSell.getNm().equals(myTeam.getNm()) | oldSell.getBdgt_rel()<p.getPri()){
				int r = R.nextInt(d.getSize());
				oldSell = d.getTeam(r);
				sellTeam = oldSell;
			}
			
			//Moves the player from your team to the randomly selected team
			myTeam.removePlayer(p);
			sellTeam.addPlayer(p);
			
			//Checks to see if your team won't become too small if you sell the player you are trying to sell
			if(CreateSelection.checkSize(myTeam)){
				//Adds the price of the player to your budget
				myTeam.addBudget_rel(p.getPri());
				myTeam.addBudget_vir(p.getPri());
				//Subtracts the price of the player from the randomly selected teams budget
				sellTeam.subtractBudget_rel(p.getPri());
				sellTeam.subtractBudget_vir(p.getPri());
				//Creates a new selection for your team
				CreateSelection.create(myTeam);
				//Update the teams in the database to the edited teams
				saveGame.refreshTeam(oldSell, sellTeam);
				saveGame.refreshTeam(oldMy, myTeam);
				//Raises sellc in the savegame, this means you can only sell three players on one day
				saveGame.csellUp();
				return sellTeam.getNm();
			}
			//If your team becomes too small an exception is thrown
			else{
				myTeam.addPlayer(p);
				sellTeam.removePlayer(p);
				throw new Exception();
			}			
		}
		return null;
	}
}
