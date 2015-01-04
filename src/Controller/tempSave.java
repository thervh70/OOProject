package Controller;

import java.util.ArrayList;

import Model.Player;
import Model.Team;

public class tempSave {
	public static ArrayList<Player> basePlayers;
	public static ArrayList<Player> benchPlayers;
	public static ArrayList<Player> myPlayers;
	
	/**This method returns the requested ArrayList of base players from your team
	 * 
	 * @return - ArrayList of Players
	 */
	public static ArrayList<Player> getBasePlayers() {
		//Gets your Team and puts the players in an ArrayList
		Team myTeam = saveGame.getMyTeam();
		for(int i = 0; i < myTeam.getSize(); i++){
			myPlayers.add(myTeam.getPlayer(i));
		}
		return basePlayers;
	}
	
	public static ArrayList<Player> getBenchPlayers() {
		return benchPlayers;
	}
	
	public static void switchPlayers(Player base, Player bench) {
		basePlayers.set(basePlayers.indexOf(base), bench);
		benchPlayers.set(benchPlayers.indexOf(bench), base);
	}
}
