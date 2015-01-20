package Model;

import java.util.ArrayList;

public class Competition {

	private ArrayList<Match> matches;
	
	/**
	 * Create a competion. In this object we will have an arraylist with all the matches that are and played and those that will be played
	 */
	public Competition() {
		matches = new ArrayList<Match>();
	}
	
	/**
	 * add a match to the competition
	 * @param match		Match object
	 */
	public void add(Match match){
		if(!matches.contains(match)) {
			matches.add(match);
		}
	}
	
	/**
	 * remove a match from the competition
	 * @param match		Match object
	 */
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
	
	/**
	 * @return String representation of the competition
	 */
	public String toWrite() {
		String write = "   <Competition>\r\n";
		for(int i=1;i<=34;i++) {
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
	
	/**
	 * @return	the amount of matches
	 */
	public int getSize(){
		return matches.size();
	}
	
	/**
	 * @param index
	 * @return the Match object with this index in the arraylist
	 */
	public Match get(int index){
		return matches.get(index);
	}
	
	/**
	 * @param day 
	 * @return an arraylist with all the matches for a certain day
	 */
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
	
	/**
	 * Add scores to matches after they have just been played
	 * @param day the day that has just passed
	 * @param m
	 */
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
