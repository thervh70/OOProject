package Model;

import java.util.ArrayList;

public class Competition {

	private ArrayList<Match> matches;
	
	public Competition() {
		matches = new ArrayList<Match>();
	}
	
	public void add(Match match){
		matches.add(match);
	}
	
	public String toString(){
		String txt = "Competition[";
		for(int i=0;i < matches.size();i++){
			txt+=matches.get(i).toString();
			if(i < (matches.size() - 1)){
				txt+=", ";
			}
		}
		txt+="]";
		return txt;
	}
	
	public String toWrite() {
		String write = "<COMPETITION>\r\n";
		for(int i=0;i<matches.size();i++) {
			write += matches.get(i).toWrite();
		}
		write += "</COMPETITION>\r\n";
		return write;
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
