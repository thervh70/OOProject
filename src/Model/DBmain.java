package Model;
import java.util.ArrayList;


public class DBmain {
	
	ArrayList<Team> d;
	
	/**
	 * Constructor creates a Database with an ArrayList of Teams
	 */
	
	public DBmain() {
		d = new ArrayList<Team>();
	}
	
	/**
	 * Method getSize returns the size of a Database
	 * @return int size
	 */
	
	public int getSize() {
		return d.size();
	}
	
	/**
	 * Method addTeam adds a Team to the Database, given the Team isn't already in the Database
	 * @param t Team
	 */
	
	public void addTeam(Team t) {
		if(!d.contains(t)) {
			d.add(t);
		}
	}
	
	public void clearAllCardsInjuries(){
		for(Team t : d){
			t.clearCardsInjuries();
		}
	}

	/**
	 * Method toString returns a String-representation of a Database
	 */
	
	public String toString() {
		String res = "Divisie: ("+d.size()+")\n----------\n";
		for(int i=0;i<d.size();i++) {
			res += d.get(i) + "\n\n";
		}
		res.substring(0,res.length()-2);
		return res;
	}
	
	/**
	 * Method toWrite Stringifies a Database so it can be written in xml-file
	 * @return String DB
	 */
	
	public String toWrite() {
		String res = "<DATABASE>\r\n";
		for(int i=0;i<d.size();i++) {
			Team t = d.get(i);
			res += t.toWrite();
		}
		res += "</DATABASE>\r\n";
		return res;
	}
	
	/**
	 * Method getTeam returns a given Team from the Database
	 * @param i
	 * @return
	 */
	
	public Team getTeam(int i) {
		return d.get(i); 
	}
	
	/**
	 * Method equals checks if two DBmains are equal to each other.
	 * Criteria:
	 *  - Size of both DBmains has to be the same
	 *  - Both DBmains must have equal Teams at every position in their ArrayLists.
	 */
	
	public boolean equals(Object other) {
		if(other instanceof DBmain) {
			DBmain that = (DBmain)(other);
			if(!(this.getSize() == that.getSize())) {return false; }
			for(int i=0; i<this.getSize(); i++) {
				if(!(this.getTeam(i).equals(that.getTeam(i)))) {return false; }
			}
			return true;
		}
		return false;
	}

	public void removeTeam(Team t) {
		if(d.contains(t)) {
			d.remove(t);
		}
	}
	
	/**Method to look for a player based on its name (String)
	 * 
	 * @param s String representing Full name of player
	 * @return Player object
	 */
	public Player lookForPlayer(String s){
		for(Team t : d){
			for(Player p : t.getTeam()){
				if(p.getName().equals(s))
					return p;
			}
		}
		return null;
	}
}
