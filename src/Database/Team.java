package Database;

import java.util.ArrayList;

public class Team {
	
	private ArrayList<Player> t;
	private String nm;
	int bdgt;
	/**
	 * Constructor creates a Team with a budget, teamname and an ArrayList of Players
	 * @param name
	 * @param budget
	 */
	public Team(String name, int budget) {
		t = new ArrayList<Player>();
		nm = name;
		bdgt = budget;
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
		String res = "Team: " + nm + "(" + t.size() + ")\n  ";
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
		res += "      <TEAMNAME>" + nm + "</TEAMNAME>\r\n";
		res += "      <BUDGET>" + bdgt + "</BUDGET>\r\n";
		for(int i=0;i<t.size();i++) {
			Player p = this.getT().get(i);
			res += p.toWrite();
		}
		res += "   </TEAM>\r\n";
		return res;
	}
	/**
	 * Getters
	 */
	public String getNm() {return this.nm;}
	public ArrayList<Player> getT() {return this.t;}
	public int getBdgt() {return this.bdgt;}
	public int getSize() {return t.size();}
	public Player getPlayer(int i) {return t.get(i);}
	/**
	 * Setters
	 */
	public void setNm(String name) {this.nm = name;}
	public void setNm(int budget) {this.bdgt = budget;}
}
