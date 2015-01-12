

import Controller.saveGame;
import Model.DBmain;
import Model.Team;
import Model.XmlParser;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DBmain hoi;
		hoi = XmlParser.parseDB();
		

		Team t = hoi.getTeam(0);
		t.newStanding();
		
		System.out.println(t.getStanding().getTeamName());
	}

}
