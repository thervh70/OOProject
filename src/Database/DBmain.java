package Database;
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
		res += "</DATABASE>";
		return res;
	}
	/**
	 * Method toString returns a String-representation of a Database
	 */
	public String toString() {
		String res = "Divisie: ("+d.size()+")\n";
		for(int i=0;i<d.size();i++) {
			res += d.get(i);
		}
		res.substring(0,res.length() - 1);
		res += "\n";
		return res;
	}
	/**
	 * Method getT returns a given Team from the Database
	 * @param i
	 * @return
	 */
	public Team getT(int i) {
		return d.get(i);
	}
}
