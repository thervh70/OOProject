package Database;
import java.util.ArrayList;
//mathias was here
public class Team {
	private ArrayList<Player> t;
	private String nm;
	int bdgt;
	
	public Team(String name, int budget) {
		t = new ArrayList<Player>();
		nm = name;
		bdgt = budget;
	}
	
	public void addPlayer(Player p) {
		if(!t.contains(p)) {
			t.add(p);
		}
	}
	
	public String toString() {
		String res = "Team: " + nm + "(" + t.size() + ")\n  ";
		for (int i = 0; i < t.size(); i++) {
			res += t.get(i) + "\n  ";
		}
		res.substring(0,res.length() - 3);
		res += "\n";
		return res;
	}
	
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
	
	public String getNm() {
		return this.nm;
	}

	public ArrayList<Player> getT() {
		return this.t;
	}
	
	public int getBdgt() {
		return this.bdgt;
	}
	
	public int getSize() {
		return t.size();
	}
	
	public Player getPlayer(int i) {
		return t.get(i);
	}
}
