package Model;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

	public static void main(String[] args){
		//Team[] teamOrder = new Team[18];
		ArrayList<Team> teams = new ArrayList<Team>();
		List<Team> teamOrder = new ArrayList<Team>();
		DBmain dbmain = XmlParser.parseDB();
		Team teamHome;
		Team teamAway;
		for (int i = 0; i < 17; i++) {
			teams.add(dbmain.getTeam(i));
		}
		for (int i = 0; i < 17; i++) {
			int number = (int) Math.round((Math.random()* 18- 0.5));
			while(teamOrder.contains(dbmain.getTeam(number))){
				number = (int) Math.round((Math.random()* 18- 0.5));
			}
			teamOrder.add(dbmain.getTeam(number));
		}
		for (int day = 0; day < 33; day++) {
		
			if(day<=18){
				for (int i = 0; i < 8; i++) {
					int numberTeamHome = day + 2*i + 1;
					if(numberTeamHome > 17 ){
						numberTeamHome -=18;
					}
					int numberTeamAway = numberTeamHome - day;
					if(numberTeamAway < 0){
						numberTeamAway += 18;
					}
					teamHome = teamOrder.get(numberTeamHome);
					teamAway= teamOrder.get(numberTeamAway);
				}
				
				
			}
			else{
				
			}
			
		}
	}
}
