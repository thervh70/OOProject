package Model;

import java.util.ArrayList;

public class Selection {
	
	private ArrayList<Player> s;
	
	public Selection() {
		s = new ArrayList<Player>();
	}
	
	public int getSize() {
		return s.size();
	}
	
	public Player getPlayer(int i) {
		return s.get(i);
	}
	
	public String toString() {
		String res = "Selection:\n";
		for(int i=0;i<s.size();i++) {
			res += s.get(i);
			res += "\n";
		}
		return res;
	}
	
	public boolean equals(Object other) {
		if(other instanceof Selection) {
			Selection that = (Selection)(other);
			for(int i=0;i<this.getSize();i++) {
				if(!this.getPlayer(i).equals(that.getPlayer(i))) {
					return false;
				}
			}
		}
		return false;
	}

}
