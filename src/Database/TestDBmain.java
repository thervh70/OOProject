package Database;
import java.util.ArrayList;


public class TestDBmain {
	
	ArrayList<Team> d;

	public TestDBmain() {
		d = new ArrayList<Team>();
	}
	
	public int getSize() {
		return d.size();
	}
	
	public void addTeam(Team t) {
		if(!d.contains(t)) {
			d.add(t);
		}
	}
	
	public String toWrite() {
		String res = "<DATABASE>\r\n";
		for(int i=0;i<d.size();i++) {
			Team t = d.get(i);
			res += t.toWrite();
		}
		res += "</DATABASE>";
		return res;
	}
	
	public String toString() {
		String res = "Divisie: \n";
		for(int i=0;i<d.size();i++) {
			res += d.get(i);
		}
		res.substring(0,res.length() - 1);
		res += "\n";
		return res;
	}
	
	public Team getT(int i) {
		return d.get(i);
	}
}
