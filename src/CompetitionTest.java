import java.util.ArrayList;

import Controller.saveGame;
import Model.Competition;
import Model.Match;
import Model.Scheduler;
import Model.Team;


public class CompetitionTest {

	public static void main(String[] args) {
		

		Competition comp = Scheduler.generate();
		
		//System.out.println("Gameday size: " + gameday.size());
		
		for(int i = 0; i < 18; i++){
			ArrayList<Match> gameday = comp.GetMatchesForDay(i);
			Match game = gameday.get(0);
			Team A = game.getTeamHome();
			Team B = game.getTeamAway();
			
			System.out.println(A.getNm());
			System.out.println(B.getNm() + "\n");
		}
		
	}

}
