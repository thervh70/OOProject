package Controller;

import java.util.ArrayList;

import Model.*;
/**Our class which handles selection creation
 * @author All
 */
public class CreateSelection {
	
	/**Check to see if a team isn't too small
	 * @param t - Team to be checked
	 * @return boolean
	 */
	public static boolean checkSize(Team t) {
		//Create a for loop for the three types of players
		for(int type=0;type<3;type++) {
			int amount = 0;
			int count = 0;
			String[] types = null;
			
			//A switch to create a string list for each type of player
			//and set the amount of said type you need
			switch(type) {
			case 0: amount = 2; types = new String[] {"GK"}; break;
			case 1: amount = 9; types = new String[] {"RB", "LB", "CB","CDM", "CM"}; break;
			case 2: amount = 7; types = new String[] {"CAM", "LW", "RW","ST"}; break;
			}
			
			//Check the entire team for players of the predetermined type
			for(int i=0;i<t.getSize();i++) {
				String pos = t.getPlayer(i).getPos();
				if(containsString(types, pos))
					count++;
			}
			
			//If the number of players of the predetermined type counted is lower than the amount, it returns false
			if(count < amount){
				return false;
			}
		}
		//If all is fine, it returns true
		return true;
	}
	
	/**Method to create the selection for a team
	 * @param t - Team for which a selection needs to be made
	 * @return Team - The team with its selection
	 */
	public static Team create(Team t) {
		//Create a backup of the team
		Team backup = t;
		//Remove the selection from the team (in case one already excists)
		t.removeSelection();
		//Create a for loop for the three types of players
		for(int type=0;type<3;type++) {
			int amount = 0;
			String[] types = null;
			
			/**A switch which will create a String list of all types of players
			 * and set the amount of said type you need
			 */
			switch(type) {
			case 0: amount = 1; types = new String[] {"GK"}; break;
			case 1: amount = 6; types = new String[] {"RB", "LB", "CB","CDM", "CM"}; break;
			case 2: amount = 4; types = new String[] {"CAM", "LW", "RW","ST"}; break;
			}
			
			//Create a list of players, add all the players of the type selected with the switch
			ArrayList<Player> list = new ArrayList<Player>();
			for(int i=0;i<t.getSize();i++) {
				String pos = t.getPlayer(i).getPos();
				if(containsString(types, pos))
					list.add(t.getPlayer(i));
			}
			/**Add the best players of the previously selected type to the Selection
			 * Add the player to the team
			 * Remove the player from the list
			 */
			for(int j=0;j<amount;j++) {
				Player p = getBestPlayer(list);
				t.addPlayer(p);
				t.toSelection(p);
				list.remove(p);
			}
		}
		//Refresh the teams (swap the backup one in the savegame with the new one)
		saveGame.refreshTeam(backup, t);
		return t;
	}
	
	/**Select the best player from a list of players
	 * @param list - The list of players from which you want the best player selected
	 * @return Player - The best player
	 */
	private static Player getBestPlayer(ArrayList<Player> list) {
		//Select the first player in the list
		Player res = list.get(0);
		//Set the score to the score belonging to said player
		double score = getPlayerScore(res);
		//For loop which loops through all the players
		for(int i=0;i<list.size();i++) {
			//If a person is better than the previous best player, set him as the best player
			if(getPlayerScore(list.get(i)) > score) {
				res = list.get(i);
			}
		}
		//Return the best player from the list
		return res;
	}
	/**Method to get a players score
	 * @param pl - A player
	 * @return double
	 */
	private static double getPlayerScore(Player pl) {
		//Set the score to 0 to start of
		double score = 0;
		//If it's a Fieldplayer, get these scores
		if(pl instanceof Fieldplayer) {
			//Cast the player as a Fieldplayer
			Fieldplayer p = (Fieldplayer)(pl);
			//Get all the statistics belonging to said player
			String pos = p.getPos();
			int pac = p.getPac();
			int sho = p.getSho();
			int pas = p.getPas();
			int dri = p.getDri();
			int def = p.getDef();
			int phy = p.getPhy();
			//Calculate the total score depending on position
			if(pos.equals("RB") || pos.equals("CB") || pos.equals("LB")) {
				score = (pac*10 + sho*10 + pas*15 + dri*10 + def*40 + phy*15)/100;
			}
			else if(pos.equals("CDM") || pos.equals("CM")) {
				score = (pac*15 + sho*10 + pas*20 + dri*10 + def*25 + phy*20)/100;
			}
			else if(pos.equals("CAM") || pos.equals("LW") || pos.equals("RW")) {
				score = (pac*15 + sho*15 + pas*15 + dri*15 + def*15 + phy*25)/100;
			}
			else if(pos.equals("ST")) {
				score = (pac*10 + sho*25 + pas*15 + dri*15 + def*10 + phy*25)/100;
			}
		}
		//If it's a Goalkeeper, get these scores
		else if(pl instanceof Goalkeeper) {
			//Cast the player as a Goalkeeper
			Goalkeeper p = (Goalkeeper)(pl);
			int div = p.getDiv();
			int han = p.getHan();
			int kick = p.getKick();
			int ref = p.getRef();
			int spd = p.getSpd();
			int ping = p.getPing();
			int hei = p.getHei();
			//Calculate the total score
			score = (div + han + kick + ref + spd + ping + hei)/7;
		}
		return score;
	}
	
	/**Check to see if an array of strings contains a certain string
	 * @param array - The array of strings
	 * @param string - The string
	 * @return boolean
	 */
	private static boolean containsString(String[] array, String string) {
		for(int i=0;i<array.length;i++) {
			if(array[i].equals(string)) {
				return true;
			}
		}
		return false;
	}

}
