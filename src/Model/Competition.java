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
	
	public boolean equals(Object obj){
		if(!(obj instanceof Competition)){
			return false;
		}
		else{
			Competition that = (Competition)(obj);
			if(this.matches.equals(that.matches)){
				return true;
			}
			return false;
		}
		
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
	
	public void setMatchesForDay(int day, Match m){
		for (int i = 0; i < this.matches.size(); i++) {
			Match matchSchedule = this.matches.get(i);
			if(m.getTeamHome().getNm().equals(matchSchedule.getTeamHome().getNm()) & m.getTeamAway().getNm().equals(matchSchedule.getTeamAway().getNm())){
				matchSchedule.setGoalsHome(m.getGoalsHome());
				matchSchedule.setGoalsAway(m.getGoalsAway());
			}
		}
	}
	
	
}
