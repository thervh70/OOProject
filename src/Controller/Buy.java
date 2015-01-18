package Controller;

import Model.Player;
import Model.Team;

public class Buy {

	private int bid;
	private Player p;
	private Team buyTeam, myTeam;
	
	public Buy(Player player, Team bTeam, int b){
		bid = b;
		p = player;
		buyTeam = bTeam;
		myTeam = saveGame.getMyTeam();
	}
	
	public int getBid() {return bid;}
	public void setBid(int bid) {this.bid = bid;}
	public Player getP() {return p;}
	public void setP(Player p) {this.p = p;}
	public Team getBuyTeam() {return buyTeam;}
	public void setBuyTeam(Team buyTeam) {this.buyTeam = buyTeam;}
	public Team getMyTeam() {return myTeam;}
	public void setMyTeam(Team myTeam) {this.myTeam = myTeam;}
}
