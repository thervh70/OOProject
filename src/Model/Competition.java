package Model;

import java.util.ArrayList;

public class Competition {

	private ArrayList<Match> matches = new ArrayList<Match>();
	
	
	public void add(Match match){
		matches.add(match);
	}
	
	public String toString(){
		String txt = "";
		for(int i=0;i < matches.size();i++){
		txt+=matches.get(i).toString();
		}
		return txt;
	}
	
	public int getSize(){
		return matches.size();
	}
	
	public Match get(int index){
		return matches.get(index);
	}
	
	public ArrayList<Match> getMatchesForDay(int day){
		ArrayList<Match> matches= new ArrayList<Match>();
		for(int i = 0; i < this.getSize(); i++){
			Match game = this.get(i);
			
			if(game.getDay() == day){
				matches.add(game);
			}
		}
		return matches;
	}
}
