package Database;

import java.util.ArrayList;

public class Team {
	
	private ArrayList<Player> t;
	private String nm;
	private int bdgt_vir, bdgt_rel;
	
	/**
	 * Constructor creates a Team with a budget, teamname and an ArrayList of Players
	 * @param name
	 * @param budget
	 */
	
	public Team(String name, int budget_vir, int budget_rel) {
		t = new ArrayList<Player>();
		nm = name;
		bdgt_vir = budget_vir;
		bdgt_rel = budget_rel;
	}
	
	/**
	 * Method addPlayer adds a Player (Fieldplayer or Goalkeeper) to a Team given the Player isn't already in the Team
	 * @param p Player
	 */
	
	public void addPlayer(Player p) {
		if(!t.contains(p)) {
			t.add(p);
		}
	}
	
	public boolean containsPlayer(Player p){
		return t.contains(p);
	}
	
	public void removePlayer(Player p){
		if(t.contains(p)){
			t.remove(p);
		}
	}
	
	public void addBudget_vir(int i){
		this.bdgt_vir+=i;
	}
	
	public void subtractBudget_vir(int i){
		this.bdgt_vir-=i;
	}
	public void addBudget_rel(int i){
		this.bdgt_rel+=i;
	}
	
	public void subtractBudget_rel(int i){
		this.bdgt_rel-=i;
	}
	
	/**
	 * Method toString gives a String-representation of a Team
	 * @return res String
	 */
	
	public String toString() {
		String res = "Team: " + nm + "(" + t.size() + "), Virtual budget: "+this.getBdgt_vir()+", Budget: "+this.getBdgt_rel()+"\n  ";
		for (int i = 0; i < t.size(); i++) {
			res += t.get(i) + "\n";
		}
		res.substring(0,res.length() - 2);
		res += "\n";
		return res;
	}
	
	/**
	 * Method toWrite Stringifies a Team so it can be written in xml-file
	 * @return
	 */
	
	public String toWrite() {
		String res = "   <TEAM>\r\n";
		res += "      <TEAMNAME>" + this.nm + "</TEAMNAME>\r\n";
		res += "      <VIRTUAL_BUDGET>" + this.bdgt_vir + "</VIRTUAL_BUDGET>\r\n";
		res += "      <BUDGET>" + this.bdgt_rel + "</BUDGET>\r\n";
		for(int i=0;i<t.size();i++) {
			if(this.getPlayer(i) instanceof Fieldplayer) {
				Fieldplayer p = (Fieldplayer)(this.getPlayer(i));
				res += p.toWrite();
			}
		}
		res += "   </TEAM>\r\n";
		return res;
	}
	
	/**The attacking score of a team is calculated.
	 * This is done by adding all of the attacking score of a player.
	 * This means only type 3 or 4 players (str/mid and str)
	 * The score is divided by the amount of players of that type.
	 * Final score is enlarged and rounded
	 * 
	 * @return An attacking score value
	 */
	
	public double calcAttScore(){
		double score = 0;
		
		for(int i = 0; i < this.getSize(); i++){
			if(this.getPlayer(i) instanceof Fieldplayer) {
				Fieldplayer p = (Fieldplayer)(this.getPlayer(i));
				score += p.calcAttScore();
			}
		}
		
		int count = this.count(3) + this.count(4);
				
		score /= count;
		//score *= 1000;
		score = Math.round(score);
		
		return score;
	}
	
	/**The defending score of a team is calculated.
	 * This is done by adding all of the defending score of a player.
	 * This means only type 1 or 2 players (def and def/mid)
	 * The score is divided by the amount of players of that type.
	 * Final score is enlarged and rounded
	 * 
	 * @return A defending score value
	 */
	
	public double calcDefScore(){
		double score = 0;
	
		for(int i = 0; i < this.getSize(); i++){
			if(this.getPlayer(i) instanceof Fieldplayer) {
				Fieldplayer p = (Fieldplayer)(this.getPlayer(i));
				score += p.calcDefScore();
			}
		}
		
		int count = this.count(1) + this.count(2);
		
		score /= count;
		//score *= 1000;
		score = Math.round(score);
		
		return score;
	}
	
	/**Count the amount of players of a certain type
	 * 
	 * @param a Type - 0=Keeper, 1=Defender, 2=Def/Mid, 3=Str/Mid, 4=Striker
	 * @return Count
	 */
	
	public int count(int a){
		int count = 0;
		for(int i = 0; i < this.getSize(); i++){
			Player p = this.getPlayer(i);
			String pos = p.getPos();
			
			switch(a){
			case 0:
				if(pos.equals("GK")) count++;
				break;
			case 1:
				if(pos.equals("RB") || pos.equals("CB") || pos.equals("LB")) count++;
				break;
			case 2:
				if(pos.equals("CDM") || pos.equals("CM")) count++;
				break;
			case 3:
				if(pos.equals("CAM") || pos.equals("LW") || pos.equals("RW")) count++;
				break;
			case 4:
				if(pos.equals("ST")) count++;
				break;
			}
		}
		return count;
	}
	
	/**
	 * Methode equals checks if two Teams are equal to each other. 
	 * Criteria:
	 *  - Size of both Teams has to be the same
	 *  - Name of both Teams has to be the same
	 *  - Real and Virtual Budgets of both Teams have to be the same
	 *  - Both Teams must have equal Players at each position on their ArrayLists. Method equals in class Player is used for this.
	 */
	
	public boolean equals(Object other) {
		if(other instanceof Team) {
			Team that = (Team)(other);
			if(!(this.getSize() == that.getSize() && this.getNm().equals(that.getNm()) && this.getBdgt_rel() == that.getBdgt_rel() && this.getBdgt_vir() == that.getBdgt_vir())) {
				return false;
			}
			for(int i=0; i<this.getSize(); i++) {
				if(!(this.getPlayer(i).equals(that.getPlayer(i)))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Getters
	 */
	
	public String getNm() {return this.nm;}
	public ArrayList<Player> getT() {return this.t;}
	public int getBdgt_vir() {return this.bdgt_vir;}
	public int getBdgt_rel() {return this.bdgt_rel;}
	public int getSize() {return t.size();}
	public Player getPlayer(int i) {return t.get(i);}
	
	/**
	 * Setters
	 */
	
	public void setNm(String name) {this.nm = name;}
	public void setBdgt_vir(int budget_vir) {this.bdgt_vir = budget_vir;}
	public void setBdgt_rel(int budget_rel) {this.bdgt_rel = budget_rel;}
}
