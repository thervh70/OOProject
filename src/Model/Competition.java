package Model;

import java.util.ArrayList;

public class Competition {

	private ArrayList<Match> matches;
	
	public Competition() {
		matches = new ArrayList<Match>();
	}
	
	public void add(Match match){
		if(!matches.contains(match)) {
			matches.add(match);
		}
	}
	
	public void add(int i, Match match) {
		if(!matches.get(i).equals(match)) {
			matches.add(i, match);
		}
	}
	
	public void remove(Match match) {
		matches.remove(match);
	}
	
	public String toString(){
		String txt = "Competition\n===========\n";
		for(int i=0;i < matches.size();i++){
			txt+=matches.get(i).toString() + "\n";
		}
		return txt;
	}
	
	public String toWrite() {
		String write = "   <Competition>\r\n";
		for(int i=1;i<34;i++) {
			write += "      <Round>\r\n";
			write += "         <Day>"+i+"</Day>\r\n";
			ArrayList<Match> day = getMatchesForDay(i);
			for(Match m : day) {
				write += m.toWrite();
			}
			write += "      </Round>\r\n";
		}
		write += "   </Competition>\r\n";
		return write;
	}
	
	public int getSize(){
		return matches.size();
	}
	
	public Match get(int index){
		return matches.get(index);
	}
	
	public int getIndex(Match m) {
		for(int i=0;i<matches.size();i++) {
			Match match = matches.get(i);
			if(match.equals(m)) {
				return i;
			}
		}
		return -1;
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
