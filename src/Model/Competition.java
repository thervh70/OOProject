package Model;

import java.util.ArrayList;

public class Competition {

	private ArrayList<Match> matches = new ArrayList<Match>();
	
	
	public void add(Match match){
		matches.add(match);
	}
	
	public String toString(){
		return matches.toString();
	}
	
	public int getSize(){
		return matches.size();
	}
	
	public Match get(int index){
		return matches.get(index);
	}
	
}
