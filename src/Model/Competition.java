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
		String txt = "Competition\n===========\n";
		for(int i=0;i < matches.size();i++){
			txt+=matches.get(i).toString() + "\n";
		}
		return txt;
	}
	
	public String toWrite() {
		String write = "<Competition>\r\n";
		for(int i=1;i<34;i++) {
			write += "   <Round>\r\n";
			write += "      <Day>"+i+"</Day>\r\n";
			ArrayList<Match> day = getMatchesForDay(i);
			for(Match m : day) {
				write += m.toWrite();
			}
			write += "   </Round>\r\n";
		}
		for(int i=0;i<matches.size();i++) {
			write += matches.get(i).toWrite();
		}
		write += "</Competition>\r\n";
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
