package Database;

import java.util.ArrayList;

public class Team {
	
	private ArrayList<Player> t;
	private String nm;
	int bdgt_vir, bdgt_rel;
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
	 * Method addPlayer adds a Player to a Team given the Player isn't already in the Team
	 * @param p Player
	 */
	public void addPlayer(Player p) {
		if(!t.contains(p)) {
			t.add(p);
		}
	}
	/**
	 * Method toString gives a String-representation of a Team
	 * @return res String
	 */
	public String toString() {
		String res = "Team: " + nm + "(" + t.size() + ") Virtual budget: "+this.getBdgt_vir()+", Budget: "+this.getBdgt_rel()+"\n  ";
		for (int i = 0; i < t.size(); i++) {
			res += t.get(i) + "\n  ";
		}
		res.substring(0,res.length() - 3);
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
			Player p = this.getT().get(i);
			res += p.toWrite();
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
	
	public int calcAttScore(){
		double score = 0;
		
		for(int i = 0; i < this.getSize(); i++){
			Player p = this.getPlayer(i);
			score += p.calcAttScore();
		}
		
		int count = this.count(3) + this.count(4);
				
		score /= count;
		score *= 10;
		score = Math.round(score);
		
		return (int) score;
	}
	
	/**The defending score of a team is calculated.
	 * This is done by adding all of the defending score of a player.
	 * This means only type 1 or 2 players (def and def/mid)
	 * The score is divided by the amount of players of that type.
	 * Final score is enlarged and rounded
	 * 
	 * @return A defending score value
	 */
	
	public int calcDefScore(){
		double score = 0;
		
		for(int i = 0; i < this.getSize(); i++){
			Player p = this.getPlayer(i);
			score += p.calcDefScore();
		}
		
		int count = this.count(1) + this.count(2);
		
		score /= count;
		score *= 10;
		score = Math.round(score);
		
		return (int) score;
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
				if(pos.equals("KP")) count++;
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
